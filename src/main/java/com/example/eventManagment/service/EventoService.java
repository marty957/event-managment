package com.example.eventManagment.service;


import com.example.eventManagment.models.Evento;
import com.example.eventManagment.payload.EventoDTO;
import com.example.eventManagment.repository.EventoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class EventoService {

    @Autowired
    EventoRepository eventoRepository;


    public Evento getById(long id){
        return (Evento) eventoRepository.findById(id).orElseThrow();
    }

    public String insertEvento(Evento evento){
        eventoRepository.save(evento);
        return "L'evento " + evento.getNomeEvento()+ " è stato inserito correttamente";
    }




    //travaso

    public Evento entity_dto(EventoDTO eventoDTO){

        Evento evento= new Evento();

        evento.setDataEvento(eventoDTO.getDataEvento());
        evento.setLuogoEvento(eventoDTO.getLuogoEvento());
        evento.setNumeroPostiDisponibili(eventoDTO.getNumeroPostiDisponibili());
        evento.setDescrizione(eventoDTO.getDescrizione());
        evento.setNomeEvento(eventoDTO.getNomeEvento());
        return evento;
    }

    public  EventoDTO dto_entity(Evento evento){
        EventoDTO eventoDTO = new EventoDTO();
        evento.setNomeEvento(eventoDTO.getNomeEvento());
        evento.setLuogoEvento(eventoDTO.getLuogoEvento());
        evento.setDescrizione(evento.getDescrizione());
        return eventoDTO;
    }
}
