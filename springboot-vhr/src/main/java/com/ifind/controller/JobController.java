package com.ifind.controller;

import com.ifind.common.response.JsonResult;
import com.ifind.entity.Job;
import com.ifind.service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/job")
@RestController
@Slf4j
public class JobController {

    @Autowired
    private JobService jobService;

    @CrossOrigin
    @RequestMapping("/getData")
    @ResponseBody
    public JsonResult getData(){

        List<Job> jobs = jobService.getAllJob();

        return new JsonResult(jobs);
    }

    @CrossOrigin
    @RequestMapping("/getChina")
    @ResponseBody
    public JsonResult chinaShow() {
        return null;
    }
}
