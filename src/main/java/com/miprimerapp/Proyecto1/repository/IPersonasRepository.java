package com.miprimerapp.Proyecto1.repository;

import com.miprimerapp.Proyecto1.entity.Persona;

import java.util.List;

public interface IPersonasRepository {

    Persona save(Persona persona);

    List<Persona> findAll();

    List<Persona> findByAge(int edad);

    Persona findById(int id);

}
