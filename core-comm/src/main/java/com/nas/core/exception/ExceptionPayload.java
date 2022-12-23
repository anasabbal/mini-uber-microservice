package com.nas.core.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionPayload implements Serializable {

    private Integer code;
    private HttpStatus status;
    private String message;
    private Object[] args;
    private String reference;

    public ExceptionPayload(Integer code, HttpStatus status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

}
