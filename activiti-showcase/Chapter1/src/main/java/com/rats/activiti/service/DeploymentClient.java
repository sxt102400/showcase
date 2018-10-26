package com.rats.activiti.service;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;

import javax.annotation.Resource;
import java.util.List;

/**
 * Copyright (C) 2016 rats
 * <p/>
 *
 * @author : rats
 * @version : v1.0
 * @since : 2018/10/25
 */
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


    public List<Deployment> queryDeploymentByPage(String deployId) {
        List<Deployment> deployments =
                repositoryService.createDeploymentQuery().listPage(1, 10);
        return deployments;
    }

    public Deployment queryDeployment(String deployId) {
        Deployment deployment =
                repositoryService.createDeploymentQuery().deploymentId(deployId).singleResult();
        return deployment;
    }

    public List<ProcessDefinition>  queryProcessDefinitionByPage() {
        List<ProcessDefinition> processDefinitions =
                repositoryService.createProcessDefinitionQuery().listPage(1, 10);
        return processDefinitions;
    }

    public ProcessDefinition queryProcessDefinition(String processDefinitionId) {
        ProcessDefinition processDefinition =
                repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
        return processDefinition;
    }


}
