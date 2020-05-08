package com.ifind.service.impl;

import com.ifind.dao.UserDao;
import com.ifind.entity.User;
import com.ifind.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户ServiceImpl
 *
 * @author Carlos
 * @date 2020/5/8 8:22
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUser(Long id) {
        return userDao.getById(id);
    }
}
