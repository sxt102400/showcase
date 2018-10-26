package com.rats.activiti.service;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
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
@Service
public class DeploymentClient {

    @Resource
    private RepositoryService repositoryService;


    /**
     *  deploy
     */
    public void deploy() {
        //根据bpmn文件部署流程
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("TestProcess.bpmn")
                .deploy();
    }


    public Map queryDeploymentByPage() {
        long total = repositoryService.createDeploymentQuery().count();
        List<Deployment> deployments =
                repositoryService.createDeploymentQuery().listPage(1, 10);
        Map result = new HashMap<String,Object>();
        List data = new ArrayList();
        deployments.stream().forEach(item->{
            Map map = new HashMap<String,String>();
            map.put( "id",item.getId());
            map.put( "key",item.getKey());
            map.put( "name",item.getName());
            map.put( "deploymentTime",item.getDeploymentTime());
            data.add(map);
        });

        result.put("total",total);
        result.put("page",1);
        result.put("data",data);
        return result;
    }

    public Deployment queryDeployment(String deployId) {
        Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(deployId).singleResult();
        return deployment;
    }

    public Map  queryProcessDefinitionByPage() {
        long total = repositoryService.createDeploymentQuery().count();
        List<ProcessDefinition> processDefinitions =
                repositoryService.createProcessDefinitionQuery().listPage(1, 10);
        Map result = new HashMap<String,Object>();
        List data = new ArrayList();
        processDefinitions.stream().forEach(item->{
            Map map = new HashMap<String,String>();
            map.put( "id",item.getId());
            map.put( "key",item.getKey());
            map.put( "name",item.getName());
            map.put( "description",item.getDescription());
            data.add(map);
        });

        result.put("total",total);
        result.put("page",1);
        result.put("data",data);
        return result;
    }

    public ProcessDefinition queryProcessDefinition(String processDefinitionId) {
        ProcessDefinition processDefinition =
                repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
        return processDefinition;
    }


    public BpmnModel getBpmnModel(String ProcessDefinitionId){
        BpmnModel bm = repositoryService.getBpmnModel(ProcessDefinitionId);
    }



}
