package com.sundayteam.backend.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
public class ResourceConflictException extends RuntimeException  {
    private static final long serialVersionUID = 1L;

    private static String message = "Already existed";
    private int statusCode = 409;

    public ResourceConflictException() {
        super(message);
    }
   public ResourceConflictException(String message) {
       super(message);
   }

}
