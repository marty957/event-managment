package com.example.eventManagment.repository;

import com.example.eventManagment.models.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtenteRepository extends JpaRepository<Utente,Long> {


    public Optional<Utente> findByUsername(String username);

    public boolean existsByUsernameAndPassword(String username,String password);
    public boolean existsByUsername (String username);
    public boolean existsByEmail(String email);


}
