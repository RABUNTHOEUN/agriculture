package com.thoeun.agriculture.exceptions;

public class AlreadyExistsException extends RuntimeException{
    public AlreadyExistsException (String message) {
        super(message);
    }
}
