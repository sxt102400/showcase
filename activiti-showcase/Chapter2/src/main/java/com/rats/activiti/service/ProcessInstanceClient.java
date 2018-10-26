package com.rats.activiti.service;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
    public void start(String processInstanceId) {
        ProcessInstance pi = runtimeService.startProcessInstanceById(processInstanceId);
    }


    public ProcessInstance queryProcessInstance(String processInstanceId) {
        ProcessInstance ProcessInstance =
                runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        return ProcessInstance;
    }


}
