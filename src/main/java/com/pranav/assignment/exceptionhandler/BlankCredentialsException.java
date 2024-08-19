package com.pranav.assignment.exceptionhandler;

public class BlankCredentialsException extends RuntimeException {
    public BlankCredentialsException(String message) {
        super(message);
    }
}

