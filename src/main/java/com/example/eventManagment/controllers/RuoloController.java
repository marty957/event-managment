package com.example.eventManagment.controllers;

import com.example.eventManagment.models.Eruolo;
import com.example.eventManagment.models.Ruolo;
import com.example.eventManagment.payload.request.RuoloRequest;
import com.example.eventManagment.repository.RuoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ruoli")
public class RuoloController {

    @Autowired
    private RuoloRepository ruoloRepository; // Oppure RuoloService

    @PostMapping("/nuovo")
    public ResponseEntity<?> nuovoRuolo(@RequestBody RuoloRequest ruoloRequest) {
        try {
            // Verifica se il ruolo esiste già
            if (ruoloRepository.findByNome(Eruolo.valueOf(ruoloRequest.getNome())).isPresent()) {
                return ResponseEntity.badRequest().body("Ruolo già esistente.");
            }

            Ruolo ruolo = new Ruolo();
            ruolo.setNome(Eruolo.valueOf(ruoloRequest.getNome()));
            ruoloRepository.save(ruolo); // Oppure ruoloService.creaRuolo(ruolo);

            return ResponseEntity.ok("Ruolo creato con successo.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Errore durante la creazione del ruolo.");
        }
    }
}
