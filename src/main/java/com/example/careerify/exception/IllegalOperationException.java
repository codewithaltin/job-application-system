package com.example.careerify.exception;

public class IllegalOperationException extends RuntimeException {
    public IllegalOperationException(String message, Throwable cause) {
        super(message, cause);
    }

}