package com.ifind.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 */
@Data
@EqualsAndHashCode
public class JobShow implements Comparable<JobShow>{

    private String name;

    private Long values;

    /**
     * 排序
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(JobShow o) {
        if (o == null) {
            return -1;
        }
        return this.getValues().compareTo(o.getValues());
    }
}
