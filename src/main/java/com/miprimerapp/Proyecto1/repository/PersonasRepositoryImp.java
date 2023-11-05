package com.miprimerapp.Proyecto1.repository;

import com.miprimerapp.Proyecto1.entity.Persona;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonasRepositoryImp implements IPersonasRepository {

    private List<Persona> dataBase = new ArrayList<>();

    @Override
    public Persona save(Persona persona) {
        dataBase.add(persona);
        return persona;
    }

    @Override
    public List<Persona> findAll() {
        return dataBase;
    }
}
