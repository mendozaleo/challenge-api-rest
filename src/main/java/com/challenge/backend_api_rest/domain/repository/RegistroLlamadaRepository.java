package com.challenge.backend_api_rest.domain.repository;

import java.util.List;

import com.challenge.backend_api_rest.domain.model.RegistroLlamada;

public interface RegistroLlamadaRepository {

    RegistroLlamada save(RegistroLlamada registro);

    List<RegistroLlamada> findAll();

}
