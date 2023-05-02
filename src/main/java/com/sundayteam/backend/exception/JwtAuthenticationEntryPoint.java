package com.sundayteam.backend.exception;

import com.nimbusds.jose.shaded.gson.Gson;
import com.sundayteam.backend.model.ExceptionResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;

/**
 * This class rejects every unauthenticated request
 * with an error code 401 sent back to the client.
 */
// @Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        System.out.println("JwtAuthenticationEntryPoint.commence");

        ExceptionResponse exp = new ExceptionResponse(HttpServletResponse.SC_UNAUTHORIZED,
                authException.getClass().getSimpleName(),
                authException.getMessage(), null);
        response.setStatus(exp.getStatusCode());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.toString());

        Gson gson = new Gson();
        String json = gson.toJson(exp);
        response.getWriter().write(json);
    }
}
