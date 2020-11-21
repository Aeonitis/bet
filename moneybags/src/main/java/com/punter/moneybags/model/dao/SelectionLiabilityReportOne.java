package com.punter.moneybags.model.dao;

import lombok.Builder;

import java.util.List;

/**
 * Report One: Shows selection liability by currency.
 * Columns: Selection Name, Currency, Num Bets, Total Stakes, Total Liability
 * Order: By Currency and Total Liability, descending.
 */

@Builder
public class SelectionLiabilityReportOne {

    private List<SelectionLiabilityEntry> selectionLiabilityEntryList;


}
