package com.miprimerapp.Proyecto1.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonaSaveDto {
    private String nombre;
    private String apellido;
    private int edad;
    private String nacionalidad;
}
