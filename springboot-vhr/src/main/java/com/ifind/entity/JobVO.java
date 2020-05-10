package com.ifind.entity;

import lombok.Data;

/**
 * JobVO 显示job表全部数据
 */
@Data
public class JobVO extends Job{

    private String jobPosition;

    private String jobCompany;

    private String jobWelfare;

    private String jobYears;

    private String jobEducation;

    private String companyNum;

    private String companyBusiness;

    private String jobDetail;
}
