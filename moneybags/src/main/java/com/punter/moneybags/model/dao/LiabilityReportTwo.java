package com.punter.moneybags.model.dao;

import java.util.List;
import lombok.Builder;

/**
 * Report Two: Shows total liability by currency. Columns: Currency, Num Bets, Total Stakes, Total
 * Liability
 */
@Builder
public class LiabilityReportTwo {

  private List<LiabilityEntry> liabilityEntryList;
}
