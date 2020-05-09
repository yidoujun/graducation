package com.ifind.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户实体类
 *
 * @author Carlos
 * @date 2020/5/8 8:18
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = -6959603157147685396L;
    /**
     * 主键
     */
    private Long id;

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
