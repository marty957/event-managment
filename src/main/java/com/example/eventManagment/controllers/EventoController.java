package com.example.eventManagment.controllers;


import com.example.eventManagment.models.Evento;
import com.example.eventManagment.models.Utente;
import com.example.eventManagment.payload.EventoDTO;
import com.example.eventManagment.security.jwt.JwtUtils;
import com.example.eventManagment.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
@RestController
@RequestMapping("/eventi")
public class EventoController {


    @Autowired
    EventoService eventoService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/new")
    @PreAuthorize("hasAnyAuthority('ORGANIZZATORE')")
    public ResponseEntity<?> nuovoEvento(@Validated @RequestBody EventoDTO eventoDTO, BindingResult validazione, Authentication authentication) {
        if(validazione.hasErrors()){

            StringBuilder errori= new StringBuilder("errori nella vadilazione dati \n");
            for(ObjectError errore: validazione.getAllErrors()) {
                errori.append(errore.getDefaultMessage()).append("\n");
            }
            return new ResponseEntity<>(errori.toString(),HttpStatus.BAD_REQUEST);
        }
        try {
            Utente organizzatore = (Utente) authentication.getPrincipal();
            if (organizzatore == null) {
                return new ResponseEntity<>("Organizzatore non trovato", HttpStatus.UNAUTHORIZED);
            }
            eventoDTO.setOrganizzatore(organizzatore);
            Evento eventoCreato = eventoService.insertEvento(eventoDTO);
            return new ResponseEntity<>(eventoCreato, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Dati non validi: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Errore durante la creazione dell'evento: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }}
*/
