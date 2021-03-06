package com.punter.moneybags.util;

import static com.punter.moneybags.model.constant.BetConstant.EUR_CURRENCY;
import static com.punter.moneybags.model.constant.CurrencyCode.appendSymbolWithAmount;
import static com.punter.moneybags.model.constant.CurrencyCode.currencyOfAlpha3IsoCode;
import static com.punter.moneybags.util.BaseUtil.distinctByKey;
import static com.punter.moneybags.util.BaseUtil.roundToTwoDecimalPoints;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.trim;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.punter.moneybags.model.Bet;
import com.punter.moneybags.model.dao.LiabilityReportTwo;
import com.punter.moneybags.model.dao.SelectionLiabilityReportOne;
import com.punter.moneybags.model.dto.LiabilityEntry;
import com.punter.moneybags.model.dto.SelectionLiabilityEntry;
import com.punter.moneybags.model.request.BetCollectionRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@UtilityClass
public class BetUtil {

  public static BetCollectionRequest convertCSVToBetCollectionRequest(MultipartFile multipartFile) {
    BetCollectionRequest convertedBetCollectionRequest;

    // Validate multipartFile
    if (multipartFile.isEmpty()) {
      convertedBetCollectionRequest = null;
    } else {

      // Parse CSV multipartFile to create a list of `Bet` objects
      try (Reader reader = new BufferedReader(
          new InputStreamReader(multipartFile.getInputStream()))) {

        // Bean reader
        CsvToBean<Bet> csvToBean = new CsvToBeanBuilder(reader)
            .withType(Bet.class)
            .withIgnoreLeadingWhiteSpace(true)
            .build();

        // convert `CsvToBean` object to list of bets
        List<Bet> parsedBets = csvToBean.parse();
        convertedBetCollectionRequest = convertBetListToBetCollectionRequest(parsedBets);

      } catch (Exception ex) {
        convertedBetCollectionRequest = null;
        // TODO: Maybe just return null. No exc needed. Exception for Error occurred while processing the CSV multipartFile.
      }
    }
    return convertedBetCollectionRequest;
  }

  public static BetCollectionRequest convertBetListToBetCollectionRequest(List<Bet> betList) {

    for (Bet eachBet : betList) {
      eachBet.setSelectionName(trim(eachBet.getSelectionName()));
      eachBet.setCurrency(trim(eachBet.getCurrency()));
    }

    return BetCollectionRequest.builder().bets(betList).build();
  }

  public static long countTotalMatchesForPredicate(List<Bet> bets, Predicate<Bet> betPredicate) {

    return bets
        .stream()
        .filter(betPredicate)
        .count();
  }

  public static BigDecimal sumOfStakesForPredicateMatch(List<Bet> bets,
      Predicate<Bet> betPredicate) {

    return BigDecimal.valueOf(bets.stream()
        .filter(betPredicate)
        .mapToDouble(Bet::getStake)
        .sum());
  }

  public static BigDecimal liabilityProductForPredicateMatch(List<Bet> bets,
      Predicate<Bet> betPredicate) {

    final Supplier<ToDoubleFunction<Bet>> liabilityFunction =
        () -> bet -> (bet.getStake()) * (bet.getPrice());
    return BigDecimal.valueOf(bets.stream()
        .filter(betPredicate)
        .mapToDouble(liabilityFunction.get())
        .sum());
  }

  // TODO: Use Pair class
  public static List<Bet> extractDistinctBetsForNameCurrencyValues(List<Bet> bets) {

    return bets.stream()
        .filter(distinctByKey(
            eachBet -> Arrays.asList(eachBet.getSelectionName(), eachBet.getCurrency())))
        .collect(toList());
  }

  public static List<Bet> extractDistinctBetsForCurrencyValues(List<Bet> bets) {

    return bets.stream()
        .filter(distinctByKey(
            eachBet -> Arrays.asList(eachBet.getCurrency())))
        .collect(toList());
  }

