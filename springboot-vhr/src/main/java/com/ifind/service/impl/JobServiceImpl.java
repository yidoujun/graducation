package com.ifind.service.impl;


import com.ifind.common.Common;
import com.ifind.common.constants.KeyConstants;
import com.ifind.common.constants.Status;
import com.ifind.common.response.JSONUtils;
import com.ifind.dao.JobDao;
import com.ifind.entity.Job;
import com.ifind.entity.JobShow;
import com.ifind.service.JobService;
import com.ifind.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
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
     * @param sort             排序规则
     * @return
     */
    public List<Job> getAllJob(String sort) {
//        String userName = "ydj";
//        String key = KeyConstants.JOB_ALL_INFO + userName;
//        String rst = (String)redisUtil.get(key);
//        if (rst != null || "".equals(rst)) {
//            List<Job> job = JSONUtils.parseArray(rst, Job.class);
//            return job;
//        }
        List<Job> list = jobDao.getAllJob(sort);
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
        String key = KeyConstants.JOB_INFO + isFlag;
        if (redisUtil.hasKey(key)) {
            return JSONUtils.parseArray((String)redisUtil.get(key), JobShow.class);
        }
        // 1.获取所有的岗位数据
        List<Job> allJobs = getAllJob(Common.SORT_1);
        Map<String, Long> map = allJobs.stream().map(e -> {
                                            String address = e.getJobAddress();
                                            if (address.split("-").length > 1) {
                                                e.setJobAddress(address.split("-")[0]);
                                            }
                                            return e;
                                        })
                .collect(Collectors.groupingBy(Job::getJobAddress, Collectors.counting()));
        List<JobShow> list = map.entrySet().stream()
                .map( e -> {
                    JobShow jobShow = new JobShow();
                    jobShow.setName(e.getKey());
                    jobShow.setValues(e.getValue());
                    return jobShow;
                }).collect(Collectors.toList()).stream().sorted(Comparator.comparing(JobShow::getValues).reversed()).collect(Collectors.toList());
        redisUtil.set(key, JSONUtils.toJsonString(list), Status.ExpireEnum.PICTURE_DATA.getTime());
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
        String key = KeyConstants.JOB_INFO + isFlag;
        if (redisUtil.hasKey(key)) {
            return JSONUtils.parseArray((String)redisUtil.get(key), JobShow.class);
        }
        // 1.获取所有的岗位数据
        List<Job> allJobs = getAllJob(Common.SORT_1);
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
        String key = KeyConstants.JOB_INFO + isFlag;
        if (redisUtil.hasKey(key)) {
            return JSONUtils.parseArray((String) redisUtil.get(key), JobShow.class);
        }
        // 1.获取所有的岗位数据
        List<Job> allJobs = getAllJob(Common.SORT_1);
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

    /**
     * 柱状图展示X线城市和职位数量关系图
     *
     * @param isFlag    redis缓存的key后缀
     * @return
     */
    @Override
    public List<JobShow> getCityLevel(String isFlag) {
        String key = KeyConstants.JOB_INFO + isFlag;
        if (redisUtil.hasKey(key)) {
            return JSONUtils.parseArray((String) redisUtil.get(key), JobShow.class);
        }
        // 1.获取所有的岗位数据
        List<Job> allJobs = getAllJob(Common.SORT_1);
        Map<String, Long> map = allJobs.stream()
                .collect(Collectors.groupingBy(Job::getCityLevel, Collectors.counting()));

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
     * 柱状图展示区位和职位数量关系图
     *
     * @param isFlag    redis缓存的key后缀
     * @return
     */
    @Override
    public List<JobShow> getLocation(String isFlag) {
        String key = KeyConstants.JOB_INFO + isFlag;
        if (redisUtil.hasKey(key)) {
            return JSONUtils.parseArray((String) redisUtil.get(key), JobShow.class);
        }
        // 1.获取所有的岗位数据
        List<Job> allJobs = getAllJob(Common.SORT_1);
        Map<String, Long> map = allJobs.stream()
                .collect(Collectors.groupingBy(Job::getLocation, Collectors.counting()));

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
     * 柱状图展示工作年限和职位数量关系图
     *
     * @param isFlag    redis缓存的key后缀
     * @return
     */
    @Override
    public List<JobShow> getWordYear(String isFlag) {
        String key = KeyConstants.JOB_INFO + isFlag;
        if (redisUtil.hasKey(key)) {
            return JSONUtils.parseArray((String) redisUtil.get(key), JobShow.class);
        }
        // 1.获取所有的岗位数据
        List<Job> allJobs = getAllJob(Common.SORT_1);
        Map<String, Long> map = allJobs.stream()
                .collect(Collectors.groupingBy(Job::getJobYear, Collectors.counting()));

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
     * 柱状图展示薪资区间和职位数量关系图
     *
     * @param isFlag    redis缓存的key后缀
     * @return
     */
    @Override
    public List<JobShow> getSalaryInterval(String isFlag) {
        String key = KeyConstants.JOB_INFO + isFlag;
        if (redisUtil.hasKey(key)) {
            return JSONUtils.parseArray((String) redisUtil.get(key), JobShow.class);
        }
        // 1.获取所有的岗位数据
        List<Job> allJobs = getAllJob(Common.SORT_2);
        Map<String, Long> map = allJobs.stream()
                .collect(Collectors.groupingBy(Job::getSalaryInterval, Collectors.counting()));

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
