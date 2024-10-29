package com.uncode.rest_api_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uncode.rest_api_security.model.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

    boolean existsByDni(Integer dni);

}
