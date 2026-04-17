package com.example.startSpring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "todos")
@Data
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required") // Validation: Title cannot be empty
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    @Column(nullable = false)
    private String title;

    private boolean completed = false;

    @Size(max = 800, message = "Description cannot exceed 800 characters")
    private String description;

    // Link Todo to User (Many Todos belong to One User)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore // Don't include the full user object in the JSON response (prevents infinite loops)
    private User user;
}
