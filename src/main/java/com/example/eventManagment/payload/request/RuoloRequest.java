package com.example.eventManagment.payload.request;

import lombok.Data;

@Data
public class RuoloRequest {
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}