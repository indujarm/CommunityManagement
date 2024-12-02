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

    @GetMapping
    public List<Society> getAllSocieties() {
        return societyRepository.findAll();
    }

    @PostMapping
    public Society addSociety(@RequestBody Society society) {
        return societyRepository.save(society);
    }
}
