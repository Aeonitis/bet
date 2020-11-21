package com.punter.moneybags.service;

import static com.punter.moneybags.util.BetUtil.convertRequestToReport;
import static com.punter.moneybags.util.BetUtil.countTotalMatchesForPredicate;

import com.punter.moneybags.model.dao.LiabilityEntry;
import com.punter.moneybags.model.dao.SelectionLiabilityReportOne;
import com.punter.moneybags.model.request.BetCollectionRequest;
import org.springframework.stereotype.Service;

@Service
public class BetService implements BetRepository {

  @Override
  public SelectionLiabilityReportOne calculateSelectionLiability(
      BetCollectionRequest betCollectionRequest) {
    printRequest(betCollectionRequest);
    SelectionLiabilityReportOne selectionLiabilityReportOne;

//    System.out.println(countTotalMatchesForPredicate(betCollectionRequest.getBets()));

//        betCollectionRequest.getBets().forEach(eachEntry -> {
//            SelectionLiabilityEntry.builder()
//                    .selectionName(eachEntry.getSelectionName())
//                    .currency(eachEntry.getCurrency())
//
////                    .totalBets(eachEntry.get)
////                    .totalStakes(eachEntry.get)
////                    .totalLiability(eachEntry.get)
//                    .build();
//                });

    convertRequestToReport(betCollectionRequest);
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
