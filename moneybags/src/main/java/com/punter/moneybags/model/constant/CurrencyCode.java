package com.punter.moneybags.model.constant;

import java.math.BigDecimal;

/**
 * TODO: Future consideration https://stackoverflow.com/questions/10536899/how-to-set-customize-currency-in-java
 * no euro sign character in ISO 8859-1; it was introduced in ISO 8859-15
 */
public enum CurrencyCode {

  //  EUR("â‚¬"),
  //  GBP("Â£"),
  EUR("€"),
  USD("$"),
  GBP("£");

  private String symbol;

  CurrencyCode(String symbol) {
    this.symbol = symbol;
  }

  public String alpha3IsoCode() {
    return this.name();
  }

  public String getSymbol() {
    return symbol;
  }

  public static CurrencyCode currencyOfAlpha3IsoCode(String alpha3IsoCode) {
    if (alpha3IsoCode.equalsIgnoreCase(USD.name())) {
      return CurrencyCode.USD;
    } else if (alpha3IsoCode.equalsIgnoreCase(EUR.name())) {
      return CurrencyCode.EUR;
    } else if (alpha3IsoCode.equalsIgnoreCase(GBP.name())) {
      return CurrencyCode.GBP;
    } else {
      return null;
    }
  }

  public static String appendSymbolWithAmount(CurrencyCode currency, BigDecimal amount) {

    StringBuilder formattedAmount = new StringBuilder();
    String symbol = currency.getSymbol();
    formattedAmount.append(symbol);
    formattedAmount.append(amount.toPlainString());

    return formattedAmount.toString();
  }

}
