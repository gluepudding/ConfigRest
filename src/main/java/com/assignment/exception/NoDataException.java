package com.assignment.exception;

public class NoDataException extends RuntimeException {

    public NoDataException(String appCode) {
        super(String.format("No config found for AppCode: %s", appCode));
    }
}
