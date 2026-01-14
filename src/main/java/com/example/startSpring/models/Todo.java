package com.example.startSpring.models;

import jakarta.persistence.*; // The library for database mapping
import lombok.Data;

@Entity // Tells Spring Boot: "This class is a table in MySQL"
@Table(name = "todos") // Optional: Names the table 'todos' in MySQL
@Data
public class Todo {

    @Id // Marks this field as the Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment (1, 2, 3...)
    private Long id;

    @Column(nullable = false) // This column cannot be empty
    private String title;

    private boolean completed = false; // Default value is false

    private String description;

//    // IMPORTANT: In Java, you need a default (empty) constructor for the DB to work
//    public Todo() {}
//
//    // Getters and Setters: How Java reads and writes these private variables
//    // Think of these like 'Object.defineProperty' in JavaScript
//    public Long getId() { return id; }
//    public void setId(Long id) { this.id = id; }
//
//    public String getTitle() { return title; }
//    public void setTitle(String title) { this.title = title; }
//
//    public boolean isCompleted() { return completed; }
//    public void setCompleted(boolean completed) { this.completed = completed; }
//
//    public String getDescription() { return description; }
//    public void setDescription(String description) { this.description = description; }



}