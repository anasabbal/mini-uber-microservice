package com.nas.core.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionResponse {

    private Integer code;
    private String message;
    private String reference;

    private ExceptionResponse(ExceptionPayload exceptionPayload) {
        this.code = exceptionPayload.getCode();
        this.message = exceptionPayload.getMessage();
        this.reference = exceptionPayload.getReference();
    }

    public static ExceptionResponse of(ExceptionPayload exceptionPayload) {
        return new ExceptionResponse(exceptionPayload);
    }
}
