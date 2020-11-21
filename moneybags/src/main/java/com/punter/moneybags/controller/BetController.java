package com.punter.moneybags.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BetController {

    @RequestMapping("/")
    public String getBetReports() {
        return "See Reports here!";
    }

}