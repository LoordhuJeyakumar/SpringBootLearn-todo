package com.example.startSpring;


import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public void addTodo(String task) {
        if (task.isEmpty()) {
            throw new RuntimeException("Task cannot be empty");
        }
        todoRepository.save(task);
    }

}
