package com.example.todo.domain.user.service;

import com.example.todo.domain.user.controller.dto.request.*;
import com.example.todo.domain.user.entity.User;
import com.example.todo.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public void signup(SignUpRequest request) {
        User user = User.builder()
                .userId(request.getUserId())
                .username(request.getUsername())
                .password(request.getPassword())
                .build();
        userRepository.save(user);
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
