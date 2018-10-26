package com.rats.showcase.service;

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

@Service
public class DeployService {

    @Resource
    private RepositoryService repositoryService;

    /**
     * 部署流程
     *
     * @return
     */
    public Map deploy() {
        Deployment deployment = repositoryService.createDeployment().addClasspathResource("test/test.bpmn20.xml").deploy();
        return query(deployment.getId());
    }

    /**
     * 部署流程
     *
     * @return
     */
    public Map query(String deployId) {
        Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(deployId).singleResult();
        Map<String, Object> result = new HashMap<>();
        result.put("deployId", deployment.getId());
        result.put("name", deployment.getName());
        result.put("tenantId", deployment.getTenantId());
        result.put("deployTime", deployment.getDeploymentTime());
        return result;
    }

    /**
     * 部署流程
     *
     * @return
     */
    public Map queryPage(Page page) {
        List<Deployment> deployments = repositoryService.createDeploymentQuery().listPage(page.getFirstResult(),page.getMaxResults());
        List list = deployments.stream().map(deployment -> {
            Map<String, Object> obj = new HashMap<>();
            obj.put("deployId", deployment.getId());
            obj.put("name", deployment.getName());
            obj.put("tenantId", deployment.getTenantId());
            obj.put("deployTime", deployment.getDeploymentTime());
            return obj;
        }).collect(Collectors.toList());
        Map<String, Object> result = new HashMap<>();
        result.put("data", list);
        return result;
    }

    public Map queryyDeployRes() {
        //TODO
        return null;
    }

    /**
     * 删除流程定义
     *
     * @return
     */
    public Map delete(String deployId) {
        repositoryService.deleteDeployment(deployId);
        Map<String, Object> result = new HashMap<>();
        result.put("deployId", deployId);
        return result;
    }


}
