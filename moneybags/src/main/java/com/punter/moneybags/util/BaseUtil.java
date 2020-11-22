package com.punter.moneybags.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BaseUtil {

  private Locale getLocaleFromISO(String iso4217code) {
    Locale toReturn = null;
    for (Locale locale : NumberFormat.getAvailableLocales()) {
      String code = NumberFormat.getCurrencyInstance(locale).
          getCurrency().getCurrencyCode();
      if (iso4217code.equals(code)) {
        toReturn = locale;
        break;
      }
    }
    return toReturn;
  }

  // TODO: Use getDefaultFractionDigits() for dynamic points per currency
  public static BigDecimal roundToTwoDecimalPoints(BigDecimal decimalToRound) {
    BigDecimal bigDecimal = decimalToRound.setScale(2, RoundingMode.HALF_UP);
    return bigDecimal;
  }

  /**
   * https://howtodoinjava.com/java8/java-stream-distinct-examples
   */
  public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
    Map<Object, Boolean> map = new ConcurrentHashMap<>();
    return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
  }

  public static String getCurrencySymbol(String currencyName) {
    return Currency.getInstance(currencyName.toUpperCase()).getSymbol(Locale.UK);
  }
}
