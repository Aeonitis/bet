package com.punter.moneybags.util;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.punter.moneybags.model.Bet;
import com.punter.moneybags.model.request.BetCollectionRequest;
import lombok.experimental.UtilityClass;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@UtilityClass
public class BetUtil {

    public static BetCollectionRequest convertCSVToBetCollectionRequest(MultipartFile multipartFile) {
        BetCollectionRequest convertedBetCollectionRequest;

        // Validate multipartFile
        if (multipartFile.isEmpty()) {
            convertedBetCollectionRequest = null;
        } else {

            // Parse CSV multipartFile to create a list of `Bet` objects
            try (Reader reader = new BufferedReader(new InputStreamReader(multipartFile.getInputStream()))) {

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
        return BetCollectionRequest.builder().bets(betList).build();
    }
}
