package com.example.todo.domain.user.service;

import com.example.todo.domain.user.controller.dto.request.*;
import com.example.todo.domain.user.entity.User;
import com.example.todo.domain.user.repository.UserRepository;
import com.example.todo.security.auth.CustomUserDetailsService;
import com.example.todo.security.jwt.token.JwtTokenProvider;
import com.example.todo.security.jwt.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final JwtTokenProvider jwtTokenProvider;

    private final CustomUserDetailsService customUserDetailsService;

    private final UserRepository userRepository;


    public void signup(SignUpRequest request) {

        String password = passwordEncoder.encode(request.getPassword());

        User user = User.builder()
                .userId(request.getUserId())
                .username(request.getUsername())
                .password(password)
                .build();
        userRepository.save(user);
    }

    public TokenResponse login(LoginRequest request) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(request.getUserId(), request.getPassword());

        UserDetails user = customUserDetailsService.loadUserByUsername(request.getUserId());

        if(!passwordEncoder.matches(authenticationToken.getCredentials().toString(), user.getPassword()))
            throw new NullPointerException(); // password mismatch

        return jwtTokenProvider.createToken(request.getUserId());
    }

    public void removeUser(Long userId) {
        if (userRepository.existsById(userId)) {
            throw new NullPointerException();
        }
        userRepository.deleteById(userId);
    }

    public void modifyUser(
            Long userId,
            UserUpdateRequest request
    ) {
        User user = userRepository.findById(userId)
                .orElseThrow();

        user.updateUser(
                request.getUserId(),
                request.getUsername(),
                request.getPassword());
    }
}
