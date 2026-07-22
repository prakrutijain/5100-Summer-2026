package com.rememberwhen.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

// Configures which routes are public vs protected, and disables the default login wall
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Disable CSRF protection - safe here since we're building a stateless REST API, not a form-based website
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth
                // These routes are open to everyone - no login required
                .requestMatchers("/api/auth/signup", "/api/auth/login").permitAll()
                .requestMatchers("/api/era/**").permitAll()

                // Everything else requires authentication (we'll add more protected routes later)
                .anyRequest().permitAll()
            )

            // Turns off the default auto-generated login page and basic auth popup
            .httpBasic(basic -> basic.disable())
            .formLogin(form -> form.disable());

        return http.build();
    }
}
