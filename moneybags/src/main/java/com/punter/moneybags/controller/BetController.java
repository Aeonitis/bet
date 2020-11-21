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

//    @PostMapping(value = "/upload", consumes = {"text/csv", "application/json"})
//    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
//        String message = "";
//
//        if (CSVHelper.hasCSVFormat(file)) {
//            try {
//                fileService.save(file);
//
//                message = "Uploaded the file successfully: " + file.getOriginalFilename();
//                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
//            } catch (Exception e) {
//                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
//                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
//            }
//        }
//
//        message = "Please upload a csv file!";
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
//    }

    @PostMapping("/post-csv")
//    @PostMapping(value = "/upload-csv-file", consumes = {MediaType.APPLICATION_JSON})
    public String postCSV(@RequestParam("file") MultipartFile uploadedFile) {
        BetCollectionRequest betCollectionRequest = convertCSVToBetCollectionRequest(uploadedFile);

        if (!isNull(betCollectionRequest)) {
            System.out.println("WOO");
            betService.calculateLiability(betCollectionRequest);
        } else {
            System.out.println("OH NO!OO. Bad Request!");
        }

        return "cv-upload";
    }

    @PostMapping(value = "/post-json", consumes = APPLICATION_JSON_VALUE)
    public String postJSON(@RequestBody List<Bet> betCollectionRequestBody) {
        BetCollectionRequest betCollectionRequest = convertBetListToBetCollectionRequest(betCollectionRequestBody);

        betService.calculateLiability(betCollectionRequest);

        return "json-post";
    }

}