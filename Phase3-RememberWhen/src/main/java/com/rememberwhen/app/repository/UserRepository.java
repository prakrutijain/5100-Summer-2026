package com.rememberwhen.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rememberwhen.app.model.User;

// Handles all database operations for the "users" table - no SQL needed!
public interface UserRepository extends JpaRepository<User, Long> {

    // Spring Data JPA automatically writes the SQL for this just from the method name
    Optional<User> findByEmail(String email);

    // Quick check used during signup, to reject duplicate emails
    boolean existsByEmail(String email);
}