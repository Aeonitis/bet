package com.punter.moneybags.model.dao;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
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

  @CsvBindByName(column = "Selection Name", required = true)
  @CsvBindByPosition(position = 0)
  private String selectionName;

  @CsvBindByName(column = "Currency", required = true)
  @CsvBindByPosition(position = 1)
  private String currency;

  @CsvBindByName(column = "Num Bets", required = true)
  @CsvBindByPosition(position = 2)
  private int totalBets;

  @CsvBindByName(column = "Total Stakes", required = true)
  @CsvBindByPosition(position = 3)
  private String totalStakes;

  @CsvBindByName(column = "Total Liability", required = true)
  @CsvBindByPosition(position = 4)
  private String totalLiability;

}
