package com.uncode.rest_api_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uncode.rest_api_security.model.Libro;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long>{

}
