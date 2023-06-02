package com.example.todo.domain.todo.controller;

import com.example.todo.domain.todo.controller.dto.request.SignUpRequest;
import com.example.todo.domain.todo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users/signup")
    public void signup(
            @RequestBody SignUpRequest request
    ) {
        userService.signup(request);
    }

}
