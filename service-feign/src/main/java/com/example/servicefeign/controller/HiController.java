package com.example.servicefeign.controller;

import com.example.servicefeign.feign.HiFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {

    @Autowired
    private HiFeignClient hiFeignClient;

    @RequestMapping("hi")
    public String sayHi(@RequestParam("name") String name) {
        return hiFeignClient.sayHi(name);
    }
}
