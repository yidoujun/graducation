package com.ifind.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ifind.common.constants.KeyConstants;
import com.ifind.common.constants.Status;
import com.ifind.common.exception.CustomException;
import com.ifind.common.response.JSONUtils;
import com.ifind.dao.JobDao;
import com.ifind.entity.Job;
import com.ifind.entity.JobShow;
import com.ifind.service.JobService;
import com.ifind.util.CommonUtil;
import com.ifind.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * jobServiceImpl
 *
 * @author Carlos
 * @date 2020/5/9 8:09
 */
@Service("jobService")
@Slf4j
public class JobServiceImpl implements JobService {

    @Autowired
    private JobDao jobDao;

    @Autowired
    private RedisUtil redisUtil;


    /**
     * 获取所有的岗位数据
     *
     * @return
     */
    @Override
    public List<Job> getAllJob() {
//        String userName = "ydj";
//        String key = KeyConstants.JOB_ALL_INFO + userName;
//        String rst = (String)redisUtil.get(key);
//        if (rst != null || "".equals(rst)) {
//            List<Job> job = JSONUtils.parseArray(rst, Job.class);
//            return job;
//        }
        List<Job> list = jobDao.getAllJob();
//        redisUtil.set(key, JSONUtils.toJsonString(list));
        return list;
    }

    /**
     * 中国地图展示各城市职位数量图
     *
     * @param isFlag                    redis缓存的key后缀
     * @return
     */
    @Override
    public List<JobShow> getCityAndNum(String isFlag) {
        String key = KeyConstants.JOB_INFO + 1;
        if (redisUtil.hasKey(key)) {
            return JSONUtils.parseArray((String)redisUtil.get(key), JobShow.class);
        }
        // 1.获取所有的岗位数据
        List<Job> allJobs = getAllJob();
        Map<String, Long> map = allJobs.stream().map(e -> {
                                            String address = e.getJobAddress();
                                            if (address.split("-").length > 1) {
                                                e.setJobAddress(address.split("-")[0]);
                                            }
                                            return e;
                                        })
                .collect(Collectors.groupingBy(Job::getJobAddress, Collectors.counting()));
        List<JobShow> list = map.entrySet().stream().map( e -> {
            JobShow jobShow = new JobShow();
            jobShow.setName(e.getKey());
            jobShow.setValues(e.getValue());
            return jobShow;
        }).collect(Collectors.toList());
        redisUtil.set(key, JSONUtils.toJsonString(list));
        return list;
    }

    /**
     * 柱状图展示个行业招聘职位数量关系图
     *
     * @param isFlag    redis缓存的key后缀
     * @return
     */
    @Override
    public List<JobShow> getIndustryAndPosition(String isFlag) {
        String key = KeyConstants.JOB_INFO + 2;
        if (redisUtil.hasKey(key)) {
            return JSONUtils.parseArray((String)redisUtil.get(key), JobShow.class);
        }
        // 1.获取所有的岗位数据
        List<Job> allJobs = getAllJob();
        Map<String, Long> map = allJobs.stream()
                .collect(Collectors.groupingBy(Job::getCompanyIndustry, Collectors.counting()));

        List<JobShow> list = map.entrySet().stream().map( e -> {
            JobShow jobShow = new JobShow();
            jobShow.setName(e.getKey());
            jobShow.setValues(e.getValue());
            return jobShow;
        }).collect(Collectors.toList());
        redisUtil.set(key, JSONUtils.toJsonString(list));
        return list;
    }

    /**
     * 柱状图展示学历和职位数量关系图
     *
     * @param isFlag    redis缓存的key后缀
     * @return
     */
    @Override
    public List<JobShow> getEducationAndPosition(String isFlag) {
        String key = KeyConstants.JOB_INFO + 3;
        if (redisUtil.hasKey(key)) {
            return JSONUtils.parseArray((String) redisUtil.get(key), JobShow.class);
        }
        // 1.获取所有的岗位数据
        List<Job> allJobs = getAllJob();
        Map<String, Long> map = allJobs.stream()
                .collect(Collectors.groupingBy(Job::getEducation, Collectors.counting()));

        List<JobShow> list = map.entrySet().stream().map( e -> {
            JobShow jobShow = new JobShow();
            jobShow.setName(e.getKey());
            jobShow.setValues(e.getValue());
            return jobShow;
        }).collect(Collectors.toList());
        redisUtil.set(key, JSONUtils.toJsonString(list));
        return list;
    }


}
