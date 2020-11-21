package com.punter.moneybags.model.dao;

import lombok.Builder;

import java.util.List;

/**
 * Report Two: Shows total liability by currency.
 * Columns: Currency, Num Bets, Total Stakes, Total Liability
 */
public class LiabilityReportTwo {

    private List<LiabilityEntry> liabilityEntryList;
}
