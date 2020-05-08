package com.ifind.dao;

import com.ifind.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    User getById(Long id);
}
