package com.assignment.exception;

import com.assignment.utils.StatusEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<Object> handleInvalidRequestException(
            InvalidRequestException ex, WebRequest request) {

        Map<String, Object> body = new HashMap<>();
        body.put("code", StatusEnum.INVALID.getState());
        body.put("message", StatusEnum.INVALID.getStateInfo());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoConfException.class)
    public ResponseEntity<Object> handleNoConfException(
            NoConfException ex, WebRequest request) {

        Map<String, Object> body = new HashMap<>();
        body.put("code", StatusEnum.NOT_FOUND.getState());
        body.put("message", StatusEnum.NOT_FOUND.getStateInfo());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoDataException.class)
    public ResponseEntity<Object> handleNoDataException(
            NoDataException ex, WebRequest request) {

        Map<String, Object> body = new HashMap<>();
        body.put("code", StatusEnum.NOT_FOUND.getState());
        body.put("message", StatusEnum.NOT_FOUND.getStateInfo());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ServerException.class)
    public ResponseEntity<Object> handleServerException(
            ServerException ex, WebRequest request) {

        Map<String, Object> body = new HashMap<>();
        body.put("code", StatusEnum.INTERAL_ERR.getState());
        body.put("message", StatusEnum.INTERAL_ERR.getStateInfo());

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<Object> handleAuthException(
            AuthException ex, WebRequest request) {

        Map<String, Object> body = new HashMap<>();
        body.put("code", StatusEnum.AUTHOR_FAIL.getState());
        body.put("message", StatusEnum.AUTHOR_FAIL.getStateInfo());

        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }
}
