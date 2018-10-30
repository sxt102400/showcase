package com.rats.activiti.controller;

import com.rats.activiti.service.*;
import org.activiti.engine.impl.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestController
public class TaskController {


    @Resource
    private DeploymentClient deploymentClient;

    @Resource
    private ProcessInstanceClient processInstanceClient;

    @Resource
    private ModelClient modelClient;

    @Resource
    private TaskClient taskService;

    @GetMapping("hello")
    public String hello() {
        return "hello";
    }


    @RequestMapping("/pass/{taskId}/{userId}")
    public void pass(@PathVariable String taskId, @PathVariable String userId) {

    }

    @RequestMapping("/reject/{taskId}/{userId}")
    public void reject(@PathVariable String taskId, @PathVariable String userId) throws IOException {

    }

}
