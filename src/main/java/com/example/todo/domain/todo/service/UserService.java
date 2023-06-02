package com.example.todo.domain.todo.service;

import com.example.todo.domain.todo.Entity.User;
import com.example.todo.domain.todo.controller.dto.request.SignUpRequest;
import com.example.todo.domain.todo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void signup(SignUpRequest request) {
        User user = new User(request.getUsername(),request.getPassword());
        userRepository.save(user);
    }
}
