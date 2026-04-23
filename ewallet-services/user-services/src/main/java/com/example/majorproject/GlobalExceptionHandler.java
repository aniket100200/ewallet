package com.example.majorproject;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<String> handleDatabaseException(Exception exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
