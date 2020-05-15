package com.ifind.entity;

import lombok.Data;

/**
 * 用户实体类
 *
 * @author Carlos
 * @date 2020/5/8 8:18
 */
@Data
public class User extends BaseEntity{

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 电话号码
     */
    private String mobile;

}
