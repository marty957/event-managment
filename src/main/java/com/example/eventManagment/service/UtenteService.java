package com.example.eventManagment.service;


import com.example.eventManagment.models.Utente;
import com.example.eventManagment.payload.UtenteDTO;
import com.example.eventManagment.repository.UtenteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UtenteService {


    @Autowired
    UtenteRepository utenteRepository;





    // travasi

    public UtenteDTO dto_entity(Utente utente) {

        UtenteDTO utenteDTO = new UtenteDTO();

        utenteDTO.setCognome(utente.getCognome());
        utenteDTO.setNome(utente.getNome());
        utenteDTO.setUsername(utente.getUsername());
        utenteDTO.setEmail(utente.getEmail());
        return utenteDTO;

    }




    public Utente entity_dto(UtenteDTO utenteDTO){

        Utente utente =new Utente();

        utente.setCognome(utenteDTO.getCognome());
        utente.setNome(utenteDTO.getNome());
        utente.setEmail(utenteDTO.getEmail());
        utente.setUsername(utenteDTO.getUsername());
        return utente;
    }

}
