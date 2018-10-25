package com.rats.showcase.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name="service-provider")
public interface  HelloClient {

    @GetMapping("/hello")
    String hello();
}
