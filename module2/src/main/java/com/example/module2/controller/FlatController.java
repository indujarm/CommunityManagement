package com.example.module2.controller;

import com.example.module2.entity.Flat;
import com.example.module2.entity.Society;
import com.example.module2.repository.FlatRepository;
import com.example.module2.repository.SocietyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flats")
public class FlatController {

    @Autowired
    private FlatRepository flatRepository;

    @Autowired
    private SocietyRepository societyRepository;

    // Get all flats
    @GetMapping
    public List<Flat> getAllFlats() {
        return flatRepository.findAll();
    }

    // Add a new flat
    @PostMapping
    public Flat addFlat(@RequestBody Flat flat) {
        Society society = societyRepository.findById(flat.getSociety().getId())
                .orElseThrow(() -> new RuntimeException("Society not found"));
        flat.setSociety(society);
        return flatRepository.save(flat);
    }

    // Get a specific flat by ID
    @GetMapping("/{id}")
    public Flat getFlatById(@PathVariable Long id) {
        return flatRepository.findById(id).orElseThrow(() -> new RuntimeException("Flat not found"));
    }

    // Update flat by ID
    @PutMapping("/{id}")
    public Flat updateFlat(@PathVariable Long id, @RequestBody Flat flat) {
        Flat existingFlat = flatRepository.findById(id).orElseThrow(() -> new RuntimeException("Flat not found"));
        existingFlat.setNumber(flat.getNumber());
        existingFlat.setSociety(flat.getSociety());
        return flatRepository.save(existingFlat);
    }
}
