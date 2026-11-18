package org.example.todolist.Service;
import org.example.todolist.Exceptions.DuplicateResourceException;
import org.example.todolist.DTO.TodoRequest;
import org.example.todolist.DTO.TodoResponse;
import org.example.todolist.Entity.ToDoList;
import org.example.todolist.Exceptions.ResourceNotFoundException;
import org.example.todolist.Repository.TodoListRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;

@Service
public class TodoListService {


    private final TodoListRepository todoListRepository;

    public TodoListService(TodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }

    // Create Task
    @Transactional
    public TodoResponse createTask(TodoRequest request) {
        if (todoListRepository.existsByTask(request.task())) {
            throw new DuplicateResourceException(
                    "A task with this name already exists"
            );
        }
        ToDoList toDoList = new ToDoList();

        toDoList.setTask(request.task());
        toDoList.setDate(request.date());
        toDoList.setStatus(request.status());

        ToDoList savedTask = todoListRepository.save(toDoList);
        return mapToResponse(savedTask);
    }

    //Read Task
    @Transactional(readOnly = true)
    public List<TodoResponse> getAllTasks() {
        return todoListRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    //Read one
    @Transactional(readOnly = true)
    public TodoResponse getTaskById(long id) {
        ToDoList toDoList = findTaskorThrow(id);

        return mapToResponse(toDoList);
    }

    //Update
    @Transactional
    public TodoResponse updateTask(long id, TodoRequest request) {
        ToDoList toDoList = findTaskorThrow(id);

        boolean taskAlreadyExist = todoListRepository.existsByTaskAndIdNot(request.task(), id);
        if (taskAlreadyExist) {
            throw new DuplicateResourceException("A Task with this name already exists");

        }
        toDoList.setStatus(request.status());
        toDoList.setDate(request.date());
        toDoList.setTask(request.task());

        ToDoList uptatedTask = (ToDoList) todoListRepository.save(toDoList);

        return mapToResponse(uptatedTask);
    }

    //Delete
    @Transactional
    public void deleteTask(long id) {
        ToDoList toDoList = findTaskorThrow(id);

        todoListRepository.delete(toDoList);
    }

    private ToDoList findTaskorThrow(long id) {
        return todoListRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Task not found with ID: " + id
                ));
    }

    private TodoResponse mapToResponse(ToDoList toDoList) {

        return new TodoResponse(
                toDoList.getId(),
                toDoList.getTask(),
                toDoList.getDate(),
                toDoList.getStatus()

        );
    }
}
