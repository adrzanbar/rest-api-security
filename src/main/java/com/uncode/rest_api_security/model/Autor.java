package com.uncode.rest_api_security.model;

import com.uncode.rest_api_security.service.Identifiable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Autor implements Identifiable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "El nombre del autor no puede estar vacío")
    private String nombre;
    @NotBlank(message = "El apellido del autor no puede estar vacío")
    private String apellido;
    private String biografia;

}
