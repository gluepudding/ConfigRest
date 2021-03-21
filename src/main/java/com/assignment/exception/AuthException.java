package com.assignment.exception;

public class AuthException extends RuntimeException {
    public AuthException() {
        super("Do Not have permission");
    }
}
