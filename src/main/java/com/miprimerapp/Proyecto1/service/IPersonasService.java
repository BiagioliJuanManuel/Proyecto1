package com.miprimerapp.Proyecto1.service;

import com.miprimerapp.Proyecto1.dto.PersonaDto;
import com.miprimerapp.Proyecto1.dto.response.ResponseDto;

import java.util.List;

public interface IPersonasService {
    ResponseDto guardarPersona(PersonaDto persona);

    List<PersonaDto> buscarTodos();
}
