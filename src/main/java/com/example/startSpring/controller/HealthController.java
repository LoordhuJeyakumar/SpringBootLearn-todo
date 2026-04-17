package com.example.startSpring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * HealthController
 * ----------------
 * Provides a public endpoint to verify the application is running.
 * This is useful for Render's health checks and for users to verify deployment.
 */
@RestController
@RequestMapping("/api/health")
public class HealthController {

    @GetMapping
    public Map<String, Object> healthCheck() {
        return Map.of(
            "status", "UP",
            "message", "Todo Backend is running smoothly",
            "timestamp", LocalDateTime.now(),
            "environment", "Production Ready"
        );
    }
}
