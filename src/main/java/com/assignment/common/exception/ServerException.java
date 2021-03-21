package com.assignment.common.exception;

public class ServerException extends RuntimeException {
    public ServerException() {
        super("Server internal error");
    }
}
