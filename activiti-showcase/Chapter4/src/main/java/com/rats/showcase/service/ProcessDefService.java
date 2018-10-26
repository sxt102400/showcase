package com.rats.showcase.service;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProcessDefService {


    @Resource
    private RepositoryService repositoryService;

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
        result.put("processDefId", processDefId);
        return result;
    }



    /**
     * 查询所有流程定义
     *
     * @return
     */
    public Map queryAll(int start,int size) {
        List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery().listPage(start, size);
        List<Map<String, Object>> list = processDefinitions.stream().map(processDef -> {
            Map<String, Object> obj = new HashMap<>();
            obj.put("deployId", processDef.getDeploymentId());
            obj.put("ProcessDefId", processDef.getId());
            obj.put("processDefKey ", processDef.getKey());
            obj.put("description", processDef.getDescription());
            obj.put("suspended", processDef.isSuspended());
            obj.put("diagram",processDef.getDiagramResourceName());
            return obj;
        }).collect(Collectors.toList());
        Map<String, Object> result = new HashMap<>();
        result.put("data", list);
        result.put("start", start);
        result.put("size", size);
        return result;
    }

    /**
     * 查询一条流程定义
     *
     * @return
     */
    public Map query(String deployId) {
        ProcessDefinition processDef = repositoryService.createProcessDefinitionQuery().deploymentId(deployId).singleResult();
        Map<String, Object> result = new HashMap<>();
        result.put("deployId", processDef.getDeploymentId());
        result.put("ProcessDefId", processDef.getId());
        result.put("processDefKey ", processDef.getKey());
        result.put("description", processDef.getDescription());
        result.put("suspended", processDef.isSuspended());
        result.put("diagram",processDef.getDiagramResourceName());
        return result;
    }


}
