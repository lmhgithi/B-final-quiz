package com.example.demo.exception;

public class SimpleException extends RuntimeException {
    private final String message;
    public SimpleException(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
}
