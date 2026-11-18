package org.example.todolist.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record TodoRequest(
        @NotBlank (message = "ID is required")
        @Size (max = 250, message = "ID must not exceed 250")
       long id,

        @NotBlank (message = "Task is required")
        @Size (max = 250, message = "Task must not exceed 250")
        String task,

        @JsonFormat(pattern = "yyyy-MM-dd")
        @NotBlank (message = "Date is required")
        @Size (max = 10, message = "Date must not exceed 10")
        LocalDate date,

        @NotBlank (message = "status is required")
        boolean status) {
}
