package com.sundayteam.backend.modules.security.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginResponse implements Serializable {
    private String tokenType = "Bearer";
    private String accessToken;

    public LoginResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
