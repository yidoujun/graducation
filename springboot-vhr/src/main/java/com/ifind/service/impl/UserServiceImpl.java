package com.ifind.service.impl;

import com.ifind.common.constants.KeyConstants;
import com.ifind.common.constants.Status;
import com.ifind.dao.UserDao;
import com.ifind.entity.User;
import com.ifind.service.UserService;
import com.ifind.util.RedisUtil;
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

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 获取用户信息
     *
     * @param user
     * @return
     */
    @Override
    public User getUser(User user) {
        String key = KeyConstants.USER_INFO + user.getUserName();
        if (redisUtil.hasKey(key)) {
            return (User)redisUtil.get(key);
        } else {
            User u = userDao.getUser(user);
            if(u != null) {
                // 添加缓存
                redisUtil.set(key, user, Status.ExpireEnum.PICTURE_DATA.getTime());
                return user;
            }
            return null;
        }
    }

    /**
     * 添加用户
     *
     * @param rUser
     * @return
     */
    @Override
    public int insert(User rUser) {
        // 密码加密
        return userDao.insert(rUser);
    }
}
