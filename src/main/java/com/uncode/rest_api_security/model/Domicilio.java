package com.uncode.rest_api_security.model;

import com.uncode.rest_api_security.service.Identifiable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Entity
@Data
public class Domicilio implements Identifiable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "La calle del domicilio no puede estar vacía")
    private String calle;
    @NotNull(message = "El número del domicilio no puede estar vacío")
    @Positive(message = "El número del domicilio debe ser un número positivo")
    private String numero;
    @ManyToOne(optional = false)
    private Localidad localidad;
}