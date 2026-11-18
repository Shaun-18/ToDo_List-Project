package org.example.todolist.DTO;

import org.example.todolist.Entity.TodoStatus;

import java.time.LocalDate;

public record TodoResponse(
        long id,
        String task,
        LocalDate date,
        TodoStatus status
) {
}
