package com.sundayteam.backend.modules.security.controller;

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
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(userService.login(request));
    }

    @SecurityRequirements
    @PostMapping("/register")
    public ResponseEntity<UserEntity> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(userService.register(request));
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserDetailsImpl>> get() {
        return ResponseEntity.ok(userService.list());
    }
}
