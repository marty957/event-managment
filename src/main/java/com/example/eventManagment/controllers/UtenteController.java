package com.example.eventManagment.controllers;


import com.example.eventManagment.exception.EmailDuplicateException;
import com.example.eventManagment.exception.UsernameDuplicateException;
import com.example.eventManagment.models.Eruolo;
import com.example.eventManagment.payload.UtenteDTO;
import com.example.eventManagment.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UtenteController {


    @Autowired
    UtenteService utenteService;


    @PostMapping("/newUser")
        public ResponseEntity<?> nuovoUtente (@Validated @RequestBody  UtenteDTO nuovoUtente, BindingResult validazione){
        if(validazione.hasErrors()){

            StringBuilder errori= new StringBuilder("errori nella vadilazione dati \n");
            for(ObjectError errore: validazione.getAllErrors()) {
                errori.append(errore.getDefaultMessage()).append("\n");
            }
            return new ResponseEntity<>(errori,HttpStatus.BAD_REQUEST);
        }

        nuovoUtente.setRuolo(Eruolo.UTENTE_NORMALE);
        try {
            String message=utenteService.insertUtente(nuovoUtente);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (UsernameDuplicateException e) {
           return new ResponseEntity<>("Username gia presente",HttpStatus.CONFLICT);
        } catch (EmailDuplicateException e) {
            return new ResponseEntity<>("Email gia presente",HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/newOrganizer")
    public ResponseEntity<?> nuovoOrganizzatore (@Validated @RequestBody  UtenteDTO nuovoUtente, BindingResult validazione){
        if(validazione.hasErrors()){

            StringBuilder errori= new StringBuilder("errori nella vadilazione dati \n");
            for(ObjectError errore: validazione.getAllErrors()) {
                errori.append(errore.getDefaultMessage()).append("\n");
            }
            return new ResponseEntity<>(errori,HttpStatus.BAD_REQUEST);
        }

        nuovoUtente.setRuolo(Eruolo.ORGANIZZATORE_EVENTI);
        try {
            String message=utenteService.insertUtente(nuovoUtente);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (UsernameDuplicateException e) {
            return new ResponseEntity<>("Username gia presente",HttpStatus.CONFLICT);
        } catch (EmailDuplicateException e) {
            return new ResponseEntity<>("Email gia presente",HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/newAdmin")
    public ResponseEntity<?> nuovoAdmin (@Validated @RequestBody  UtenteDTO nuovoUtente, BindingResult validazione){
        if(validazione.hasErrors()){

            StringBuilder errori= new StringBuilder("errori nella vadilazione dati \n");
            for(ObjectError errore: validazione.getAllErrors()) {
                errori.append(errore.getDefaultMessage()).append("\n");
            }
            return new ResponseEntity<>(errori,HttpStatus.BAD_REQUEST);
        }

        nuovoUtente.setRuolo(Eruolo.ADMIN);
        try {
            String message=utenteService.insertUtente(nuovoUtente);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (UsernameDuplicateException e) {
            return new ResponseEntity<>("Username gia presente",HttpStatus.CONFLICT);
        } catch (EmailDuplicateException e) {
            return new ResponseEntity<>("Email gia presente",HttpStatus.CONFLICT);
        }
    }

}
