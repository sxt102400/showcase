package com.shawn.controller;

import com.shawn.auth.bean.SysUser;
import com.shawn.auth.dao.SysUserDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/")
public class LoginController {

    @RequestMapping(value = "/login")
    public String hello() {
        return "login";
    }

    @RequestMapping(value = "/loginAjax")
    @ResponseBody
    public Map login() {
        Map result = new HashMap<>();
        result.put("isLogin",false);
        return result;
    }
}
