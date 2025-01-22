package com.foxnival.exception;

public class InvalidRequestException extends RuntimeException {

    private String message;

    public InvalidRequestException() {

    }

    public InvalidRequestException(String message) {
        super(message);
    }
}
