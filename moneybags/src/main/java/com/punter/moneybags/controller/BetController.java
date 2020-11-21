package com.punter.moneybags.controller;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.punter.moneybags.model.Bet;
import com.punter.moneybags.model.request.BetCollectionRequest;
import com.punter.moneybags.service.BetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Objects;

import static com.punter.moneybags.util.BetUtil.convertCSVToBetCollectionRequest;
import static java.util.Objects.isNull;
import static org.springframework.http.MediaType.APPLICATION_JSON;

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

//    @PostMapping("/upload-csv-file")
////    @PostMapping(value = "/upload-csv-file", consumes = {MediaType.APPLICATION_JSON})
//    public String uploadCSVFile(@RequestParam("file") MultipartFile uploadedFile) {
//        BetCollectionRequest betCollectionRequest = convertCSVToBetCollectionRequest(uploadedFile);
//
//        if (!isNull(betCollectionRequest)) {
//            System.out.println("OH NO!OO");
//            betService.calculateLiability(betCollectionRequest);
//        } else {
//            System.out.println("WOO");
//        }
//
//        return "cv-uploaded";
//    }

    @PostMapping("/upload-csv-file")
//    @PostMapping(value = "/upload-csv-file", consumes = ({APPLICATION_JSON}))
    public String uploadJSONFile(@RequestBody BetCollectionRequest betCollectionRequest) {
        System.out.println(betCollectionRequest);
//        BetCollectionRequest betCollectionRequest = convertCSVToBetCollectionRequest(uploadedFile);



        return "json-upload-status";
    }

}