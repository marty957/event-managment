package com.example.eventManagment.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "utenti")
@Data
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id_utente;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String cognome;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false,unique = true)
    private String email;
    @Enumerated (EnumType.STRING)
    private Eruolo ruolo;

    @OneToMany(mappedBy = "organizzatore")
    private List<Evento> eventiCreati;

    @OneToMany(mappedBy = "utente")
    private List<Prenotazione> prenotazioni;

}
