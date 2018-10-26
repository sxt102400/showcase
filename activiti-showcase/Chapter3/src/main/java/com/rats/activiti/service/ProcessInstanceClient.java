package com.rats.activiti.service;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (C) 2016 rats
 * <p/>
 *
 * @author : rats
 * @version : v1.0
 * @since : 2018/10/25
 */
@Service
public class ProcessInstanceClient {

    @Resource
    private RuntimeService runtimeService;

    /**
     *  start
     */
    public Map startByKey(String processDefinitionKey) {
        ProcessInstance pi = runtimeService.startProcessInstanceByKey(processDefinitionKey);
        Map result = new HashMap<>();
        result.put("id",pi.getId());
        result.put("name",pi.getName());
        result.put("instanceId",pi.getProcessInstanceId());
        result.put("processDefinitionKey",pi.getProcessDefinitionKey());
        result.put("_processDefinitionId",pi.getProcessDefinitionId());
        result.put("_processDefinitionName",pi.getProcessDefinitionName());
        result.put("businessKey",pi.getBusinessKey());
        result.put("deploymentId",pi.getDeploymentId());
        result.put("description",pi.getDescription());
        result.put("startTime",pi.getStartTime());
        return result;
    }


    public ProcessInstance queryProcessInstance(String processInstanceId) {
        ProcessInstance ProcessInstance =
                runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        return ProcessInstance;
    }


}
