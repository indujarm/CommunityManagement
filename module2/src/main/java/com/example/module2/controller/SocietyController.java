package com.example.module2.controller;

import com.example.module2.entity.Society;
import com.example.module2.repository.SocietyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/societies")
public class SocietyController {

    @Autowired
    private SocietyRepository societyRepository;

    // Get all societies
    @GetMapping
    public List<Society> getAllSocieties() {
        return societyRepository.findAll();
    }

    // Add a new society
    @PostMapping
    public Society addSociety(@RequestBody Society society) {
        return societyRepository.save(society);
    }

    // Get a specific society by ID
    @GetMapping("/{id}")
    public Society getSocietyById(@PathVariable Long id) {
        return societyRepository.findById(id).orElseThrow(() -> new RuntimeException("Society not found"));
    }

    // Update society by ID
    @PutMapping("/{id}")
    public Society updateSociety(@PathVariable Long id, @RequestBody Society society) {
        Society existingSociety = societyRepository.findById(id).orElseThrow(() -> new RuntimeException("Society not found"));
        existingSociety.setName(society.getName());
        existingSociety.setAddress(society.getAddress());
        return societyRepository.save(existingSociety);
    }
}
