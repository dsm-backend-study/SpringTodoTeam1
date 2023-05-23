package com.example.todo.domain.todo.controller;

import com.example.todo.domain.todo.controller.dto.request.TodoCreateRequest;
import com.example.todo.domain.todo.controller.dto.request.TodoUpdateRequest;
import com.example.todo.domain.todo.controller.dto.response.TodoListResponse;
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

    @PostMapping("/clickCheck")
    public void clickCheckBox(@PathVariable long todoId) {

    }

    @PutMapping("/{todoId}")
    public void todoModify(
            @PathVariable Long todoId,
            @RequestBody TodoUpdateRequest request
    ) {
        todoService.modifyTodo(todoId, request);
    }

    @GetMapping
    public TodoListResponse todoList() {
        return todoService.listTodo();
    }


    @DeleteMapping("/{todoId}")
    public void todoRemove(
            @PathVariable long todoId
    ) {
        todoService.removeTodo(todoId);
    }
}
