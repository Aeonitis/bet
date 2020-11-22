package com.punter.moneybags.model.dao;

import com.punter.moneybags.model.dto.LiabilityEntry;
import dnl.utils.text.table.TextTable;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

/**
 * Report Two: Shows total liability by currency. Columns: Currency, Num Bets, Total Stakes, Total
 * Liability
 */
@Builder
@Getter
public class LiabilityReportTwo {

  private List<LiabilityEntry> liabilityEntryList;

  private static final String[] TABLE_COLUMNS = {
      "Currency",
      "No Of Bets",
      "Total Stakes",
      "Total Liability"
  };

  public void printReportToConsole() {
    Object[][] data = new Object[liabilityEntryList.size()][];
    for (int i = 0; i < liabilityEntryList.size(); i++) {
      LiabilityEntry liabilityEntry = liabilityEntryList.get(i);
      data[i] = new Object[TABLE_COLUMNS.length];
      data[i][0] = liabilityEntry.getCurrency();
      data[i][1] = liabilityEntry.getTotalBets();
      data[i][2] = liabilityEntry.getTotalStakes();
      data[i][3] = liabilityEntry.getTotalLiability();
    }

    TextTable textTable = new TextTable(TABLE_COLUMNS, data);
    textTable.printTable();
    System.out.println("-------------------");
    System.out.println("No. of entries: " + liabilityEntryList.size());
  }
}
