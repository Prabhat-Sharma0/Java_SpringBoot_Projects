package com.restApi.BasicRestApplication.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/info")
public class InfoController {

    @GetMapping("/message")
    public String getMessage() {
        return "This is the message!!!!";
    }

}
