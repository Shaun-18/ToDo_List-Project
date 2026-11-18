
package org.example.todolist.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.example.todolist.Entity.TodoStatus;

import java.time.LocalDate;

public record TodoRequest(

        @NotBlank(message = "Task is required")
        @Size(max = 250, message = "Task must not exceed 250 characters")
        String task,

        @JsonFormat(pattern = "dd/MM/yyyy")
        @NotNull(message = "Date is required")
        LocalDate date,

        @NotNull(message = "Status is required")
        TodoStatus status

) {}

