package com.example.todo.domain.todo.controller.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public class TodoListResponse {

    private List<TodoResponse> todoResponseList;
}
