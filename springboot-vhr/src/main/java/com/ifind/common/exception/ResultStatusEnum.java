package com.ifind.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public enum ResultStatusEnum {
    /**
     * 请求成功
     */
    SUCCESS(200, "请求成功！"),

    /**
     * 密码错误
     */
    PASSWORD_NOT_MATCHING(400, "密码错误"),

    /**
     * 序列化错误
     */
    SERIALIZE(-2000, "序列化错误");

    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private String message;
}
