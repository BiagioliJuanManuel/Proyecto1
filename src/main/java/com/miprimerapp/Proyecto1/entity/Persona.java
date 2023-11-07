package com.miprimerapp.Proyecto1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Persona {
    private long id;
    private String nombre;
    private String apellido;
    private int edad;
    private String nacionalidad;

}
