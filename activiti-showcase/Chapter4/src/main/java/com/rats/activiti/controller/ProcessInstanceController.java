package com.rats.activiti.controller;

import com.rats.activiti.service.ImageClient;
import com.rats.activiti.service.ModelClient;
import com.rats.activiti.service.ProcessInstanceClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Copyright (C) 2016 rats
 * <p/>
 *
 * @author : rats
 * @version : v1.0
 * @since : 2018/10/26
 */
@RestController
public class ProcessInstanceController {

    @Resource
    private ProcessInstanceClient processInstanceClient;

    @Resource
    private ModelClient modelClient;

    @GetMapping("/process/startById/{processDefId}")
    public Map startById(@PathVariable String processDefId) {
        return processInstanceClient.startById(processDefId, null, null);
    }

    @GetMapping("/process/startByKey/{processDefKey}")
    public Map start(@PathVariable String processDefKey) {
        return processInstanceClient.startByKey(processDefKey, null, null);
    }

    @GetMapping("/process/stop/{processId}")
    public Map stop(@PathVariable String processId) {
        return processInstanceClient.stop(processId, "nothing");
    }

    @GetMapping("/process/list")
    public Map list() {
        return processInstanceClient.queryProcessAll();
    }

    @GetMapping("/resourceDiagram/{deployId}")
    public void resourceDiagram(HttpServletResponse response, @PathVariable String deployId){
        try {
            modelClient.writeResourceDiagram(deployId,response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @GetMapping("/processImg/{processId}")
    public void processImg(HttpServletResponse response, @PathVariable String processId){
        try {
            modelClient.writeProcessImg(processId,response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
