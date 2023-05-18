package com.example.todo.domain.todo.controller;

import com.example.todo.domain.todo.controller.dto.request.TodoCreateRequest;
import com.example.todo.domain.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    public void todoAdd(
            @RequestBody TodoCreateRequest request
    ) {
        todoService.addTodo(request);
    }
}
