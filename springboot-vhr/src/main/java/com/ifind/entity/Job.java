package com.ifind.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 显示处理过的数据
 *
 * @author yidujun
 * @date 2020/4/13 9:29
 *
 */
@Data
public class Job implements Serializable {
    private static final long serialVersionUID = 4589699808999073441L;

    private Integer id;

    /**
     * 工作地点
     */
    private String jobAddress;

    /**
     * 城市水平---一线/新一线....
     */
    private String cityLevel;

    /**
     * 工作区位---华北/华南...
     */
    private String location;

    /**
     * 公司规模
     */
    private String companyNumStandard;

    /**
     * 学历
     */
    private String education;

    /**
     * 工作经验
     */
    private String jobYear;

    /**
     * 薪资区间
     */
    private String salaryInterval;

    /**
     * 平均薪资
     */
    private Double salaryAverage;

    /**
     * 公司所属行业
     */
    private String companyIndustry;

    /**
     * 聚类标签
     */
    private Integer cluster;

}
