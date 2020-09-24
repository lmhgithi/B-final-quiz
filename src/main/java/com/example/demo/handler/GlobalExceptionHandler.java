package com.example.demo.handler;

import com.example.demo.exception.SimpleException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.security.InvalidParameterException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(SimpleException.class)
    public ResponseEntity handleInvalidException(SimpleException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
