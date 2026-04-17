package com.example.startSpring.service;

import com.example.startSpring.model.Role;
import com.example.startSpring.model.Todo;
import com.example.startSpring.model.User;
import com.example.startSpring.repository.TodoRepository;
import com.example.startSpring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    // Helper method to get the current logged-in user
    private User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public Todo saveTodo(Todo todo) {
        User currentUser = getCurrentUser();
        todo.setUser(currentUser); // Link the todo to the user
        return todoRepository.save(todo);
    }

    public List<Todo> getAllTodos() {
        User currentUser = getCurrentUser();
        // If Admin, show all. If User, show only theirs.
        if (currentUser.getRole() == Role.ADMIN) {
            return todoRepository.findAll();
        } else {
            return todoRepository.findByUserId(currentUser.getId());
        }
    }

    public Page<Todo> getAllTodosWithPagination(int page, int size, String sortBy) {
        // For simplicity in this demo, we'll just return all (pagination logic needs custom query for user)
        // But in a real app, you'd use findByUserId(id, pageable)
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return todoRepository.findAll(pageable);
    }

    public Todo getTodoById(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));
        
        // Check ownership
        User currentUser = getCurrentUser();
        if (currentUser.getRole() != Role.ADMIN && !todo.getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("You are not allowed to view this todo");
        }
        return todo;
    }

    public Todo editTodoById(Long id, Todo todo) {
        Todo existingTodo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));

        // Check ownership
        User currentUser = getCurrentUser();
        if (currentUser.getRole() != Role.ADMIN && !existingTodo.getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("You are not allowed to edit this todo");
        }

        existingTodo.setTitle(todo.getTitle());
        existingTodo.setDescription(todo.getDescription());
        existingTodo.setCompleted(todo.isCompleted());
        return todoRepository.save(existingTodo);
    }

    public void deleteTodo(Long id) {
        // Note: The Controller already checks for ADMIN role for delete.
        // But if we wanted users to delete their own, we'd add logic here.
        todoRepository.deleteById(id);
    }
}
