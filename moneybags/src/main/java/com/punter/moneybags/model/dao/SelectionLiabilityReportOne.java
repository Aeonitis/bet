package com.punter.moneybags.model.dao;

import java.util.List;
import lombok.Builder;

/**
 * Report One: Shows selection liability by currency. Columns: Selection Name, Currency, Num Bets,
 * Total Stakes, Total Liability Order: By Currency and Total Liability, descending.
 */

@Builder
public class SelectionLiabilityReportOne {

  private List<SelectionLiabilityEntry> selectionLiabilityEntryList;


}
