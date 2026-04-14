package com.yubiniperez.kinalapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeViewController {

    @GetMapping ("/")
    public String index() {
        return "index";
    }
}
