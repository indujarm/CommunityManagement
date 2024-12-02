package com.example.module2.controller;

import com.example.module2.entity.Flat;
import com.example.module2.repository.FlatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flats")
public class FlatController {

    @Autowired
    private FlatRepository flatRepository;

    // Get all flats
    @GetMapping
    public List<Flat> getAllFlats() {
        return flatRepository.findAll();
    }

    // Add a new flat
    @PostMapping
    public Flat addFlat(@RequestBody Flat flat) {
        return flatRepository.save(flat);
    }

    // Get a specific flat by ID
    @GetMapping("/{id}")
    public Flat getFlatById(@PathVariable Long id) {
        return flatRepository.findById(id).orElseThrow(() -> new RuntimeException("Flat not found"));
    }
}
