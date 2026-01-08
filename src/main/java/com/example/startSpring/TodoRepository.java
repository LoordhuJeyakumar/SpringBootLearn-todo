package com.example.startSpring;

import org.springframework.stereotype.Component;

@Component
public class TodoRepository {
    public void save(String task) {
        System.out.println("Saved to DB: " + task);
    }
}

