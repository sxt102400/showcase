package com.rats.showcase.service;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProcessInstanceService {

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private RuntimeService runtimeService;

    /**
     * 开始执行流程实例
     *
     * @param processDefId
     * @param businessKey
     * @param variables
     */
    public Map start(String processDefId, String businessKey, Map<String, Object> variables) {
        ProcessInstance processInstance =
                this.runtimeService.startProcessInstanceById(processDefId, businessKey, variables);
        Map<String, Object> result = new HashMap<>();
        result.put("processId", processInstance.getId());
        result.put("deployId", processInstance.getDeploymentId());
        result.put("businessKey", processInstance.getBusinessKey());
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
        this.runtimeService.deleteProcessInstance(processId, deleteReason);
        Map<String, Object> result = new HashMap<>();
        result.put("processId", processId);
        return result;
    }

    /**
     * 查询流程实例列表
     *
     * @return
     */
    public Map queryAllByPage(int start, int limit) {
        List<ProcessInstance> processInstances =
                this.runtimeService.createProcessInstanceQuery().listPage(start, limit);
        List list = processInstances.stream().map(processInstance -> {
            Map<String,Object> obj = new HashMap();
            obj.put("processId", processInstance.getId());
            obj.put("deployId", processInstance.getDeploymentId());
            obj.put("businessKey", processInstance.getBusinessKey());
            obj.put("processDefKey", processInstance.getProcessDefinitionKey());
            obj.put("processDefName", processInstance.getProcessDefinitionName());
            obj.put("startTime", processInstance.getStartTime());
            obj.put("description", processInstance.getDescription());
            obj.put("actId", processInstance.getActivityId());
            return obj;
        }).collect(Collectors.toList());
        Map result = new HashMap();
        result.put("items", list);
        return result;
    }

    /**
     * 查询流程实例列表
     *
     * @return
     */
    public Map queryAll() {
        List<ProcessInstance> processInstances =
                this.runtimeService.createProcessInstanceQuery().list();
        List<Map<String, Object>> list = processInstances.stream().map(processInstance -> {
            Map<String,Object> obj = new HashMap();
            obj.put("processId", processInstance.getId());
            obj.put("deployId", processInstance.getDeploymentId());
            obj.put("businessKey", processInstance.getBusinessKey());
            obj.put("processDefKey", processInstance.getProcessDefinitionKey());
            obj.put("processDefName", processInstance.getProcessDefinitionName());
            obj.put("startTime", processInstance.getStartTime());
            obj.put("description", processInstance.getDescription());
            obj.put("actId", processInstance.getActivityId());
            return obj;
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
    public Map query(String processId) {
        ProcessInstance processInstance =
                this.runtimeService.createProcessInstanceQuery().processInstanceId(processId).singleResult();
        Map<String, Object> obj = new HashMap<>();
        obj.put("processId", processInstance.getId());
        obj.put("deployId", processInstance.getDeploymentId());
        obj.put("businessKey", processInstance.getBusinessKey());
        obj.put("processDefKey", processInstance.getProcessDefinitionKey());
        obj.put("processDefName", processInstance.getProcessDefinitionName());
        obj.put("startTime", processInstance.getStartTime());
        obj.put("description", processInstance.getDescription());
        obj.put("actId", processInstance.getActivityId());
        return obj;
    }
}
