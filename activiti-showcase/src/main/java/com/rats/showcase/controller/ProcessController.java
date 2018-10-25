package com.rats.showcase.controller;

import com.rats.showcase.service.DeployService;
import com.rats.showcase.service.ModelService;
import com.rats.showcase.service.ProcessDefService;
import com.rats.showcase.service.ProcessInstanceService;
import com.rats.showcase.utils.PageHelper;
import org.activiti.engine.impl.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProcessController {


    @Resource
    private ProcessInstanceService processInstanceService;

    @Resource
    private ProcessDefService processDefClient;

    @Resource
    private DeployService deployService;

    @Resource
    private ModelService modelClient;

    @GetMapping("/deploy/list")
    public Map deploy(HttpServletRequest request) {
        Page page = PageHelper.getPage(request);
        return deployService.queryPage(page);
    }

}
