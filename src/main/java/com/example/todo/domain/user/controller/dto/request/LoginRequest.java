package com.example.todo.domain.user.controller.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginRequest {

    @NotNull
    private String userId;

    @NotNull
    private String password;
}
