package com.example.startSpring.service;

import com.example.startSpring.model.Todo;
import com.example.startSpring.repository.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public Todo saveTodo(Todo todo) {
        try {
            return todoRepository.save(todo);
        } catch (Exception e) {
            log.error("Error saving todo: " + e.getMessage());
            return null;
        }
    }

    public List<Todo> getAllTodos() {
        try {
            return todoRepository.findAll();
        } catch (Exception e) {
            log.error("Error getting all todos: " + e.getMessage());
            return null;
        }
    }

    public Page<Todo> getAllTodosWithPagination(int page, int size, String sortBy) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
            return todoRepository.findAll(pageable);
        } catch (Exception e) {
            log.error("Error getting all todos with pagination: " + e.getMessage());
            return null;
        }
    }

    public Todo editTodoById(Long id, Todo todo) {
        try {
            Todo existingTodo = todoRepository.findById(id).orElse(null);

            if (existingTodo != null) {
                existingTodo.setTitle(todo.getTitle());
                existingTodo.setDescription(todo.getDescription());
                existingTodo.setCompleted(todo.isCompleted());
                return todoRepository.save(existingTodo);
            } else {
                return null;
            }
        } catch (Exception e) {
            log.error("Error editing todo: " + e.getMessage());
            return null;
        }
    }

    public Todo updateTodoStatus(Long id, Todo todo) {
        try {
            Todo existingTodo = todoRepository.findById(id).orElse(null);

            if (existingTodo != null) {
                existingTodo.setCompleted(todo.isCompleted());
                return todoRepository.save(existingTodo);
            } else {
                return null;
            }
        } catch (Exception e) {
            log.error("Error editing todo: " + e.getMessage());
            return null;
        }
    }

    public void deleteTodo(Long id) {
        try {
            todoRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Error deleting todo: " + e.getMessage());
            throw new RuntimeException("Error deleting todo");
        }
    }
}
