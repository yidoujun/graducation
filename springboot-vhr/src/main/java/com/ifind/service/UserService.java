package com.ifind.service;

import com.ifind.entity.User;

public interface UserService {
    User getUser(User user);

    int insert(User rUser);
}
