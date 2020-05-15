package com.ifind.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private int isValid;

    private Date ctime;

    private Date mtime;
}
