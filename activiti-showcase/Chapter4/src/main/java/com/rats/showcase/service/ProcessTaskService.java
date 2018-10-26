package com.rats.showcase.service;


import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class ProcessTaskService {

    @Resource
    private TaskService taskService;


    public Map findAllTaskPageByGroup(String candidateGroup, int start, int limit) {
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup(candidateGroup).listPage(start, limit);
        Map map = new HashMap();
        map.put("data", tasks);
        return map;
    }

    public Map findAllTaskByGroup(String candidateGroup) {
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup(candidateGroup).list();
        Map map = new HashMap();
        map.put("data", tasks);
        return map;
    }

    public Map findAllTaskPageByAssignee(String assignee, int start, int size) {
        List<Task> tasks = taskService.createTaskQuery().taskAssignee(assignee).listPage(start, size);
        Map map = new HashMap();
        map.put("data", tasks);
        map.put("start", start);
        map.put("size", size);
        return map;
    }

    public Map findAllTaskByAssignee(String assignee) {
        List<Task> tasks = taskService.createTaskQuery().taskAssignee(assignee).list();
        Map map = new HashMap();
        map.put("data", tasks);
        return map;
    }

    public Map findTask(String taskId) {
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
     * 签收任务
     *
     * @param taskId
     */
    public void unclaimTask(String taskId) {
        taskService.unclaim(taskId);
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

    public void complateTask(String taskid,String destTaskKey){
        //TODO
    }
    public void complateTask(String taskid,String destTaskKey,Map<String,Object> vars){
        //TODO

    }

    /**
     * 委托任务
     *
     * @param taskId
     * @param userId
     */
    public void assigneeTask(String taskId, String userId) {
        taskService.setAssignee(taskId, userId);
    }

    /**
     * 回退任务
     *
     * @param historyTaskId
     */
    public void backTask(String historyTaskId) {

    }


}
