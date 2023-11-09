package com.miprimerapp.Proyecto1.exception;

import com.miprimerapp.Proyecto1.dto.response.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    // -------- Siempre vamos a lanzar las excepciones desde el service -------- //
    @ExceptionHandler(PersonaNotFoundException.class)
    public ResponseEntity<?> personaNotFoundException(PersonaNotFoundException ex){
        //Siempre respondemos con DTOs, en este caso hacemos una clase ErrorDto para responder
        //con un status code y un mensaje para cada excepción.
        ErrorDto err = new ErrorDto(404, ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ClonException.class)
    public ResponseEntity<?> ClonException(ClonException ex){
        ErrorDto err = new ErrorDto(404, ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InsertionDBException.class)
    public ResponseEntity<?> InsertionException(InsertionDBException ex){
        ErrorDto err = new ErrorDto(404, ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }
}
