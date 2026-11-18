package org.example.todolist.Repository;

import org.example.todolist.Entity.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodoListRepository extends JpaRepository<ToDoList, Long> {

    boolean existsByTask(String task);

    boolean existsByTaskAndIdNot(String task, Long id);

    Optional<ToDoList> findByTask(String task);
}
