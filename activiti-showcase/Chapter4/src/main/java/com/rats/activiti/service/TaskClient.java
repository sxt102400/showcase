package com.rats.activiti.service;


import org.activiti.engine.TaskService;
import org.activiti.engine.impl.Page;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class TaskClient {

    @Resource
    private TaskService taskService;


    /**
     * 查询当用户下所有任务
     *
     * @param assignee
     * @param page
     * @return
     */
    public Map queryTaskByUser(String assignee, Page page) {
        long count = taskService.createTaskQuery().taskAssignee(assignee).count();
        List<Task> tasks = taskService.createTaskQuery().taskAssignee(assignee).listPage(page.getFirstResult(), page.getMaxResults());
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
    public Map queryTaskByGroupAll(String userId) {
        List<Task> tasks = taskService.createTaskQuery().taskAssignee(userId).list();
        Map map = new HashMap();
        map.put("data", tasks);
        return map;
    }

    /**
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
    public void completeTask(String taskId, Map<String, Object> variables) {
        taskService.complete(taskId, variables);
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
     * 审批驳回任务
     *
     * @param taskId
     * @param variables
     */
    public void rejectTask(String taskId, Map<String, Object> variables) {

    }


    /**
     * 回退任务
     *
     * @param historyTaskId
     */
    public void turnBackTask(String historyTaskId) {

    }


}
