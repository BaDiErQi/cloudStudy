package com.example.servicefeign.feign.fallback;

import com.example.servicefeign.feign.HiFeignClient;
import org.springframework.stereotype.Component;

@Component
public class HiFeignClientFallback implements HiFeignClient {
    @Override
    public String sayHi(String name) {
        return "sorry,i am feign,this is some error occur:"+name;
    }
}
