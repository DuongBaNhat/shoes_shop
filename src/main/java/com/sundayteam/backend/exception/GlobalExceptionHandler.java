package com.sundayteam.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.webjars.NotFoundException;

import javax.xml.bind.ValidationException;
import java.io.IOException;
import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        // handle validation exception
        System.out.println("GlobalExceptionHandler.handleMethodArgumentNotValidException");
        ExceptionResponse exp = new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), ex.getObjectName(),
                ex.getMessage(), ex.getBindingResult().getAllErrors());
        return ResponseEntity.badRequest().body(exp);
    }
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleValidationException(ValidationException ex) {
        // handle validation exception
        System.out.println("GlobalExceptionHandler.handleValidationException");
        return  ResponseEntity.badRequest().body("Validate");
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
        // handle not found exception
        System.out.println("GlobalExceptionHandler.handleNotFoundException");
        ExceptionResponse exp = new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), ex.getClass().getSimpleName(),
                ex.getMessage(), ex.getStackTrace()[0]);
        return ResponseEntity.badRequest().body(exp);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex) {
        // handle all exceptions
        System.out.println("GlobalExceptionHandler.handleAllExceptions");
        ExceptionResponse expRes = new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), ex.getClass().getSimpleName(),
                ex.getMessage(), ex.getStackTrace()[0]);
        return ResponseEntity.badRequest().body(expRes);
    }

}
