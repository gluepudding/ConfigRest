package com.assignment.common.exception;

public class InvalidRequestException extends RuntimeException {

    public InvalidRequestException() {
        super("Invalid/Malformed requests");
    }
}
