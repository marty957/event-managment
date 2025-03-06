package com.example.eventManagment.service;


import com.example.eventManagment.models.Evento;
import com.example.eventManagment.payload.EventoDTO;
import com.example.eventManagment.repository.EventoRepository;
import com.example.eventManagment.repository.UtenteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.Optional;

@Service
@Transactional
public class EventoService {

    @Autowired
    EventoRepository eventoRepository;
    @Autowired
    UtenteRepository utenteRepository;


    public Evento getById(long id){
        return (Evento) eventoRepository.findById(id).orElseThrow();
    }

    public Evento insertEvento(EventoDTO evento){

        Evento event= dto_entity(evento);
        try {
            // Salvataggio dell'evento
            Evento eventoNuovo=eventoRepository.save(event);

            return eventoNuovo;

        } catch (Exception e) {
            // Gestione di eccezioni generiche
            throw new RuntimeException("Errore imprevisto durante il salvataggio dell'evento.");
        }


    }

    public String deleteEvento(Long id){

     Evento evento= eventoRepository.findById(id).orElseThrow();
     eventoRepository.delete(evento);
     return "evento eliminito correttamente";
    }


    public void deleteEvento(long id, String username) {
        Optional<Evento> optionalEvento = eventoRepository.findById(id);

        if (optionalEvento.isEmpty()) {
            throw new EntityNotFoundException("Evento non trovato.");
        }

        Evento evento = optionalEvento.get();

        if (!evento.getOrganizzatore().getUsername().equals(username)) {
            try {
                throw new AccessDeniedException("Non sei autorizzato ad eliminare questo evento.");
            } catch (AccessDeniedException e) {
                throw new RuntimeException(e);
            }
        }

        eventoRepository.deleteById(id);
    }


    //travaso

    public Evento dto_entity(EventoDTO eventoDTO){

        Evento evento= new Evento();

        evento.setDataEvento(eventoDTO.getDataEvento());
        evento.setLuogoEvento(eventoDTO.getLuogoEvento());
        evento.setNumeroPostiDisponibili(eventoDTO.getNumeroPostiDisponibili());
        evento.setDescrizione(eventoDTO.getDescrizione());
        evento.setNomeEvento(eventoDTO.getNomeEvento());
        evento.setOrganizzatore(utenteRepository.findById(eventoDTO.getOrganizzatoreId()).orElseThrow());
        return evento;
    }

    public  EventoDTO entity_dto(Evento evento){
        EventoDTO eventoDTO = new EventoDTO();
        evento.setNomeEvento(eventoDTO.getNomeEvento());
        evento.setLuogoEvento(eventoDTO.getLuogoEvento());
        evento.setDescrizione(evento.getDescrizione());
        return eventoDTO;
    }
}
