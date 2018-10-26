package com.rats.activiti.controller;

import com.alibaba.fastjson.JSON;
import com.rats.activiti.service.DeploymentClient;
import org.activiti.engine.repository.Deployment;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C) 2016 rats
 * <p/>
 *
 * @author : rats
 * @version : v1.0
 * @since : 2018/10/25
 */
@RestController
public class DeploymentController {

    @Resource
    private DeploymentClient deploymentClient;

    @GetMapping("/deployment/list")
    public Map list(){
        return deploymentClient.queryDeploymentByPage();
    }


    @GetMapping("/deployment/deploy")
    public void deploy(){
         deploymentClient.deploy();
    }

    @GetMapping("/processDefinition/list")
    public Map processDefinitionList(){
       return deploymentClient.queryProcessDefinitionByPage();
    }
}
