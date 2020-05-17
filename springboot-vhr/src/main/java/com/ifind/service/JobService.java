package com.ifind.service;

import com.ifind.entity.Job;
import com.ifind.entity.JobShow;

import java.util.List;
import java.util.Map;

/**
 * jobService
 * 
 * @author Carlos 
 * @date 2020/5/9 8:08
 */
public interface JobService {

    List<JobShow> getCityAndNum(String isFlag);

    List<JobShow> getIndustryAndPosition(String isFlag);

    List<JobShow> getEducationAndPosition(String isFlag);

    List<JobShow> getCityLevel(String isFlag);

    List<JobShow> getLocation(String isFlag);

    List<JobShow> getWordYear(String isFlag);

    List<JobShow> getSalaryInterval(String isFlag);
}
