package com.nas.core.exception;


import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException  {
    private final ExceptionPayload payload;

    public BusinessException(ExceptionPayload payload) {
        this.payload = payload;
    }

    public BusinessException(ExceptionPayload payload, Object... args) {
        payload.setArgs(args);
        this.payload = payload;
    }
}
