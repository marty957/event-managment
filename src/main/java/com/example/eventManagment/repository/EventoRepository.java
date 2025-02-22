package com.example.eventManagment.repository;

import com.example.eventManagment.models.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento,Long> {
}
