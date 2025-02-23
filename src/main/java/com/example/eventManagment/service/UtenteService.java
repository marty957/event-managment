package com.example.eventManagment.service;


import com.example.eventManagment.exception.EmailDuplicateException;
import com.example.eventManagment.exception.UsernameDuplicateException;
import com.example.eventManagment.models.Eruolo;
import com.example.eventManagment.models.Ruolo;
import com.example.eventManagment.models.Utente;
import com.example.eventManagment.payload.UtenteDTO;
import com.example.eventManagment.payload.request.RegistrazioneRequest;
import com.example.eventManagment.repository.RuoloRepository;
import com.example.eventManagment.repository.UtenteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Service
@Transactional
public class UtenteService {


    @Autowired
    UtenteRepository utenteRepository;
    @Autowired
    private RuoloRepository ruoloRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    public String insertUtente(RegistrazioneRequest userDTO) throws UsernameDuplicateException, EmailDuplicateException {

        checkDuplicateKey(userDTO.getUsername(),userDTO.getEmail());
        Utente user=dto_entity(userDTO);
        Ruolo ruolo=ruoloRepository.findByNome(Eruolo.UTENTE)
                .orElseThrow(()-> new RuntimeException("ruolo non trovatp"));
        user.setRuoli(new HashSet<>(Set.of(ruolo)));
        user= utenteRepository.save(user);
        return "L'utente " + user.getUsername() +" è stato inserito";
    }


    public void checkDuplicateKey( String username, String email) throws EmailDuplicateException, UsernameDuplicateException {

        if(utenteRepository.existsByUsername(username)){
            throw new UsernameDuplicateException();
        }

        if(utenteRepository.existsByEmail(email)){
            throw new EmailDuplicateException();
        }

    }


    public boolean hasRole(Utente utente, Eruolo ruoloRichiesto){
        return utente.getRuoli().equals(ruoloRichiesto);
    }



    // travasi

/*    public Utente dto_entity(UtenteDTO utenteDTO) {

        Utente utente = new Utente();
        utente.setCognome(utenteDTO.getCognome());
        utente.setNome(utenteDTO.getNome());
        utente.setUsername(utenteDTO.getUsername());
        utente.setEmail(utenteDTO.getEmail());
        utente.setPassword(utenteDTO.getPassword());

        return utente;

    }*/


    public UtenteDTO entity_dto(Utente utente){

        UtenteDTO utenteDTO =new UtenteDTO();
        utenteDTO.setCognome(utente.getCognome());
        utenteDTO.setNome(utente.getNome());
        utenteDTO.setEmail(utente.getEmail());
        utenteDTO.setUsername(utente.getUsername());
        return utenteDTO;
    }
    public Utente dto_entity(RegistrazioneRequest userDTO) {
        Utente utente = new Utente();
        utente.setCognome(userDTO.getCognome());
        utente.setNome(userDTO.getNome());
        utente.setUsername(userDTO.getUsername());
        utente.setEmail(userDTO.getEmail());
        utente.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return utente;
    }

}
