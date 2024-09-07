package com.foxnival.exception;

public class DataInsertionFailedException extends RuntimeException{

    private String message;

    public DataInsertionFailedException(){

    }

    public DataInsertionFailedException(String message){
       super(message);
    }
}
