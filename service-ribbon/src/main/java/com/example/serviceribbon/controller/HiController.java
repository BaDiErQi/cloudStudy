package com.example.serviceribbon.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HiController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("hi")
    @HystrixCommand(fallbackMethod = "sayHiError")
    public String sayHi(@RequestParam("name") String name) {
        return restTemplate.getForObject("http://eureka-client/hi?name=" + name, String.class);
    }

    public String sayHiError(String name) {
        return "discovery error!";
    }
}
