package com.punter.moneybags.model.dao;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Columns: Selection Name, Currency, Num Bets, Total Stakes, Total Liability Order: By Currency and
 * Total Liability, descending.
 */
@Builder
@Getter
@ToString
public class SelectionLiabilityEntry {

  private String selectionName;
  private String currency;
  private int totalBets;
  private String totalStakes;
  private String totalLiability;

}
