package com.nas.core.exception;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.UUID;


@RestControllerAdvice
@AllArgsConstructor
@Slf4j
public class RestExceptionHandler {
    private final MessageSourceHandler messageSource;

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ExceptionResponse> handleBusinessException(BusinessException ex) {
        ExceptionPayload payload = ex.getPayload();
        String errorMessage = messageSource.getMessage(payload.getMessage(), payload.getArgs());
        log.error("Business exception occurred : {}", errorMessage, ex);
        return ResponseEntity.status(payload.getStatus()).body(ExceptionResponse.builder().code(payload.getCode()).message(errorMessage).build());
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponse> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException e) {
        ExceptionPayload payload = ExceptionPayloadFactory.MISSING_REQUEST_BODY_ERROR_CODE.get();
        String errorMessage = messageSource.getMessage(payload.getMessage());
        return ResponseEntity.status(payload.getStatus()).body(ExceptionResponse.builder().code(payload.getCode()).message(errorMessage).build());
    }


    @ExceptionHandler({Throwable.class, TechnicalException.class})
    public ResponseEntity<ExceptionResponse> handleThrowableException(Throwable throwable) {
        final String uuid = UUID.randomUUID().toString();
        String errorMessage = messageSource.getMessage(ExceptionPayloadFactory.TECHNICAL_ERROR.getMessage());
        log.error("Technical exception occurred with reference : {}", uuid, throwable);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ExceptionResponse.of(ExceptionPayload.builder()
                .code(ExceptionPayloadFactory.TECHNICAL_ERROR.getCode())
                .message(errorMessage).reference(uuid)
                .build()));
    }
}