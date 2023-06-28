package com.example.todo.security.jwt.config;

import com.example.todo.security.auth.CustomUserDetailsService;
import com.example.todo.security.exception.ExceptionFilter;
import com.example.todo.security.jwt.JwtFilter;
import com.example.todo.security.jwt.token.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class FilterConfiguration extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final JwtTokenProvider jwtTokenProvider;

    private final CustomUserDetailsService customUserDetailsService;

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        var jwtFilter = new JwtFilter(jwtTokenProvider, customUserDetailsService);
        var exceptionFilter = new ExceptionFilter();
        builder.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        builder.addFilterBefore(exceptionFilter, JwtFilter.class);
    }
}
