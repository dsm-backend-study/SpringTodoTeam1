package com.example.todo.domain.user.controller;

import com.example.todo.domain.user.controller.dto.request.SignUpRequest;
import com.example.todo.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/users/remove")
    public void remove(
            @PathVariable Long userId
    ) {
        userService.removeUser(userId);
    }
}
