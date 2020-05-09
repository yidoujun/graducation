package com.ifind.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yidujun
 * @date 2020/4/13 9:29
 *
 */
@Data
public class Job implements Serializable {
    private static final long serialVersionUID = 4589699808999073441L;

    /**
     * id
     */
    private Integer id;

    /**
     * 岗位名称
     */
    private String jobPosition;

    /**
     * 企业名称
     */
    private String jobCompany;

    /**
     * 工作地点
     */
    private String jobAddress;

    private String jobWelfare;

    private String jobYears;

    private String jobEducation;

    private String companyNum;

    private String companyBusiness;

    private String jobDetail;

    private String cityLevel;

    private String location;

    private String companyNumStandard;

    private String education;

    private String jobYear;

    private String salaryInterval;

    private Double salaryAverage;

    private String companyIndustry;

    private Integer cluster;

}
