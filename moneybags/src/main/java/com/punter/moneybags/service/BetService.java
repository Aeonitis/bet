package com.punter.moneybags.service;


import static com.punter.moneybags.util.BetUtil.processRequestToReportOne;
import static com.punter.moneybags.util.BetUtil.processRequestToReportTwo;
import static com.punter.moneybags.util.helper.csv.BeanToCsvWriter.writeReportOneToFile;
import static com.punter.moneybags.util.helper.csv.BeanToCsvWriter.writeReportTwoToFile;

import com.punter.moneybags.model.dao.LiabilityReportTwo;
import com.punter.moneybags.model.dao.SelectionLiabilityReportOne;
import com.punter.moneybags.model.request.BetCollectionRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BetService implements BetRepository {

  @Value("${app.output.csv:true}")
  private boolean writeOutputToCsv;


  @Override
  public SelectionLiabilityReportOne calculateSelectionLiability(
      BetCollectionRequest betCollectionRequest) {
    SelectionLiabilityReportOne selectionLiabilityReportOne;

    selectionLiabilityReportOne = processRequestToReportOne(betCollectionRequest);

    System.out.println("Printing Report One...");
    selectionLiabilityReportOne.printReportToConsole();

    if (writeOutputToCsv) {
      writeReportOneToFile(selectionLiabilityReportOne.getSelectionLiabilityEntryList());
    } else {
      System.out.println("no write");
    }

    return selectionLiabilityReportOne;
  }

  @Override
  public LiabilityReportTwo calculateLiability(BetCollectionRequest betCollectionRequest) {
    LiabilityReportTwo liabilityReportTwo;

    liabilityReportTwo = processRequestToReportTwo(betCollectionRequest);

    System.out.println("Printing Report Two...");
    liabilityReportTwo.printReportToConsole();

    if (writeOutputToCsv) {
      writeReportTwoToFile(liabilityReportTwo.getLiabilityEntryList());
    } else {
      System.out.println("no write");
    }

    return liabilityReportTwo;
  }

  private void printRequest(BetCollectionRequest betCollectionRequestToPrint) {
    System.out.println(betCollectionRequestToPrint.toString());
  }

}
