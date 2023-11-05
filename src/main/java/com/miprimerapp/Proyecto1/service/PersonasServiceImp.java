package com.miprimerapp.Proyecto1.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miprimerapp.Proyecto1.dto.PersonaDto;
import com.miprimerapp.Proyecto1.dto.response.ResponseDto;
import com.miprimerapp.Proyecto1.entity.Persona;
import com.miprimerapp.Proyecto1.repository.IPersonasRepository;
import com.miprimerapp.Proyecto1.repository.PersonasRepositoryImp;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonasServiceImp implements IPersonasService{

    //Inyeccion del repositorio al Servicio (Clase 25/09)
    private IPersonasRepository repository;

    public PersonasServiceImp(PersonasRepositoryImp repository){
        this.repository = repository;
    }

    @Override
    public ResponseDto guardarPersona(PersonaDto persona) {
        ObjectMapper mapper = new ObjectMapper();
        Persona personaEntity = mapper.convertValue(persona, Persona.class);
        Persona respuestaRepo = repository.save(personaEntity);
        if (respuestaRepo == null){
            return new ResponseDto("No se logro guardar");
        }
        return new ResponseDto("La persona "+respuestaRepo.getNombre()+" se guardo correctamente");
    }

    /* Metodo largo

    @Override
    public List<PersonaDto> buscarTodos() {
        ObjectMapper mapper = new ObjectMapper();
        List<Persona> listaEntidades = repository.findAll();
        List<PersonaDto> respuesta = listaEntidades.stream()
                .map(persona ->mapper.convertValue(persona, PersonaDto.class))
                .toList();
        return respuesta;
    }

     */
       @Override
    public List<PersonaDto> buscarTodos() {
           ObjectMapper mapper = new ObjectMapper();
           return repository.findAll().stream()
                   .map(persona -> mapper.convertValue(persona, PersonaDto.class))
                   .toList();
    }

}
