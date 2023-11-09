package com.miprimerapp.Proyecto1.exception;

/*
*       ------ Clase 09/10 ------
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/*
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "La Persona no fue encontrada")

*   Esta anotación vincula una excepción (clase o derivado) con un estado de respuesta
*   de Http especifico, lo que nos permite definir y configurar un mensaje (y codigo http)
*   a nivel clase o metodo especifico para el lanzamiento y captura de esa excepción.
*
*   (no la vamos a usar mucho porque responderiamos con un string en el body, con lo cual
*   no estariamos respetando el patron DTO, ~DISCUTIBLE~)
 */
public class PersonaNotFoundException extends RuntimeException{
    public PersonaNotFoundException(String message) {
        super(message);
    }
}
