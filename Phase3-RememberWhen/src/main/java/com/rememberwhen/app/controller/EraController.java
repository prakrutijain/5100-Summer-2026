package com.rememberwhen.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rememberwhen.app.model.EraItem;
import com.rememberwhen.app.service.EraService;

// Exposes GET /api/era?year=1990&country=India as requested in the assignment brief
@RestController
@RequestMapping("/api/era")
@CrossOrigin(origins = "http://localhost:3000")
public class EraController {

    @Autowired
    private EraService eraService;

    @GetMapping
    public ResponseEntity<?> getEraItems(@RequestParam int year, @RequestParam String country) {
        try {
            List<EraItem> items = eraService.getMoviesByYear(year, country);
            return ResponseEntity.ok(items);

        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Failed to fetch era data: " + e.getMessage());
            return ResponseEntity.internalServerError().body(error);
        }
    }
}