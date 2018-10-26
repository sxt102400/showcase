package com.rats.activiti.controller;

import com.rats.activiti.service.DeploymentClient;
import com.rats.activiti.service.ProcessInstanceClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Copyright (C) 2016 rats
 * <p/>
 *
 * @author : rats
 * @version : v1.0
 * @since : 2018/10/26
 */
@RestController
public class ProcessInstanceController {

    @Resource
    private ProcessInstanceClient processInstanceClient;

    @GetMapping("/processInstance/start/{processDefinitionKey}")
    public Map start(@PathVariable String processDefinitionKey){
        return processInstanceClient.startByKey(processDefinitionKey);
    }
}
