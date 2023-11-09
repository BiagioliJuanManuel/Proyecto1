package com.miprimerapp.Proyecto1.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miprimerapp.Proyecto1.dto.request.EdadDto;
import com.miprimerapp.Proyecto1.dto.request.PersonaSaveDto;
import com.miprimerapp.Proyecto1.dto.response.PersonaResponseDto;
import com.miprimerapp.Proyecto1.dto.response.PersonasPorEdadDto;
import com.miprimerapp.Proyecto1.dto.response.ResponseDto;
import com.miprimerapp.Proyecto1.entity.Persona;
import com.miprimerapp.Proyecto1.exception.ClonException;
import com.miprimerapp.Proyecto1.exception.InsertionDBException;
import com.miprimerapp.Proyecto1.exception.PersonaNotFoundException;
import com.miprimerapp.Proyecto1.repository.IPersonasRepository;
import com.miprimerapp.Proyecto1.repository.PersonasRepositoryImp;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonasServiceImp implements IPersonasService{

    //Inyeccion del repositorio al Servicio (Clase 25/09)
    private IPersonasRepository repository;

    private final ObjectMapper mapper;
    public PersonasServiceImp(PersonasRepositoryImp repository){
        this.repository = repository;
        this.mapper = new ObjectMapper();
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

    /* Clase 04/10
    * falla el convert value al convertir los atributos, recibimos 5 atributos
    * y queremos mostrar solo dos.

       @Override
    public List<PersonaResponseDto> buscarTodos() {
           return repository.findAll().stream()
                   .map(persona -> mapper.convertValue(persona, PersonaResponseDto.class))
                   .toList();
    }
     */

    /*
    *       -------- Clase 09/10 --------
    *
            @Override
        public ResponseDto guardarPersona(PersonaSaveDto persona) {
            Persona personaEntity = mapper.convertValue(persona, Persona.class);
            Persona respuestaRepo = repository.save(personaEntity);
            if (respuestaRepo == null){
                return new ResponseDto("No se logro guardar");
            }
            return new ResponseDto("La persona "+respuestaRepo.getNombre()+" se guardo correctamente");
        }
    *
    *
    *
           @Override
        public List<PersonaResponseDto> buscarTodos() {
               return repository.findAll().stream()
                       .map(persona -> new PersonaResponseDto(persona.getNombre(), persona.getApellido()))
                       .toList();
        }
    *
    *
            @Override
        public List<PersonasPorEdadDto> buscarPorEdad(EdadDto edad) {
               return repository.findByAge(edad.getEdad()).stream()
                       .map(persona -> new PersonasPorEdadDto(persona.getNombre(), persona.getApellido(), persona.getEdad()))
                       .toList();
        }
    *
    *       modificamos los métodos para lanzar excepciónes desde el service (siempre)
    */

    @Override
    public ResponseDto guardarPersona(PersonaSaveDto persona) {
        Persona personaEntity = mapper.convertValue(persona, Persona.class);
        if (verificarExistencia(personaEntity)){
            throw new ClonException("La persona ya existe.");
        }
        Persona respuestaRepo = repository.save(personaEntity);
        if (respuestaRepo == null){
            throw new InsertionDBException("No se guardo la persona correctamente.");
        }
        return new ResponseDto("La persona "+respuestaRepo.getNombre()+" se guardo correctamente");
    }

    @Override
    public List<PersonaResponseDto> buscarTodos() {
        // Para no hacer dos llamados al repositorio, lo instanciamos.
        List<Persona> listaRepo = repository.findAll();

        // lanzamos la excepción en caso de tener una lista de personas vacía.
        if (listaRepo.isEmpty()){
            // Seteamos el mensaje en la instanciación de la excepción.
            throw new PersonaNotFoundException("La lista de personas está vacía.");
        }

        return listaRepo.stream()
                .map(persona -> new PersonaResponseDto(persona.getNombre(), persona.getApellido()))
                .toList();
    }


    @Override
    public List<PersonasPorEdadDto> buscarPorEdad(EdadDto edad) {
    List<Persona> listaRepo = repository.findByAge(edad.getEdad());
        if (listaRepo.isEmpty()){
            throw new PersonaNotFoundException("No se encontró ninguna persona en ese rango de edad.");
        }

       return listaRepo.stream()
               .map(persona -> new PersonasPorEdadDto(persona.getNombre(), persona.getApellido(), persona.getEdad()))
               .toList();
    }

    private boolean verificarExistencia(Persona p){
        List<Persona> listaEntidad = repository.findAll();
        if (listaEntidad.isEmpty()){
            return false;
        }
        List<Persona> listaBusqueda = listaEntidad.stream()
                .filter(persona -> persona.equals(p))
                .toList();
        if (listaBusqueda.isEmpty()){
            return false;
        }
        return true;
    }

}
