package com.example.eventManagment.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "prenotazione")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_prenotazione;

    @ManyToOne
    @JoinColumn(name = "id_evento", nullable = false)
    private Evento evento;
    @ManyToOne
    @JoinColumn(name = "id_utente", nullable = false)
    private Utente utente;
    @Column(nullable = false)
    private int numeroDiPostiPrenotati;
    @Column(nullable = false)
    private LocalDate dataPrenotazione;

    public Prenotazione(Evento evento, Utente utente, int numeroDiPostiPrenotati, LocalDate dataPrenotazione) {
        this.evento = evento;
        this.utente = utente;
        this.numeroDiPostiPrenotati = numeroDiPostiPrenotati;
        this.dataPrenotazione =LocalDate.now();
    }
}
