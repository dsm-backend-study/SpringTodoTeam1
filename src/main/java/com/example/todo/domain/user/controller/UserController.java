package com.example.todo.domain.user.controller;

import com.example.todo.domain.user.controller.dto.request.SignUpRequest;
import com.example.todo.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public void signup(
            @RequestBody SignUpRequest request
    ) {
        userService.signup(request);
    }

    @DeleteMapping("/remove/{userId}")
    public void removeUser(
            @PathVariable Long userId
    ) {
        userService.removeUser(userId);
    }
}
