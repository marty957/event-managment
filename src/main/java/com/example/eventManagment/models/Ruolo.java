package com.example.eventManagment.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Ruolo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idruolo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Eruolo nome;


}
