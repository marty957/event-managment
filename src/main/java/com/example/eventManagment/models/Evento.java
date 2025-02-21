package com.example.eventManagment.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Table(name = "eventi")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_evento;
    @Column(nullable = false)
    private String nomeEvento;
    private String descrizione;
    @Column(nullable = false)
    private LocalDate dataEvento;
    @Column(nullable = false)
    private String luogoEvento;
    @Column(nullable = false)
    private int numeroPostiDisponibili;


   @ManyToOne
   @JoinColumn(name = "creatore",nullable = false)
    private Utente organizzatore;

}
