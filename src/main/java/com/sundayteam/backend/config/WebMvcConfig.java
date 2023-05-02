package com.sundayteam.backend.config;

import com.sundayteam.backend.interceptor.AuthInterceptor;
import com.sundayteam.backend.interceptor.ResponseInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration()
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private ResponseInterceptor responseInterceptor;
    @Autowired
    private AuthInterceptor authInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(responseInterceptor);
        // registry.addInterceptor(authInterceptor);
    }

}
