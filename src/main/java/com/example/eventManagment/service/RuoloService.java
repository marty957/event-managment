package com.example.eventManagment.service;


import com.example.eventManagment.models.Ruolo;
import com.example.eventManagment.repository.RuoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RuoloService {

    @Autowired
    RuoloRepository ruoloRepository;

    public void insertRuolo(Ruolo ruolo)
    {
        ruoloRepository.save(ruolo);
    }
}
