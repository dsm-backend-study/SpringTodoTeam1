package com.example.todo.domain.todo.controller;

import com.example.todo.domain.todo.controller.dto.request.TodoCreateRequest;
import com.example.todo.domain.todo.controller.dto.request.TodoUpdateRequest;
import com.example.todo.domain.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public void todoAdd(
            @RequestBody TodoCreateRequest request
    ) {
        todoService.addTodo(request);
    }

    @PutMapping("/{todoId}")
    public void todoModify(
            @PathVariable Long todoId,
            @RequestBody TodoUpdateRequest request
    ) {
        todoService.modifyTodo(todoId, request);
    }
}
