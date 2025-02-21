package com.example.eventManagment.payload;


import com.example.eventManagment.models.Evento;
import com.example.eventManagment.models.Utente;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PrenotazioneDTO {

    @NotNull(message = "campo obbligatorio")
    private Long idEvento;

   @NotNull(message = "campo obbligatorio")
   @Min(value = 1)
   private int numeroDiPostiPrenotati;

}
