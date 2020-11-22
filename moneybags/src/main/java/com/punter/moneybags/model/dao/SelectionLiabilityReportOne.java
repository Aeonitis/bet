package com.punter.moneybags.model.dao;

import dnl.utils.text.table.TextTable;
import java.util.List;
import lombok.Builder;

/**
 * Report One: Shows selection liability by currency. Columns: Selection Name, Currency, Num Bets,
 * Total Stakes, Total Liability Order: By Currency and Total Liability, descending.
 */
@Builder
public class SelectionLiabilityReportOne {

  private List<SelectionLiabilityEntry> selectionLiabilityEntryList;

  private static final String[] columns = {
      "Selection Name",
      "Currency",
      "Num Bets",
      "Total Stakes",
      "Total Liability"
  };

  public void printReportToConsole() {
    Object[][] data = new Object[selectionLiabilityEntryList.size()][];
    for (int i = 0; i < selectionLiabilityEntryList.size(); i++) {
      SelectionLiabilityEntry selectionLiabilityEntry = selectionLiabilityEntryList.get(i);
      data[i] = new Object[columns.length];
      data[i][0] = selectionLiabilityEntry.getSelectionName();
      data[i][1] = selectionLiabilityEntry.getCurrency();
      data[i][2] = selectionLiabilityEntry.getTotalBets();
      data[i][3] = selectionLiabilityEntry.getTotalStakes();
      data[i][4] = selectionLiabilityEntry.getTotalLiability();
    }

    TextTable textTable = new TextTable(columns, data);
    textTable.printTable();
    System.out.println("-------------------");
    System.out.println("No. of entries: " + selectionLiabilityEntryList.size());
  }

}
