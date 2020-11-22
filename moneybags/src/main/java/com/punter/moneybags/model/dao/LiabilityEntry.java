package com.punter.moneybags.model.dao;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
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

  @CsvBindByName(column = "Currency", required = true)
  @CsvBindByPosition(position = 0)
  private String currency;

  @CsvBindByName(column = "No Of Bets", required = true)
  @CsvBindByPosition(position = 1)
  private int totalBets;

  @CsvBindByName(column = "Total Stakes", required = true)
  @CsvBindByPosition(position = 2)
  private String totalStakes;

  @CsvBindByName(column = "Total Liability", required = true)
  @CsvBindByPosition(position = 3)
  private String totalLiability;
}
