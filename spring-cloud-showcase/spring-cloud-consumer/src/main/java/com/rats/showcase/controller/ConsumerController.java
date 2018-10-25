package com.rats.showcase.controller;

import com.rats.showcase.client.HelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    @Autowired
    private HelloClient helloClient;

    @GetMapping("/helloFeign")
    public  String getProductByFeign() {
        return helloClient.hello();
    }

}
