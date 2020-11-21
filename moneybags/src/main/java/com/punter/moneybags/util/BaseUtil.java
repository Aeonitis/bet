package com.punter.moneybags.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BaseUtil {

  public static double roundToTwoDecimalPoints(double doubleToRound) {
    DecimalFormat decimalFormat = new DecimalFormat("#.##");
    BigDecimal bd = new BigDecimal(doubleToRound).setScale(2, RoundingMode.HALF_UP);
    System.out.println(bd);
    return bd.doubleValue();
//    return Double.parseDouble(decimalFormat.format(doubleToRound));
  }

  /**
   * https://howtodoinjava.com/java8/java-stream-distinct-examples
   */
  public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
    Map<Object, Boolean> map = new ConcurrentHashMap<>();
    return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
  }
}
