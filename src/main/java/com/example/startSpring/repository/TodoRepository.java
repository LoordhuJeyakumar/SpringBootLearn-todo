package com.example.startSpring.repository;

import com.example.startSpring.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    // Find all todos that belong to a specific user ID
    List<Todo> findByUserId(Long userId);
}
