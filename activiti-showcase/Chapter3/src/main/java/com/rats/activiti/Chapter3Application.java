package com.rats.activiti;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Copyright (C) 2016 rats
 * <p/>
 *
 * @author : rats
 * @version : v1.0
 * @since : 2018/10/25
 */
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class Chapter3Application {
    public static void main(String[] args) {
        SpringApplication.run(Chapter3Application.class, args);
    }
}
