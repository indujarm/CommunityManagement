package com.example.module2.controller;

import com.example.module2.entity.Resident;
import com.example.module2.entity.Flat;
import com.example.module2.repository.ResidentRepository;
import com.example.module2.repository.FlatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/residents")
public class ResidentController {

    @Autowired
    private ResidentRepository residentRepository;

    @Autowired
    private FlatRepository flatRepository;

    // Get all residents
    @GetMapping
    public List<Resident> getAllResidents() {
        return residentRepository.findAll();
    }

    // Add a new resident
    @PostMapping
    public Resident addResident(@RequestBody Resident resident) {
        Flat flat = flatRepository.findById(resident.getFlat().getId())
                .orElseThrow(() -> new RuntimeException("Flat not found"));
        resident.setFlat(flat);
        return residentRepository.save(resident);
    }

    // Get a specific resident by ID
    @GetMapping("/{id}")
    public Resident getResidentById(@PathVariable Long id) {
        return residentRepository.findById(id).orElseThrow(() -> new RuntimeException("Resident not found"));
    }

    // Update resident by ID
    @PutMapping("/{id}")
    public Resident updateResident(@PathVariable Long id, @RequestBody Resident resident) {
        Resident existingResident = residentRepository.findById(id).orElseThrow(() -> new RuntimeException("Resident not found"));
        existingResident.setName(resident.getName());
        existingResident.setEmail(resident.getEmail());
        existingResident.setFlat(resident.getFlat());
        return residentRepository.save(existingResident);
    }
}
