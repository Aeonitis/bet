package com.punter.moneybags.controller;

import static com.punter.moneybags.util.BetUtil.convertBetListToBetCollectionRequest;
import static com.punter.moneybags.util.BetUtil.convertCSVToBetCollectionRequest;
import static java.util.Objects.isNull;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.punter.moneybags.model.Bet;
import com.punter.moneybags.model.request.BetCollectionRequest;
import com.punter.moneybags.service.BetService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class BetController {

  @Autowired
  BetService betService;

  @RequestMapping("/")
  public String getBetReports() {
    return "See Reports here!";
  }

  @PostMapping("/post-csv")
  public String postCSV(@RequestParam("file") MultipartFile uploadedFile) {
    BetCollectionRequest betCollectionRequest = convertCSVToBetCollectionRequest(uploadedFile);

    if (!isNull(betCollectionRequest)) {
      System.out.println("WOO");
      betService.calculateSelectionLiability(betCollectionRequest);
      betService.calculateLiability(betCollectionRequest);
    } else {
      System.out.println("OH NO!OO. Bad Request!");
    }

    return "csv-upload";
  }

  @PostMapping(value = "/post-json", consumes = APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Bet>> postJSON(
      @Valid @RequestBody List<Bet> betCollectionRequestBody) {
    BetCollectionRequest betCollectionRequest = convertBetListToBetCollectionRequest(
        betCollectionRequestBody);

    betService.calculateSelectionLiability(betCollectionRequest);
    betService.calculateLiability(betCollectionRequest);

    return ResponseEntity.ok(betCollectionRequestBody);
  }
}