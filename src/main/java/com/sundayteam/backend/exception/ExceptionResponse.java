package com.sundayteam.backend.exception;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class ExceptionResponse {
    @NonNull
    private int statusCode;
    @NonNull
    private String error;
    private String message;
    private Object exp;
}
