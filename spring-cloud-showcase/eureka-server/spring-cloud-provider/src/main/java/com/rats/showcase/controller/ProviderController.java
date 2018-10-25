package com.rats.showcase.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {

    @RequestMapping("/hello")
    String hello() {
        return "Hello World!";
    }
}

