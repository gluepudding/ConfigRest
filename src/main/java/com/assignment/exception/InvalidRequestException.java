package com.assignment.exception;

public class InvalidRequestException extends RuntimeException {

    public InvalidRequestException() {
        super("Invalid/Malformed requests");
    }
}
