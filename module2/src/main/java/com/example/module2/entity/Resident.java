package com.example.module2.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Resident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @OneToOne
    @JoinColumn(name = "flat_id")
    private Flat flat;
}
