package com.uncode.rest_api_security.model;

import java.time.LocalDate;

import com.uncode.rest_api_security.service.Identifiable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Entity
@Data
public class Libro implements Identifiable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String titulo;
    @NotNull(message = "La fecha de publicación no puede estar vacía")
    private LocalDate fechaPublicacion;
    @NotBlank
    private String genero;
    @NotNull
    @Positive
    private Integer paginas;
    @NotBlank
    private String autor;

}
