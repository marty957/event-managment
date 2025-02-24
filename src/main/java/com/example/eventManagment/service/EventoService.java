package com.example.eventManagment.service;


import com.example.eventManagment.models.Evento;
import com.example.eventManagment.payload.EventoDTO;
import com.example.eventManagment.repository.EventoRepository;
import com.example.eventManagment.repository.UtenteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Evento eventoNuovo=eventoRepository.save(event);
       return  eventoNuovo;
    }


    public String editEvento(EventoDTO eventoDTO){
        Evento event= dto_entity(eventoDTO);
        eventoRepository.save(event);
        return "L'Evento "+ event.getNomeEvento()+ " è stato modificato";

    }

    public String deleteEvento(long id){

        eventoRepository.delete(getById(id));
        return "Evento eliminato";
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
