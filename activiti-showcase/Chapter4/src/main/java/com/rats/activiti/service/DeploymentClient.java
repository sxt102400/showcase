package com.rats.activiti.service;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.Page;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
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
 * @since : 2018/10/30
 */
@Service
public class DeploymentClient {

    @Resource
    private RepositoryService repositoryService;

    /**
     * 部署流程
     *
     * @return
     */
    public Map deploy(String resName) {
        //根据bpmn文件部署流程
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource(resName)
                .deploy();
        return queryDeployment(deployment.getId());
    }

    /**
     * 查询部署流程
     *
     * @param page
     * @return
     */
    public Map queryDeploymentByPage(Page page) {
        long total = repositoryService.createDeploymentQuery().count();
        List<Deployment> deployments = repositoryService.createDeploymentQuery().listPage(page.getFirstResult(), page.getMaxResults());
        List data = deployments.stream().map(deployment -> {
            Map<String, Object> obj = new HashMap<>();
            obj.put("deployId", deployment.getId());
            obj.put("name", deployment.getName());
            obj.put("tenantId", deployment.getTenantId());
            obj.put("deployTime", deployment.getDeploymentTime());
            return obj;
        }).collect(Collectors.toList());
        Map<String, Object> result = new HashMap<>();
        result.put("total", total);
        result.put("page", 1);
        result.put("data", data);
        return result;
    }

    /**
     * 查询部署流程
     *
     * @param deployId
     * @return
     */
    public Map queryDeployment(String deployId) {
        Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(deployId).singleResult();
        Map<String, Object> result = new HashMap<>();
        result.put("deployId", deployment.getId());
        result.put("name", deployment.getName());
        result.put("tenantId", deployment.getTenantId());
        result.put("deployTime", deployment.getDeploymentTime());
        return result;
    }

    /**
     * 删除流程部署
     *
     * @return
     */
    public Map delete(String deployId) {
        repositoryService.deleteDeployment(deployId);
        Map<String, Object> result = new HashMap<>();
        result.put("deployId", deployId);
        return result;
    }


    /**
     * 暂停流程定义
     *
     * @return
     */
    public Map suspend(String processDefId) {
        repositoryService.suspendProcessDefinitionById(processDefId);
        Map<String, Object> result = new HashMap<>();
        result.put("processDefId", processDefId);
        return result;
    }

    /**
     * 恢复流程定义
     *
     * @return
     */
    public Map resume(String processDefId) {
        repositoryService.activateProcessDefinitionById(processDefId);
        Map<String, Object> result = new HashMap<>();
        result.put("processDefId", processDefId);
        return result;
    }

    /**
     * 查询流程定义
     *
     * @return
     */
    public Map queryProcessDefinitionByPage(Page page) {
        long total = repositoryService.createDeploymentQuery().count();
        List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery().listPage(page.getFirstResult(), page.getMaxResults());
        List<Map<String, Object>> data = processDefinitions.stream().map(processDef -> {
            Map<String, Object> map = new HashMap<>();
            map.put("processDefId", processDef.getId());
            map.put("processDefKey ", processDef.getKey());
            map.put("deployId", processDef.getDeploymentId());
            map.put("name", processDef.getName());
            map.put("description", processDef.getDescription());
            map.put("suspended", processDef.isSuspended());
            map.put("diagram", processDef.getDiagramResourceName());
            return map;
        }).collect(Collectors.toList());
        Map<String, Object> result = new HashMap<>();
        result.put("total", total);
        result.put("page", 1);
        result.put("data", data);
        return result;
    }

    /**
     * 根据deploymentId查询ProcessDefinition
     *
     * @param deploymentId
     * @return
     */
    public Map queryProcessDefinitionByDeploymentId(String deploymentId) {
        ProcessDefinition processDef = repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId).singleResult();
        Map<String, Object> result = new HashMap<>();
        result.put("deployId", processDef.getDeploymentId());
        result.put("ProcessDefId", processDef.getId());
        result.put("processDefKey ", processDef.getKey());
        result.put("description", processDef.getDescription());
        result.put("suspended", processDef.isSuspended());
        result.put("diagram", processDef.getDiagramResourceName());
        return result;
    }

    /**
     * 查询流程定义
     *
     * @return
     */
    public Map queryProcessDefinitionAll() {
        List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery().list();
        List<Map<String, Object>> data = processDefinitions.stream().map(processDef -> {
            Map<String, Object> map = new HashMap<>();
            map.put("processDefId", processDef.getId());
            map.put("processDefKey ", processDef.getKey());
            map.put("deployId", processDef.getDeploymentId());
            map.put("name", processDef.getName());
            map.put("description", processDef.getDescription());
            map.put("suspended", processDef.isSuspended());
            map.put("diagram", processDef.getDiagramResourceName());
            return map;
        }).collect(Collectors.toList());
        Map<String, Object> result = new HashMap<>();
        result.put("data", data);
        return result;
    }
    public BpmnModel getBpmnModel(String ProcessDefinitionId) {
        BpmnModel bm = repositoryService.getBpmnModel(ProcessDefinitionId);
        return bm;
    }
}
