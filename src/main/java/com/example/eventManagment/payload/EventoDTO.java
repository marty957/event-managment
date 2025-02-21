package com.example.eventManagment.payload;

import com.example.eventManagment.models.Utente;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EventoDTO {

    @NotBlank(message = "campo obbligatorio")
    private String nomeEvento;

    private String descrizione;

    @NotEmpty(message = "la data deve essere inserita")
    private LocalDate dataEvento;

    @NotBlank(message = "campo obbligatorio")
    private String luogoEvento;

    @NotNull(message = "campo obbligatorio")
    @Min(value = 1, message = "deve essere un numero uguale o superiore a 1")
    private int numeroPostiDisponibili;


}
