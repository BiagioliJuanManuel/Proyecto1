package com.miprimerapp.Proyecto1.service;

import com.miprimerapp.Proyecto1.dto.request.EdadDto;
import com.miprimerapp.Proyecto1.dto.request.PersonaSaveDto;
import com.miprimerapp.Proyecto1.dto.response.PersonaResponseDto;
import com.miprimerapp.Proyecto1.dto.response.PersonasPorEdadDto;
import com.miprimerapp.Proyecto1.dto.response.ResponseDto;

import java.util.List;

public interface IPersonasService {
    ResponseDto guardarPersona(PersonaSaveDto persona);

    List<PersonaResponseDto> buscarTodos();

    List<PersonasPorEdadDto> buscarPorEdad(EdadDto edad);
}
