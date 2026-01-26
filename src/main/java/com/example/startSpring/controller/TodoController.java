package com.example.startSpring.controller;

import com.example.startSpring.model.Todo;
import com.example.startSpring.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/all")
    public ResponseEntity<List<Todo>> getAllTodos() {
        try {
            log.info("Calling API endpoint to get all todos /api/v1/todos/all");
            List<Todo> listOfTodos = todoService.getAllTodos();
            return new ResponseEntity<>(listOfTodos, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error getting all todos: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all/pagination")
    public ResponseEntity<Page<Todo>> getAllTodosWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "completed") String sortBy
    ) {
        try {
            log.info("Calling API endpoint to get all todos with pagination");
            Page<Todo> todos = todoService.getAllTodosWithPagination(page, size, sortBy);
            return new ResponseEntity<>(todos, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error getting all todos with pagination: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{todoId}")
    public String getTodoById(@PathVariable("todoId") String todoId) {
        return "Get todo by id " + todoId;
    }

    @GetMapping("/byUser")
    public String getTodosByUser(@RequestParam String user, @RequestParam String status, @RequestParam String priority) {
        return "Get todo by user " + user + " " + status + " " + priority;
    }

    @PostMapping("/create")
    public ResponseEntity<Todo> addTodo(@RequestBody Todo todo) {
        return new ResponseEntity<>(todoService.saveTodo(todo), HttpStatus.CREATED);
    }

    @PutMapping("/{todoId}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long todoId, @RequestBody Todo todo) {
        Todo updatedTodo = todoService.editTodoById(todoId, todo);
        if (updatedTodo != null) {
            return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{todoId}/status")
    public ResponseEntity<Todo> updateTodoStatus(@PathVariable Long todoId, @RequestBody Todo todo) {
        Todo updatedTodo = todoService.editTodoById(todoId, todo);
        if (updatedTodo != null) {
            return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long todoId) {
        try {
            todoService.deleteTodo(todoId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting todo", e);
        }
    }
}
