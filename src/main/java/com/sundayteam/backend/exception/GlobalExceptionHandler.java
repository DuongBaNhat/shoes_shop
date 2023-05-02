package com.sundayteam.backend.exception;

import com.sundayteam.backend.model.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.webjars.NotFoundException;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        // handle validation exception (422 Un_processable Entity)
        System.out.println("GlobalExceptionHandler.handleMethodArgumentNotValidException");

        ExceptionResponse exp = new ExceptionResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getObjectName(),
                ex.getMessage(), ex.getBindingResult().getAllErrors());
        return ResponseEntity.status(exp.getStatusCode()).body(exp);
    }
    // @ExceptionHandler(ValidationException.class)
    // public ResponseEntity<Object> handleValidationException(ValidationException ex) {
    //     // handle validation exception
    //     System.out.println("GlobalExceptionHandler.handleValidationException");
    //     return  ResponseEntity.badRequest().body("Validate");
    // }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(Exception ex) {
        // handle not found exception
        System.out.println("GlobalExceptionHandler.handleNotFoundException");
        ExceptionResponse exp = new ExceptionResponse(HttpStatus.NOT_FOUND.value(), ex.getClass().getSimpleName(),
                ex.getMessage(), ex.getStackTrace()[0]);
        return ResponseEntity.status(exp.getStatusCode()).body(exp);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(Exception ex) {
        // handle access denied exception (403 Forbidden)
        System.out.println("GlobalExceptionHandler.handleAccessDeniedException");
        ExceptionResponse exp = new ExceptionResponse(HttpStatus.FORBIDDEN.value(), ex.getClass().getSimpleName(),
                ex.getMessage(), ex.getStackTrace()[0]);
        return ResponseEntity.status(exp.getStatusCode()).body(exp);
    }

    @ExceptionHandler(ResourceConflictException.class)
    public ResponseEntity<Object> handleConflictException(ResourceConflictException ex)  {
        // handle conflict exception (409 conflict)
        System.out.println("GlobalExceptionHandler.handleConflictException");

        ExceptionResponse exp = new ExceptionResponse(ex.getStatusCode(), ex.getClass().getSimpleName(),
                ex.getMessage(), ex.getStackTrace()[0]);
        return ResponseEntity.status(exp.getStatusCode()).body(exp);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex) {
        // handle all exceptions (bad request 400)
        System.out.println("GlobalExceptionHandler.handleAllExceptions");
        ExceptionResponse exp = new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), ex.getClass().getSimpleName(),
                ex.getMessage(), ex.getStackTrace()[0]);
        return ResponseEntity.status(exp.getStatusCode()).body(exp);
    }

}
