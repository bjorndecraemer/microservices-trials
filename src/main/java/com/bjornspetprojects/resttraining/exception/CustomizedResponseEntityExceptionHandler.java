package com.bjornspetprojects.resttraining.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;


@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions (Exception e, WebRequest request){
        ExceptionResponse excResponse = ExceptionResponse
                .builder()
                .timestamp(LocalDateTime.now())
                .message(e.getMessage())
                .details(request.getDescription(false))
                .build();
        return new ResponseEntity<>(excResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundExceptions (UserNotFoundException e, WebRequest request){
        ExceptionResponse excResponse = ExceptionResponse
                .builder()
                .timestamp(LocalDateTime.now())
                .message("User not found")
                .details(request.getDescription(false))
                .build();
        return new ResponseEntity<>(excResponse, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionResponse excResponse = ExceptionResponse
                .builder()
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .details(ex.getBindingResult().toString())
                .build();
        return new ResponseEntity<>(excResponse, HttpStatus.BAD_REQUEST);
    }
}
