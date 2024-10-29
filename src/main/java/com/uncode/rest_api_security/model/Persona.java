package com.uncode.rest_api_security.model;

import java.util.ArrayList;
import java.util.List;

import com.uncode.rest_api_security.service.Identifiable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Entity
@Data
public class Persona implements Identifiable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "El nombre de la persona no puede estar vacío")
    private String nombre;
    @NotBlank(message = "El apellido de la persona no puede estar vacío")
    private String apellido;
    @NotNull(message = "El DNI de la persona no puede estar vacío")
    @Positive(message = "El DNI de la persona debe ser un número positivo")
    private Integer dni;
    @NotNull(message = "El domicilio de la persona no puede estar vacío")
    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Domicilio domicilio;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Libro> libros = new ArrayList<>();

}
