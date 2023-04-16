package com.sundayteam.backend.modules.security.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RegisterRequest extends LoginRequest {
    @NotBlank
    @Email
    private String email;
}
