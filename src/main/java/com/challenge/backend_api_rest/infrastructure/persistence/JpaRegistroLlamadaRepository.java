package com.challenge.backend_api_rest.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challenge.backend_api_rest.domain.model.RegistroLlamada;
import com.challenge.backend_api_rest.domain.repository.RegistroLlamadaRepository;

@Repository
public interface JpaRegistroLlamadaRepository extends JpaRepository<RegistroLlamada, Long>, RegistroLlamadaRepository{
    
}
