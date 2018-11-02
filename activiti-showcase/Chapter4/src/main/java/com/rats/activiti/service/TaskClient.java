package com.rats.activiti.service;


import com.rats.activiti.utils.DeleteTaskCmd;
import com.rats.activiti.utils.SetFLowNodeAndGoCmd;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.Page;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class TaskClient {

    @Resource
    private TaskService taskService;

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private HistoryService historyService;

    @Resource
    private ManagementService managementService;
    /**
     * 查询当用户下所有任务
     *
     * @param assignee
     * @param page
     * @return
     */
    public Map queryTaskByUser(String assignee, Page page) {
        long count = taskService.createTaskQuery().taskCandidateOrAssigned(assignee).count();
        List<Task> tasks = taskService.createTaskQuery().taskCandidateOrAssigned(assignee).listPage(page.getFirstResult(), page.getMaxResults());
        Map result = new HashMap();
        result.put("data", tasks);
        result.put("page", 1);
        result.put("data", tasks);
        return result;
    }

    /**
     * 查询当前角色下所有任务
     *
     * @param candidateGroup
     * @param page
     * @return
     */
    public Map queryTaskByGroup(String candidateGroup, Page page) {
        long total = taskService.createTaskQuery().taskCandidateGroup(candidateGroup).count();
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup(candidateGroup).listPage(page.getFirstResult(), page.getMaxResults());
        Map result = new HashMap();
        result.put("total", total);
        result.put("page", 1);
        result.put("data", tasks);
        return result;
    }

    /**
     * 查询当前角色下所有任务
     *
     * @param userId
     * @return
     */
    public Map queryTaskAllByUser(String userId) {
        List<Task> tasks = taskService.createTaskQuery().taskCandidateOrAssigned(userId).list();
        Map map = new HashMap();
        map.put("data", tasks);
        return map;
    }

    /**
     * 根据taskId查询任务
     * @param taskId
     * @return
     */
    public Map queryTask(String taskId) {
        List<Task> tasks = taskService.createTaskQuery().taskId(taskId).list();
        Map map = new HashMap();
        map.put("data", tasks);
        return map;
    }

    /**
     * 签收任务
     *
     * @param taskId
     * @param userId
     */
    public void claimTask(String taskId, String userId) {
        taskService.claim(taskId, userId);
    }

    /**
     * 拒签任务
     *
     * @param taskId
     */
    public void unclaim(String taskId) {
        taskService.unclaim(taskId);
    }

    /**
     * 委托任务
     *
     * @param taskId
     */
    public void delegateTask(String taskId, String userId) {
        taskService.delegateTask(taskId, userId);
    }

    /**
     * 分配任务
     *
     * @param taskId
     * @param userId
     */
    public void assigneeTask(String taskId, String userId) {
        taskService.setAssignee(taskId, userId);
    }

    /**
     * 提交任务
     *
     * @param taskId
     * @param variables
     */
    public void commitTask(String taskId, Map<String, Object> variables) {
        taskService.complete(taskId, variables);
    }


    /**
     * 提交任务,设置提交后的节点
     *
     * @param taskId
     * @param variables
     */
    public void commitTask(String taskId, String activityId, Map<String, Object> variables) {
        if (StringUtils.isEmpty(activityId)) {
            // 跳转节点为空，默认提交操作
            taskService.complete(taskId, variables);
        }
        else {
            // 流程转向操作
            turnTransition(taskId, activityId, variables);
        }
    }

    /**
     * 审批通过任务
     *
     * @param taskId
     * @param variables
     */
    public void passTask(String taskId, Map<String, Object> variables) {
        taskService.complete(taskId, variables);
    }


    /**
     * 回退任务
     *
     * @param historyTaskId
     */
    public void turnBackTask(String historyTaskId , Map<String, Object> variables ) {
        return;
    }

    public void turnTransition(String taskId,String activityId,Map variables){
        //当前任务
        Task currentTask = taskService.createTaskQuery().taskId(taskId).singleResult();
        //获取流程定义
        BpmnModel bpmnModel = repositoryService.getBpmnModel(currentTask.getProcessDefinitionId());
        //获取目标节点定义
        FlowNode targetNode = (FlowNode)bpmnModel.getMainProcess().getFlowElement(activityId);
        //删除当前运行任务
        String executionEntityId = managementService.executeCommand(new DeleteTaskCmd(currentTask.getId()));
        //流程执行到来源节点
        managementService.executeCommand(new SetFLowNodeAndGoCmd(targetNode, executionEntityId));
    }

}
