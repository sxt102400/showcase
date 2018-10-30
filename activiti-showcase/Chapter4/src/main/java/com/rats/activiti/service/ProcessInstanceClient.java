package com.rats.activiti.service;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.Page;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
     * 开始执行流程实例
     *
     * @param processDefId
     * @param businessKey
     * @param variables
     */
    public Map startById(String processDefId, String businessKey, Map<String, Object> variables) {
        ProcessInstance processInstance =
                runtimeService.startProcessInstanceById(processDefId, businessKey, variables);
        Map<String, Object> result = new HashMap<>();
        result.put("processId", processInstance.getId());
        result.put("processName", processInstance.getName());
        result.put("deploymentId", processInstance.getDeploymentId());
        result.put("businessKey", processInstance.getBusinessKey());
        result.put("processDefId", processInstance.getProcessDefinitionId());
        result.put("processDefKey", processInstance.getProcessDefinitionKey());
        result.put("processDefName", processInstance.getProcessDefinitionName());
        result.put("startTime", processInstance.getStartTime());
        result.put("description", processInstance.getDescription());
        result.put("actId", processInstance.getActivityId());
        return result;
    }


    /**
     * 开始执行流程实例
     *
     * @param processDefKey
     * @param businessKey
     * @param variables
     */
    public Map startByKey(String processDefKey, String businessKey, Map<String, Object> variables) {
        ProcessInstance processInstance =
                runtimeService.startProcessInstanceByKey(processDefKey, businessKey, variables);
        Map<String, Object> result = new HashMap<>();
        result.put("processId", processInstance.getId());
        result.put("processName", processInstance.getName());
        result.put("deploymentId", processInstance.getDeploymentId());
        result.put("businessKey", processInstance.getBusinessKey());
        result.put("processDefId", processInstance.getProcessDefinitionId());
        result.put("processDefKey", processInstance.getProcessDefinitionKey());
        result.put("processDefName", processInstance.getProcessDefinitionName());
        result.put("startTime", processInstance.getStartTime());
        result.put("description", processInstance.getDescription());
        result.put("actId", processInstance.getActivityId());
        return result;
    }
    /**
     * 停止并删除执行流程实例
     *
     * @param processId
     * @param deleteReason
     * @return
     */
    public Map stop(String processId, String deleteReason) {
        runtimeService.deleteProcessInstance(processId, deleteReason);
        Map<String, Object> result = new HashMap<>();
        result.put("processId", processId);
        return result;
    }

    /**
     * 查询流程实例列表
     *
     * @return
     */
    public Map queryProcessByPage(Page page) {
        long total = runtimeService.createProcessInstanceQuery().count();
        List<ProcessInstance> processInstances =
                runtimeService.createProcessInstanceQuery().listPage(page.getFirstResult(),page.getMaxResults());
        List data = processInstances.stream().map(processInstance -> {
            Map<String,Object> map = new HashMap();
            map.put("processId", processInstance.getId());
            map.put("processName", processInstance.getName());
            map.put("deploymentId", processInstance.getDeploymentId());
            map.put("businessKey", processInstance.getBusinessKey());
            map.put("processDefId", processInstance.getProcessDefinitionId());
            map.put("processDefKey", processInstance.getProcessDefinitionKey());
            map.put("processDefName", processInstance.getProcessDefinitionName());
            map.put("startTime", processInstance.getStartTime());
            map.put("description", processInstance.getDescription());
            map.put("actId", processInstance.getActivityId());
            return map;
        }).collect(Collectors.toList());
        Map<String, Object> result = new HashMap<>();
        result.put("total", total);
        result.put("page", 1);
        result.put("data", data);
        return result;
    }

    /**
     * 查询流程实例列表
     *
     * @return
     */
    public Map queryProcessAll() {
        List<ProcessInstance> processInstances =
                runtimeService.createProcessInstanceQuery().list();
        List<Map<String, Object>> list = processInstances.stream().map(processInstance -> {
            Map<String,Object> map = new HashMap();
            map.put("processId", processInstance.getId());
            map.put("processName", processInstance.getName());
            map.put("deploymentId", processInstance.getDeploymentId());
            map.put("businessKey", processInstance.getBusinessKey());
            map.put("processDefId", processInstance.getProcessDefinitionId());
            map.put("processDefKey", processInstance.getProcessDefinitionKey());
            map.put("processDefName", processInstance.getProcessDefinitionName());
            map.put("startTime", processInstance.getStartTime());
            map.put("description", processInstance.getDescription());
            map.put("actId", processInstance.getActivityId());
            return map;
        }).collect(Collectors.toList());
        Map result = new HashMap();
        result.put("items", list);
        return result;
    }


    /**
     * 查询流程实例
     *
     * @return
     */
    public Map queryProcess(String processId) {
        ProcessInstance processInstance =
                runtimeService.createProcessInstanceQuery().processInstanceId(processId).singleResult();
        Map<String,Object> map = new HashMap();
        map.put("processId", processInstance.getId());
        map.put("processName", processInstance.getName());
        map.put("deploymentId", processInstance.getDeploymentId());
        map.put("businessKey", processInstance.getBusinessKey());
        map.put("processDefId", processInstance.getProcessDefinitionId());
        map.put("processDefKey", processInstance.getProcessDefinitionKey());
        map.put("processDefName", processInstance.getProcessDefinitionName());
        map.put("startTime", processInstance.getStartTime());
        map.put("description", processInstance.getDescription());
        map.put("actId", processInstance.getActivityId());
        return map;
    }

}
