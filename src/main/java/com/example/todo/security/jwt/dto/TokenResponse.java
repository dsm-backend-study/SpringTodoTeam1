package com.example.todo.security.jwt.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
public class TokenResponse {
    public String type;

    public String accessToken;

    public String refreshToken;
}
