package com.artineer.artineer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class IntegrationExceptionHandler {

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Object> handlerMessageIllegalEx(Exception ex) {
        Map<HttpStatus, String> returnStat = new HashMap<>();
        returnStat.put(HttpStatus.BAD_REQUEST, ex.getMessage());

        return new ResponseEntity<>(returnStat, HttpStatus.OK);
    }
}