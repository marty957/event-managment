package com.example.eventManagment.service;


import com.example.eventManagment.models.Prenotazione;
import com.example.eventManagment.payload.PrenotazioneDTO;
import com.example.eventManagment.repository.PrenotazioneRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PrenotazioneService {

    @Autowired
    PrenotazioneRepository prenotazioneRepository;
    @Autowired
    EventoService eventoService;


    public String insertPrenotazione(Prenotazione prenotazione){
       prenotazioneRepository.save(prenotazione);
       return "La Prenotazione è stata eseguita";
    }





    // travasi


    public Prenotazione entinty_dto(PrenotazioneDTO prenotazioneDTO){
        Prenotazione prenotazione= new Prenotazione();

        prenotazione.setNumeroDiPostiPrenotati(prenotazioneDTO.getNumeroDiPostiPrenotati());
        prenotazione.setEvento(eventoService.getById(prenotazioneDTO.getIdEvento()));
      return prenotazione;
    }


    public PrenotazioneDTO dto_entinty(Prenotazione prenotazione){
        PrenotazioneDTO prenotazioneDTO=new PrenotazioneDTO();

        prenotazioneDTO.setIdEvento(prenotazioneDTO.getIdEvento());
        prenotazioneDTO.setNumeroDiPostiPrenotati(prenotazione.getNumeroDiPostiPrenotati());
        return prenotazioneDTO;
    }
}
