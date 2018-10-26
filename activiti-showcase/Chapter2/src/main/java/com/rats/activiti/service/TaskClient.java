package com.rats.activiti.service;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
     * 完成
     *
     * @param taskId
     */
    public void complete(String taskId, Map<String, Object> paramMap) {
        taskService.complete(taskId, paramMap);
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
