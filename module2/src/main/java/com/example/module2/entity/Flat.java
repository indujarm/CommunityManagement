package com.example.module2.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Flat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;

    @ManyToOne
    @JoinColumn(name = "society_id")
    private Society society;

    @OneToOne(mappedBy = "flat", cascade = CascadeType.ALL)
    private Resident resident;
}
