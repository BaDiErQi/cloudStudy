package com.example.eurekaclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {

    @Value("${server.port}")
    public String port;

    @RequestMapping("hi")
    public String sayHi(@RequestParam("name") String name) {
        return "hi, " + name + " this is port:"+ port;
    }
}
