package com.rats.activiti.service;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class TaskClient {

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private TaskService taskService;

    @Resource
    private HistoryService  historyService;

    /**
     * 认领
     *
     * @param taskId
     */
    public void claim(String taskId, String userId) {
        taskService.claim(taskId, userId);
    }

    /**
     * 不认领
     * @param taskId
     */
    public void unclaim(String taskId) {
        taskService.unclaim(taskId);
    }

    /**
     * 委托
     *
     * @param taskId
     */
    public void delegate(String taskId, String userId) {
        taskService.delegateTask(taskId, userId);
    }
    /**
     * 审批通过
     *
     * @param taskId
     */
    public void commit(String taskId, Map<String, Object> variables) {

        if (variables == null) {
            variables = new HashMap<String, Object>();
        }
        taskService.complete(taskId, variables);
    }
    /**
     * 审批通过
     *
     * @param taskId
     */
    public void commit(String taskId, String activityId, Map<String, Object> variables) {

        if (variables == null) {
            variables = new HashMap<String, Object>();
        }
        // 跳转节点为空，默认提交操作
        if (activityId == null) {
            taskService.complete(taskId, variables);
        } else {// 流程转向操作
            turnTransition(taskId, activityId, variables);
        }
        taskService.complete(taskId, variables);
    }
    /**
     * 驳回流程
     *
     * @param taskId
     *            当前任务ID
     * @param activityId
     *            驳回节点ID
     * @param variables
     *            流程存储参数
     * @throws Exception
     */
    public void turndown(String taskId, String activityId, Map<String, Object> variables) throws Exception {

    }


    /**
     * 中止流程(特权人直接审批通过等)
     *
     * @param taskId
     */
    public void suspend(String taskId) throws Exception {

    }


    /**
     * 流程转向操作
     *
     * @param taskId
     *            当前任务ID
     * @param activityId
     *            目标节点任务ID
     * @param variables
     *            流程变量
     * @throws Exception
     */
    private void turnTransition(String taskId, String activityId,
                                Map<String, Object> variables)  {



        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        String processDefinitionId = task.getProcessDefinitionId();
        String processInstanceId = task.getProcessInstanceId();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);


        List<HistoricActivityInstance> historicActivityInstanceList = historyService.createHistoricActivityInstanceQuery()
                .processInstanceId(processInstanceId).orderByHistoricActivityInstanceId().asc().list();

        for (int i = 0; i < historicActivityInstanceList.size() - 1; i++) {
            // 当前节点
            FlowNode activityImpl = (FlowNode)bpmnModel.getMainProcess().getFlowElement(historicActivityInstanceList.get(i).getActivityId());


            // 执行转向任务
            taskService.complete(taskId, variables);

        }

    }

    public List<Task> queryTaskByPage(String deployId) {
        List<Task> tasks = taskService.createTaskQuery().listPage(1, 10);
        return tasks;
    }

    public Task queryTask(String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        return task;
    }

    public List<Task> queryTaskByUser(String userId) {
        List<Task> tasks = taskService.createTaskQuery().taskAssignee(userId).listPage(1, 10);
        return tasks;
    }
}
