package com.rememberwhen.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rememberwhen.app.model.User;
import com.rememberwhen.app.repository.UserRepository;

// Handles the actual logic for signing up and logging in users
@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    // BCrypt turns "mypassword123" into a scrambled, one-way hash - never store plain text passwords
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User signup(String email, String rawPassword, Integer birthYear, String country) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("An account with this email already exists.");
        }

        String hashedPassword = passwordEncoder.encode(rawPassword);

        User newUser = new User(email, hashedPassword);
        newUser.setBirthYear(birthYear);
        newUser.setCountry(country);

        return userRepository.save(newUser);
    }

    public User login(String email, String rawPassword) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("No account found with this email.");
        }

        User user = userOptional.get();

        // .matches() re-hashes the entered password and compares it to the stored hash
        // it never "un-hashes" anything - hashing is one-way by design
        boolean passwordMatches = passwordEncoder.matches(rawPassword, user.getPassword());

        if (!passwordMatches) {
            throw new IllegalArgumentException("Incorrect password.");
        }

        return user;
    }
}