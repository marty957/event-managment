package com.example.eventManagment.service;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PrenotazioneService {

    @Autowired
    PrenotazioneService prenotazioneService;
}
