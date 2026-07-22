package com.rememberwhen.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// This is the entry point of the whole app - running this class starts everything:
// the embedded web server, all controllers, services, and database connections
@SpringBootApplication
public class RememberWhenApplication {

    public static void main(String[] args) {
        SpringApplication.run(RememberWhenApplication.class, args);
    }
}
