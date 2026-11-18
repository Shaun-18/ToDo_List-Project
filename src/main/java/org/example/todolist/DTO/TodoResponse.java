package org.example.todolist.DTO;

import java.time.LocalDate;

public record TodoResponse(
        long id,
        String task,
        LocalDate date,
        boolean status
) {
}
