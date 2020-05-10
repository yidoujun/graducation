package com.ifind.common.exception;

import lombok.Getter;

/**
 * 自定义异常类
 *
 * @author Carlos
 * @date 2020/5/10 10:23
 */
@Getter
public class CustomException extends Exception{

    private int code;
    private String message;

    public CustomException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public CustomException(ResultStatusEnum resultStatusEnum) {
        this.code = resultStatusEnum.getCode();
        this.message = resultStatusEnum.getMessage();
    }
}

