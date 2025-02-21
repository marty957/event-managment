package com.example.eventManagment.repository;

import com.example.eventManagment.models.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtenteRepository extends JpaRepository<Utente,Long> {
}
