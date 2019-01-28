package com.example.servicefeign.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "eureka-client")
public interface HiFeignClient {

    @RequestMapping("hi")
    String sayHi(@RequestParam("name") String name);
}
