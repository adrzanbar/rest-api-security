package com.uncode.rest_api_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uncode.rest_api_security.model.Domicilio;
import com.uncode.rest_api_security.model.Localidad;

@Repository
public interface DomicilioRepository extends JpaRepository<Domicilio, Long>{

    boolean existsByCalleAndNumeroAndLocalidad(String calle, String numero, Localidad localidad);

}
