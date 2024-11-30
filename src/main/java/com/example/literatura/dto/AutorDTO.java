package com.example.literatura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AutorDTO (
    @JsonAlias("name") String nombre,
    @JsonAlias("birth_year") String fechaDeNacimiento,
    @JsonAlias("death_year") String fechaDeMuerte
){
}
