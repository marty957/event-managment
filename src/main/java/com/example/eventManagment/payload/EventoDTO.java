package com.example.eventManagment.payload;

import com.example.eventManagment.models.Utente;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventoDTO {

    @NotBlank(message = "campo obbligatorio")
    private String nomeEvento;

    private String descrizione;
    @NotNull
    private Long organizzatoreId;

    private LocalDate dataEvento;

    @NotBlank(message = "campo obbligatorio")
    private String luogoEvento;

    @JsonProperty("numeroPostiDisponibili")
    @NotNull(message = "campo obbligatorio")
    @Min(value = 10, message = "deve essere un numero uguale o superiore a 10")
    private Integer numeroPostiDisponibili;


}
