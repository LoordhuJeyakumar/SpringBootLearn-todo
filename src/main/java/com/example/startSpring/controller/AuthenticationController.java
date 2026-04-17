package com.example.startSpring.controller;

import com.example.startSpring.dto.ApiResponse;
import com.example.startSpring.dto.AuthenticationRequest;
import com.example.startSpring.dto.AuthenticationResponse;
import com.example.startSpring.dto.RegisterRequest;
import com.example.startSpring.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(
                ApiResponse.success("User registered successfully", service.register(request))
        );
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(
                ApiResponse.success("Login successful", service.authenticate(request))
        );
    }
}
