package com.sundayteam.backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

@Data
@AllArgsConstructor
public class ExceptionResponse {
    @NonNull
    private int statusCode;
    @NonNull
    private String error;
    private String message;
    private Object exp;
}

