package com.ifind.dao;

import com.ifind.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserDao {
    /**
     * 查询用户
     * @param user
     * @return
     */
    User getUser(User user);

    int insert(User rUser);
}