  public static SelectionLiabilityReportOne processRequestToReportOne(
      BetCollectionRequest betCollectionRequest) {
    List<SelectionLiabilityEntry> selectionLiabilityEntries = new ArrayList<>();

    List<Bet> betsWithDistinctNameAndCurrencyValues = extractDistinctBetsForNameCurrencyValues(
        betCollectionRequest.getBets());

    log.debug("Distinct bets: " + betsWithDistinctNameAndCurrencyValues.size());

    betsWithDistinctNameAndCurrencyValues.forEach(
        eachDistinctNameCurrency -> {
          String currentName = eachDistinctNameCurrency.getSelectionName();
          String currentCurrency = eachDistinctNameCurrency.getCurrency();
          Predicate<Bet> nameAndCurrencyPredicate = currentNameCurrencyPair ->
              currentNameCurrencyPair.getSelectionName().equals(currentName)
                  && currentNameCurrencyPair.getCurrency().equals(currentCurrency);

          int totalBetsForNameCurrency = (int) countTotalMatchesForPredicate(
              betCollectionRequest.getBets(), nameAndCurrencyPredicate);

          BigDecimal sumOfStakesForNameCurrency = roundToTwoDecimalPoints(
              sumOfStakesForPredicateMatch(
                  betCollectionRequest.getBets(), nameAndCurrencyPredicate));

          BigDecimal sumOfLiabilitiesForNameCurrency = roundToTwoDecimalPoints(
              liabilityProductForPredicateMatch(
                  betCollectionRequest.getBets(), nameAndCurrencyPredicate));

          SelectionLiabilityEntry newEntry = SelectionLiabilityEntry.builder()
              .selectionName(currentName)
              .currency(currentCurrency)
              .totalBets(totalBetsForNameCurrency)
              .totalStakes(appendSymbolWithAmount(currencyOfAlpha3IsoCode(currentCurrency),
                  (sumOfStakesForNameCurrency)))
              .totalLiability(appendSymbolWithAmount(currencyOfAlpha3IsoCode(currentCurrency),
                  (sumOfLiabilitiesForNameCurrency)))
              .build();

          selectionLiabilityEntries.add(newEntry);
        });

    log.debug(String.valueOf(selectionLiabilityEntries));

    return SelectionLiabilityReportOne.builder()
        .selectionLiabilityEntryList(selectionLiabilityEntries).build();
  }

  /**
   * Report two (Per-currency totals)
   *
   * @param betCollectionRequest
   * @return
   */
  public static LiabilityReportTwo processRequestToReportTwo(
      BetCollectionRequest betCollectionRequest) {
    List<LiabilityEntry> liabilityEntries = new ArrayList<>();

    List<Bet> betsWithDistinctCurrencyValues = extractDistinctBetsForCurrencyValues(
        betCollectionRequest.getBets());

    log.debug("Distinct bets: " + betsWithDistinctCurrencyValues.size());

    betsWithDistinctCurrencyValues.forEach(
        eachDistinctCurrency -> {
          String currentCurrency = eachDistinctCurrency.getCurrency();

          Predicate<Bet> currencyPredicateMatch = currentCurrencyBet ->
              currentCurrencyBet.getCurrency().equals(currentCurrency);

          int totalBetsMatchingCurrency = (int) countTotalMatchesForPredicate(
              betCollectionRequest.getBets(), currencyPredicateMatch);

          BigDecimal sumOfStakesMatchingCurrency = roundToTwoDecimalPoints(
              sumOfStakesForPredicateMatch(
                  betCollectionRequest.getBets(), currencyPredicateMatch));

          BigDecimal sumOfLiabilitiesMatchingCurrency = roundToTwoDecimalPoints(
              liabilityProductForPredicateMatch(
                  betCollectionRequest.getBets(), currencyPredicateMatch));

          LiabilityEntry newEntry = LiabilityEntry.builder()
              .currency(currentCurrency)
              .totalBets(totalBetsMatchingCurrency)
              .totalStakes(appendSymbolWithAmount(currencyOfAlpha3IsoCode(currentCurrency),
                  sumOfStakesMatchingCurrency))
              .totalLiability(appendSymbolWithAmount(currencyOfAlpha3IsoCode(currentCurrency),
                  sumOfLiabilitiesMatchingCurrency))
              .build();

          liabilityEntries.add(newEntry);
        });

    log.debug(String.valueOf(liabilityEntries));

    return LiabilityReportTwo.builder()
        .liabilityEntryList(liabilityEntries).build();
  }
}
