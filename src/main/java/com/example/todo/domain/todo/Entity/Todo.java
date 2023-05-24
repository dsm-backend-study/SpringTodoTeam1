package com.example.todo.domain.todo.Entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private boolean isChecked = false;

    public void clickCheckBox() {
        this.isChecked = !isChecked;
    }

    public void updateTodo(
            String content
    ) {
        this.content = content;
    }

    @Builder
    public Todo(
            String content,
            boolean isChecked
    ) {
        this.content = content;
        this.isChecked = isChecked;
    }

}
