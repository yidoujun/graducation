package com.ifind.controller;

import com.ifind.common.response.JsonResult;
import com.ifind.entity.Job;
import com.ifind.entity.JobShow;
import com.ifind.service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 中国地图展示各城市职位数量图
     *
     * @param isFlag    redis缓存的key后缀
     * @return
     */
    @CrossOrigin
    @RequestMapping("/getChina")
    @ResponseBody
    public JsonResult chinaCityAndNum(@RequestBody String isFlag){
        List<JobShow> list = jobService.getCityAndNum(isFlag);
        return new JsonResult(list);
    }

    /**
     * 柱状图展示个行业招聘职位数量关系图
     *
     * @param isFlag    redis缓存的key后缀
     * @return
     */
    @CrossOrigin
    @RequestMapping("/getIndustry")
    @ResponseBody
    public JsonResult industry(@RequestBody String isFlag) {
        List<JobShow> list = jobService.getIndustryAndPosition(isFlag);
        return new JsonResult(list);
    }

    /**
     * 柱状图展示学历和职位数量关系图
     *
     * @param isFlag    redis缓存的key后缀
     * @return
     */
    @CrossOrigin
    @RequestMapping("/getEducation")
    @ResponseBody
    public JsonResult education(String isFlag) {
        List<JobShow> list = jobService.getEducationAndPosition(isFlag);
        return new JsonResult(list);
    }

    public JsonResult cityLevel(String isFlag) {
//        List<JobShow> list = jobService.
        return null;
    }
}
