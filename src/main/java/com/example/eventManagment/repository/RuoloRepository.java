package com.example.eventManagment.repository;

import com.example.eventManagment.models.Eruolo;
import com.example.eventManagment.models.Ruolo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RuoloRepository extends JpaRepository<Ruolo,Long> {
    Optional<Ruolo> findByNome(Eruolo nome);
    public boolean existsByNome(Eruolo nome);
}
