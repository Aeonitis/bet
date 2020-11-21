package com.punter.moneybags.model.dao;

import lombok.Builder;

/**
 * Columns: Currency, Num Bets, Total Stakes, Total Liability
 */
@Builder
public class LiabilityEntry {

  private String currency;
  private int totalBets;
  private double totalStakes;
  private double totalLiability;
}
