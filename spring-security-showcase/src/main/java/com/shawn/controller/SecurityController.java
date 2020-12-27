package com.shawn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/")
public class SecurityController {

    @RequestMapping(value = "/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping(value = "/ajaxLogin")
    @ResponseBody
    public Map login() {
        Map result = new HashMap<>();
        result.put("isLogin",false);
        return result;
    }
}
