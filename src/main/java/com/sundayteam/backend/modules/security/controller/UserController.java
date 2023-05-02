package com.sundayteam.backend.modules.security.controller;

import com.sundayteam.backend.annotation.Auth;
import com.sundayteam.backend.model.DataResponse;
import com.sundayteam.backend.modules.security.entity.UserEntity;
import com.sundayteam.backend.modules.security.model.request.LoginRequest;
import com.sundayteam.backend.modules.security.model.request.RegisterRequest;
import com.sundayteam.backend.modules.security.model.response.LoginResponse;
import com.sundayteam.backend.modules.security.model.user.UserDetailsImpl;
import com.sundayteam.backend.modules.security.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentication", description = "User authentication management ")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @SecurityRequirements
    @PostMapping("/login")
    public Object login(@Valid @RequestBody LoginRequest request) {
        return new DataResponse(userService.login(request));
    }

    @SecurityRequirements
    @PostMapping("/register")
    public Object register(@Valid @RequestBody RegisterRequest request) {
        return new DataResponse(userService.register(request));
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserDetailsImpl>> get() {
        return ResponseEntity.ok(userService.list());
    }
}
