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
    private RuoloRepository ruoloRepository;

    @PostMapping("/nuovo")
    public ResponseEntity<?> nuovoRuolo(@RequestBody RuoloRequest ruoloRequest) {
        try {
            Eruolo ruoloEnum = Eruolo.valueOf(ruoloRequest.getNome().toUpperCase()); // Converti in maiuscolo

            if (ruoloRepository.findByNome(ruoloEnum).isPresent()) {
                return ResponseEntity.badRequest().body("Ruolo già esistente.");
            }

            Ruolo ruolo = new Ruolo();
            ruolo.setNome(ruoloEnum);
            ruoloRepository.save(ruolo);

            return ResponseEntity.ok("Ruolo creato con successo.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Ruolo non valido. I ruoli validi sono: " + java.util.Arrays.asList(Eruolo.values()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Errore durante la creazione del ruolo.");
        }
    }
}
