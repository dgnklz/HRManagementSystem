package com.dgnklz.hrmanagementsystem.controllers;

import com.dgnklz.hrmanagementsystem.services.abstracts.UserService;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.security.SigninRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.security.SignupRequest;
import jakarta.validation.Valid;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// http://localhost:8080/api/auth/signup
// http://localhost:8080/api/auth/signin



@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private UserService service;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequest request) {
        return service.signup(request);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@Valid @RequestBody SigninRequest request) {
        return service.signin(request);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        return service.logout();
    }
}