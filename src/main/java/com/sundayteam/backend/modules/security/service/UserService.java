package com.sundayteam.backend.modules.security.service;

import com.sundayteam.backend.modules.security.entity.UserEntity;
import com.sundayteam.backend.modules.security.model.jwt.JwtAuthenticationProvider;
import com.sundayteam.backend.modules.security.model.request.LoginRequest;
import com.sundayteam.backend.modules.security.model.request.RegisterRequest;
import com.sundayteam.backend.modules.security.model.response.LoginResponse;
import com.sundayteam.backend.modules.security.model.user.UserDetailsImpl;
import com.sundayteam.backend.modules.security.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       JwtAuthenticationProvider jwtAuthenticationProvider) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtAuthenticationProvider = jwtAuthenticationProvider;
    }

    public UserEntity register(RegisterRequest user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new RuntimeException("username already existed");
        }
        UserEntity newUser = UserEntity.builder()
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .email(user.getEmail())
                .enabled(true)
                .build();
        userRepository.save(newUser);
        return newUser;
    }

    public LoginResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        SecurityContextHolder.getContext()
                .setAuthentication(authentication);
        String jwt = jwtAuthenticationProvider.generateToken(authentication);
        return new LoginResponse(jwt);
    }

    public List<UserDetailsImpl> list() {
        List<UserEntity> entityList = userRepository.findAll();

        List<UserDetailsImpl> result = new ArrayList<>();

        for (UserEntity list : entityList) {
            result.add(convertEntityToDetail(list));
        }
        return result;
    }

    UserDetailsImpl convertEntityToDetail(UserEntity entity) {
        return UserDetailsImpl.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .enabled(entity.isEnabled())
                .email(entity.getEmail())
                .build();
    }
}
