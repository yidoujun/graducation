package com.ifind.service.impl;

import com.ifind.common.constants.KeyConstants;
import com.ifind.common.constants.Status;
import com.ifind.dao.UserDao;
import com.ifind.entity.User;
import com.ifind.service.UserService;
import com.ifind.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public User getUser(Long id) {
        String key = KeyConstants.USER_INFO + String.valueOf(id);
        Object obj = redisUtil.get(key);
        if (obj != null) {
            return (User)obj;
        } else {
            User user = userDao.getById(id);
            // 添加缓存
            redisUtil.set(key, user, Status.ExpireEnum.PICTURE_DATA.getTime());
            return user;
        }
    }
}
