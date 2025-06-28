package org.carrefour.leasing.application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.carrefour.leasing.core.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ApiExceptionHandlerAdvice {

    private static final Logger LOG = LogManager.getLogger(ApiExceptionHandlerAdvice.class.getName());

    @ExceptionHandler(value = IllegalStateException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<String> business(IllegalStateException exception, WebRequest request) {
        LOG.error(exception.getMessage(), exception);
        return ResponseEntity.internalServerError().body(exception.getMessage());
    }
}
