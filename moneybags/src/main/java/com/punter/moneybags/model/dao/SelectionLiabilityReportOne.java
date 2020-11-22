package com.punter.moneybags.model.dao;

import com.punter.moneybags.model.dto.SelectionLiabilityEntry;
import dnl.utils.text.table.TextTable;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

/**
 * Report One: Shows selection liability by currency. Columns: Selection Name, Currency, Num Bets,
 * Total Stakes, Total Liability Order: By Currency and Total Liability, descending.
 */
@Builder
@Getter
public class SelectionLiabilityReportOne {

  private List<SelectionLiabilityEntry> selectionLiabilityEntryList;

  private static final String[] TABLE_COLUMNS = {
      "Selection Name",
      "Currency",
      "Num Bets",
      "Total Stakes",
      "Total Liability"
  };

  /**
   * Console has encoding issues to resolve, but work if using the following values below
   * EUR("â‚¬"), GBP("Â£"), in CurrencyCode class. I didn't use them coz Csv write works fine with
   * this for now Current symbol in console: �
   */
  public void printReportToConsole() {
    Object[][] data = new Object[selectionLiabilityEntryList.size()][];
    for (int i = 0; i < selectionLiabilityEntryList.size(); i++) {
      SelectionLiabilityEntry selectionLiabilityEntry = selectionLiabilityEntryList.get(i);
      data[i] = new Object[TABLE_COLUMNS.length];
      data[i][0] = selectionLiabilityEntry.getSelectionName();
      data[i][1] = selectionLiabilityEntry.getCurrency();
      data[i][2] = selectionLiabilityEntry.getTotalBets();
      data[i][3] = selectionLiabilityEntry.getTotalStakes();
      data[i][4] = selectionLiabilityEntry.getTotalLiability();
    }

    TextTable textTable = new TextTable(TABLE_COLUMNS, data);
    textTable.printTable();
    System.out.println("-------------------");
    System.out.println("No. of entries: " + selectionLiabilityEntryList.size());
  }

}
