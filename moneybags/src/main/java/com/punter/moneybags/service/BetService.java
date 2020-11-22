package com.punter.moneybags.service;

import static com.punter.moneybags.model.constant.CurrencyCode.currencyOfAlpha3IsoCode;
import static com.punter.moneybags.util.BetUtil.processRequestToReportOne;
import static com.punter.moneybags.util.BetUtil.processRequestToReportTwo;
import static java.util.Objects.isNull;

import com.punter.moneybags.model.dao.LiabilityEntry;
import com.punter.moneybags.model.dao.SelectionLiabilityReportOne;
import com.punter.moneybags.model.request.BetCollectionRequest;
import org.springframework.stereotype.Service;

@Service
public class BetService implements BetRepository {

  @Override
  public SelectionLiabilityReportOne calculateSelectionLiability(
      BetCollectionRequest betCollectionRequest) {
//    SelectionLiabilityReportOne selectionLiabilityReportOne;




    processRequestToReportOne(betCollectionRequest);
    processRequestToReportTwo(betCollectionRequest);

    return null;
  }

  @Override
  public LiabilityEntry calculateLiability(BetCollectionRequest betCollectionRequest) {
    return null;
  }

  private void printRequest(BetCollectionRequest betCollectionRequestToPrint) {
    System.out.println(betCollectionRequestToPrint.toString());
  }

}
