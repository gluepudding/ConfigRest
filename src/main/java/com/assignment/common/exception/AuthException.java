package com.assignment.common.exception;

public class AuthException extends RuntimeException {
    public AuthException() {
        super("Do Not have permission");
    }
}
