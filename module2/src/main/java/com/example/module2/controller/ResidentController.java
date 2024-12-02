package com.example.module2.controller;

import com.example.module2.entity.Resident;
import com.example.module2.repository.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/residents")
public class ResidentController {

    @Autowired
    private ResidentRepository residentRepository;

    // Get all residents
    @GetMapping
    public List<Resident> getAllResidents() {
        return residentRepository.findAll();
    }

    // Add a new resident
    @PostMapping
    public Resident addResident(@RequestBody Resident resident) {
        return residentRepository.save(resident);
    }

    // Get a specific resident by ID
    @GetMapping("/{id}")
    public Resident getResidentById(@PathVariable Long id) {
        return residentRepository.findById(id).orElseThrow(() -> new RuntimeException("Resident not found"));
    }
}
