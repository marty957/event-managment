package com.example.eventManagment.controllers;


import com.example.eventManagment.exception.EmailDuplicateException;
import com.example.eventManagment.exception.UsernameDuplicateException;
import com.example.eventManagment.models.Eruolo;
import com.example.eventManagment.models.Evento;
import com.example.eventManagment.models.Utente;
import com.example.eventManagment.payload.EventoDTO;
import com.example.eventManagment.payload.UtenteDTO;
import com.example.eventManagment.payload.request.LoginRequest;
import com.example.eventManagment.payload.request.RegistrazioneRequest;
import com.example.eventManagment.payload.response.JwtResponse;
import com.example.eventManagment.security.jwt.JwtUtils;
import com.example.eventManagment.security.service.UserDetailsmpl;
import com.example.eventManagment.service.EventoService;
import com.example.eventManagment.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UtenteController {


    @Autowired
    UtenteService utenteService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    EventoService eventoService;


    @PostMapping("/registrazione")
        public ResponseEntity<?> nuovoUtente (@Validated @RequestBody RegistrazioneRequest nuovoUtente, BindingResult validazione){
        if(validazione.hasErrors()){

            StringBuilder errori= new StringBuilder("errori nella vadilazione dati \n");
            for(ObjectError errore: validazione.getAllErrors()) {
                errori.append(errore.getDefaultMessage()).append("\n");
            }
            return new ResponseEntity<>(errori.toString(),HttpStatus.BAD_REQUEST);
        }
        Map<String, String> response = new HashMap<>();

        try {
            String message = utenteService.insertUtente(nuovoUtente);
            response.put("message", message);

            return ResponseEntity.ok(response);
        } catch (UsernameDuplicateException e) {
           return new ResponseEntity<>("Username gia presente",HttpStatus.BAD_REQUEST);
        } catch (EmailDuplicateException e) {
            return new ResponseEntity<>("Email gia presente",HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login (@Validated @RequestBody LoginRequest loginDTO, BindingResult validazione) {
        if (validazione.hasErrors()) {

            StringBuilder errori = new StringBuilder("errori nella vadilazione dati \n");
            for (ObjectError errore : validazione.getAllErrors()) {
                errori.append(errore.getDefaultMessage()).append("\n");
            }
            return new ResponseEntity<>(errori, HttpStatus.BAD_REQUEST);
        }
        UsernamePasswordAuthenticationToken tokenAuth= new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),loginDTO.getPassword());

        Authentication authentication= authenticationManager.authenticate(tokenAuth);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String finalToken=jwtUtils.creaJwtToken(authentication);

        UserDetailsmpl detailUser= (UserDetailsmpl) authentication.getPrincipal();

        List<String> ruoli=detailUser.getAuthorities().stream()
                .map(item->item.getAuthority())
                .collect(Collectors.toList());

        JwtResponse jwtResponse=new JwtResponse(detailUser.getUsername(),detailUser.getId(),detailUser.getEmail(),ruoli,finalToken);


        return new ResponseEntity<>(jwtResponse,HttpStatus.OK);
    }
    @PostMapping("/newEvento")
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    public ResponseEntity<?> nuovoEvento(@Validated @RequestBody EventoDTO eventoDTO, BindingResult validazione, Authentication authentication) {
        if(validazione.hasErrors()){

            StringBuilder errori= new StringBuilder("errori nella vadilazione dati \n");
            for(ObjectError errore: validazione.getAllErrors()) {
                errori.append(errore.getDefaultMessage()).append("\n");
            }
            return new ResponseEntity<>(errori.toString(),HttpStatus.BAD_REQUEST);
        }
        try {
            Evento evento = eventoService.insertEvento(eventoDTO);
            return new ResponseEntity<>(evento, HttpStatus.CREATED);
        }
        catch (Exception e ){
            throw new RuntimeException("dati non validi");
        }
    /*    try {
            Utente organizzatore = (Utente) authentication.getPrincipal();
            if (organizzatore == null) {
                return new ResponseEntity<>("Organizzatore non trovato", HttpStatus.UNAUTHORIZED);
            }
            Evento eventoCreato = eventoService.insertEvento(eventoDTO);
            return new ResponseEntity<>(eventoCreato, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Dati non validi: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Errore durante la creazione dell'evento: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/


    }}




