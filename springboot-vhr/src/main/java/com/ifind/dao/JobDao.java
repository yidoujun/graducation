package com.ifind.dao;

import com.ifind.entity.Job;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * JobDao
 * 
 * @author Carlos 
 * @date 2020/5/9 8:10
 */
@Repository
@Mapper
public interface JobDao {

    List<Job> getAllJob(String sort);
}
