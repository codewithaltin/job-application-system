package com.example.careerify.exception;

public class DuplicateEntryException extends RuntimeException {
    public DuplicateEntryException(String message, Throwable cause) {
        super(message, cause);
    }
}