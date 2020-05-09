package com.ifind.service.impl;

import com.ifind.common.constants.KeyConstants;
import com.ifind.common.response.JSONUtils;
import com.ifind.dao.JobDao;
import com.ifind.entity.Job;
import com.ifind.service.JobService;
import com.ifind.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;


/**
 * jobServiceImpl
 *
 * @author Carlos
 * @date 2020/5/9 8:09
 */
@Service("jobService")
public class JobServiceImpl implements JobService {

    @Autowired
    private JobDao jobDao;

    @Autowired
    private RedisUtil redisUtil;


    @Override
    public List<Job> getAllJob() {
        String userName = "ydj";
        String key = KeyConstants.JOB_ALL_INFO + userName;
        String rst = (String)redisUtil.get(key);
        if (rst != null || "".equals(rst)) {
            List<Job> job = JSONUtils.parseArray(rst, Job.class);
            return job;
        }
        List<Job> list = jobDao.getAllJob();
        redisUtil.set(key, JSONUtils.toJsonString(list));
        return list;
    }
}
