package com.punter.moneybags.model.dao;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Columns: Currency, Num Bets, Total Stakes, Total Liability
 */
@Builder
@Getter
@ToString
public class LiabilityEntry {

  private String currency;
  private int totalBets;
  private double totalStakes;
  private double totalLiability;
}
