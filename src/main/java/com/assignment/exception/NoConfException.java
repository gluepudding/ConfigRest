package com.assignment.exception;

public class NoConfException extends RuntimeException {

    public NoConfException(String appCode, String version) {
        super(String.format("No config found for AppCode: %s with Version: %s", appCode, version));
    }
}
