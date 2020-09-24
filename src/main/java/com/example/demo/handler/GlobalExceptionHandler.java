package com.example.demo.handler;

import com.example.demo.dto.ErrorResult;
import com.example.demo.exception.SimpleException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.security.InvalidParameterException;
import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(SimpleException.class)
    public ResponseEntity<ErrorResult> handleInvalidException(SimpleException e) {
        ErrorResult errorResult = new ErrorResult(HttpStatus.BAD_REQUEST.value(), e.getMessage(), Instant.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }
}
