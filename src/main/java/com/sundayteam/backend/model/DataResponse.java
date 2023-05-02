package com.sundayteam.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataResponse {
    private int statusCode;
    private String message;
    private Object data;

    public DataResponse(Object data) {
        statusCode = HttpStatus.OK.value();
        message = "Success";
        this.data = data;
    }
}
