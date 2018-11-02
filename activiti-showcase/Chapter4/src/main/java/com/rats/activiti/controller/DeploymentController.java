package com.rats.activiti.controller;

import com.rats.activiti.service.DeploymentClient;
import com.rats.activiti.service.ImageClient;
import org.activiti.engine.impl.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
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


    @GetMapping("/deploy")
    public Map deploy() {
        return deploymentClient.deploy("bpmn/test.bpmn20.xml");
    }

    @GetMapping("/deploy/list")
    public Map deploylist() {
        return deploymentClient.queryProcessDefinitionAll();
    }

    @GetMapping("/deploy/{deployId}")
    public Map deploy(@PathVariable String deployId) {
        return deploymentClient.queryDeployment(deployId);
    }


    @GetMapping("/processDef/list")
    public Map processDefList() {
        Page page = new Page(0, 10);
        return deploymentClient.queryProcessDefinitionByPage(page);
    }

    @GetMapping("/processDef/{deployId}")
    public Map processDefClientQuery(@PathVariable String deployId) {
        return deploymentClient.queryProcessDefinitionByDeploymentId(deployId);
    }

}
