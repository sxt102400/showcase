package com.rats.showcase.controller;

import com.rats.showcase.service.*;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestController
public class HelloController {

    @Autowired
    private ProcessInstanceService processInstanceService;

    @Autowired
    private ProcessDefService processDefClient;

    @Autowired
    private DeployService dployClient;

    @Autowired
    private ModelService modelClient;

    @Autowired
    private ProcessTaskService taskService;

    @GetMapping("hello")
    public String hello() {
        return "hello";
    }


    @GetMapping("/deploy")
    public Map deploy() {
        return dployClient.deploy();
    }

    @GetMapping("/deploy/{deployId}")
    public Map deploy(@PathVariable String deployId) {
        return dployClient.query(deployId);
    }

    @GetMapping("/processDef/list")
    public Map processDefList() {
        return processDefClient.queryAll(0,10);
    }

    @GetMapping("/processDef/{deployId}")
    public Map processDefClientQuery(@PathVariable String deployId) {
        return processDefClient.query(deployId);
    }

    @GetMapping("/process/list")
    public Map list() {
        return processInstanceService.queryAll();
    }

    @GetMapping("/process/start")
    public Map start(@RequestParam("processDefId") String processDefId, @RequestParam("businessKey") String businessKey) {
        return processInstanceService.start(processDefId, businessKey, null);
    }

    @GetMapping("/process/stop/{processId}")
    public Map stop(@PathVariable String processId) {
        return processInstanceService.stop(processId, "nothing");
    }



    @RequestMapping("/pass/{taskId}/{userId}")
    public void pass(@PathVariable String taskId,@PathVariable   String userId) {

    }

    @RequestMapping("/reject/{taskId}/{userId}")
    public void reject(@PathVariable String taskId,@PathVariable   String userId) throws IOException {

    }

    @RequestMapping("/processImg/{processId}")
    public void getProcessImg(@PathVariable String processId, HttpServletResponse resp) throws IOException {
        modelClient.getProcessImg(processId,resp.getOutputStream());
    }
}
