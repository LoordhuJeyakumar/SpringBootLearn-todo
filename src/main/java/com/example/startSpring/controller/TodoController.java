package com.example.startSpring.controller;

import com.example.startSpring.dto.ApiResponse;
import com.example.startSpring.model.Todo;
import com.example.startSpring.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TodoController
 * --------------
 * This class handles all requests related to "Todos" (Tasks).
 * It uses the Service layer to do the actual work.
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    // ----------------------------------------------------------------
    // PUBLIC ENDPOINTS (Well, authenticated users only)
    // ----------------------------------------------------------------

    /**
     * Get all todos.
     */
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<Todo>>> getAllTodos() {
        log.info("Calling API endpoint to get all todos /api/v1/todos/all");
        List<Todo> listOfTodos = todoService.getAllTodos();
        return ResponseEntity.ok(ApiResponse.success("Todos fetched successfully", listOfTodos));
    }

    /**
     * Get todos with pagination.
     */
    @GetMapping("/all/pagination")
    public ResponseEntity<ApiResponse<Page<Todo>>> getAllTodosWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "completed") String sortBy
    ) {
        log.info("Calling API endpoint to get all todos with pagination");
        Page<Todo> todos = todoService.getAllTodosWithPagination(page, size, sortBy);
        return ResponseEntity.ok(ApiResponse.success("Todos fetched successfully", todos));
    }

    /**
     * Get a single Todo by ID.
     */
    @GetMapping("/{todoId}")
    public ResponseEntity<ApiResponse<Todo>> getTodoById(@PathVariable Long todoId) {
        // We need to implement getTodoById in Service first, or reuse editTodoById logic (which fetches it)
        // But better to have a dedicated method.
        // For now, I'll assume we add getTodoById to Service.
        Todo todo = todoService.getTodoById(todoId);
        return ResponseEntity.ok(ApiResponse.success("Todo fetched successfully", todo));
    }

    /**
     * Create a new Todo.
     */
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Todo>> addTodo(@Valid @RequestBody Todo todo) {
        Todo savedTodo = todoService.saveTodo(todo);
        return new ResponseEntity<>(
                ApiResponse.success("Todo created successfully", savedTodo),
                HttpStatus.CREATED
        );
    }

    /**
     * Update an existing Todo.
     */
    @PutMapping("/{todoId}")
    public ResponseEntity<ApiResponse<Todo>> updateTodo(@PathVariable Long todoId, @Valid @RequestBody Todo todo) {
        Todo updatedTodo = todoService.editTodoById(todoId, todo);
        return ResponseEntity.ok(ApiResponse.success("Todo updated successfully", updatedTodo));
    }
    
    /**
     * Update Todo Status (Partial Update).
     */
    @PatchMapping("/{todoId}/status")
    public ResponseEntity<ApiResponse<Todo>> updateTodoStatus(@PathVariable Long todoId, @RequestBody Todo todo) {
        // We can reuse editTodoById or create a specific method. 
        // For simplicity, reusing editTodoById as it handles updates.
        // In a real app, you might want a specific method to only update the status.
        Todo updatedTodo = todoService.editTodoById(todoId, todo);
        return ResponseEntity.ok(ApiResponse.success("Todo status updated successfully", updatedTodo));
    }

    // ----------------------------------------------------------------
    // ADMIN ONLY ENDPOINTS
    // ----------------------------------------------------------------

    /**
     * Delete a Todo.
     */
    @DeleteMapping("/{todoId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> deleteTodo(@PathVariable Long todoId) {
        todoService.deleteTodo(todoId);
        return ResponseEntity.ok(ApiResponse.success("Todo deleted successfully", null));
    }
}
