package com.assignment.exception;

public class ServerException extends RuntimeException {
    public ServerException() {
        super("Server internal error");
    }
}
