package com.miprimerapp.Proyecto1.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonasPorEdadDto {
    private String nombre;
    private String apellido;
    private int edad;
}
