package com.punter.moneybags.controller;

import com.punter.moneybags.model.Bet;
import com.punter.moneybags.model.request.BetCollectionRequest;
import com.punter.moneybags.service.BetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.punter.moneybags.util.BetUtil.convertCSVToBetCollectionRequest;
import static com.punter.moneybags.util.BetUtil.convertBetListToBetCollectionRequest;
import static java.util.Objects.isNull;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class BetController {

    @Autowired
    BetService betService;

    @RequestMapping("/")
    public String getBetReports() {
        return "See Reports here!";
    }

    @PostMapping("/post-csv")
//    @PostMapping(value = "/upload-csv-file", consumes = {MediaType.APPLICATION_JSON})
    public String postCSV(@RequestParam("file") MultipartFile uploadedFile) {
        BetCollectionRequest betCollectionRequest = convertCSVToBetCollectionRequest(uploadedFile);

        if (!isNull(betCollectionRequest)) {
            System.out.println("WOO");
            betService.calculateSelectionLiability(betCollectionRequest);
        } else {
            System.out.println("OH NO!OO. Bad Request!");
        }

        return "csv-upload";
    }

    @PostMapping(value = "/post-json", consumes = APPLICATION_JSON_VALUE)
    public String postJSON(@RequestBody List<Bet> betCollectionRequestBody) {
        BetCollectionRequest betCollectionRequest = convertBetListToBetCollectionRequest(betCollectionRequestBody);

        betService.calculateSelectionLiability(betCollectionRequest);

        return "json-post";
    }

}