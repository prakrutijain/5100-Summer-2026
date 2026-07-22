package com.rememberwhen.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rememberwhen.app.model.User;
import com.rememberwhen.app.service.AuthService;

// Exposes /api/auth/signup and /api/auth/login as REST endpoints for the React frontend
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000") // allows our React app (port 3000) to call this API
public class AuthController {

    @Autowired
    private AuthService authService;

    // Handles POST requests to /api/auth/signup
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request) {
        try {
            User user = authService.signup(
                request.getEmail(),
                request.getPassword(),
                request.getBirthYear(),
                request.getCountry()
            );

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Account created successfully");
            response.put("email", user.getEmail());
            response.put("birthYear", user.getBirthYear());
            response.put("country", user.getCountry());

            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    // Handles POST requests to /api/auth/login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            User user = authService.login(request.getEmail(), request.getPassword());

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("email", user.getEmail());
            response.put("birthYear", user.getBirthYear());
            response.put("country", user.getCountry());

            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(401).body(error);
        }
    }

    // Inner class representing the JSON body sent from React for signup
    public static class SignupRequest {
        private String email;
        private String password;
        private Integer birthYear;
        private String country;

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
        public Integer getBirthYear() { return birthYear; }
        public void setBirthYear(Integer birthYear) { this.birthYear = birthYear; }
        public String getCountry() { return country; }
        public void setCountry(String country) { this.country = country; }
    }

    // Inner class representing the JSON body sent from React for login
    public static class LoginRequest {
        private String email;
        private String password;

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }
}