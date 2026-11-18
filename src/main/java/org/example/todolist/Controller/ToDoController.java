package org.example.todolist.Controller;

import jakarta.validation.Valid;
import org.example.todolist.DTO.TodoRequest;
import org.example.todolist.DTO.TodoResponse;
import org.example.todolist.Service.TodoListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Tasks")
public class ToDoController {

    private final TodoListService todoListService;

    public ToDoController(TodoListService todoListService){this.todoListService = todoListService;}

    @PostMapping
    public ResponseEntity<TodoResponse> createTask(
            @Valid @RequestBody TodoRequest request ){
        TodoResponse response = todoListService.createTask(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);


    }

    @GetMapping
    public ResponseEntity<List<TodoResponse>>getAllTasks(){
        return ResponseEntity.ok(todoListService.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoResponse> getTaskById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                todoListService.getTaskById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoResponse>updateTask(
      @PathVariable long id,
      @Valid @RequestBody TodoRequest request) {
        return ResponseEntity.ok(todoListService.updateTask(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteTask(
            @PathVariable long id) {
        todoListService.deleteTask(id);

        return ResponseEntity.noContent().build();
    }


}
