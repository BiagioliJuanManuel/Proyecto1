package com.miprimerapp.Proyecto1.repository;

import com.miprimerapp.Proyecto1.entity.Persona;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonasRepositoryImp implements IPersonasRepository {

    //final: que sea constante en JAVA,
    // solo significa que no puedo cambiar la referencia en memoria del objeto,
    // si puedo modificar los objetos que contenga dentro.
    private final List<Persona> dataBase = new ArrayList<>();

    @Override
    public Persona save(Persona persona) {
        persona.setId(dataBase.size()+1);
        dataBase.add(persona);
        return persona;
    }

    @Override
    public List<Persona> findAll() {
        return dataBase;
    }

    @Override
    public List<Persona> findByAge(int edad) {
        return dataBase.stream()
                .filter(persona -> persona.getEdad()>=edad).toList();
    }

    @Override
    public Persona findById(int id) {
        return dataBase.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }
}
