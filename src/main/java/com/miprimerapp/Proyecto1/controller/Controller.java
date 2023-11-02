package com.miprimerapp.Proyecto1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/hola")
    public String saludar(){
        return "Hola Mundo!!";
    }

    @GetMapping("/{name}/{apellido}")
    public String saludarPorNombre(@PathVariable String name, @PathVariable String apellido){
        return "Hola "+name+" "+apellido;
    }

    /*
    * @SpringBootApplication: Nos permite especificar que trabajamos sobre una aplicacion de spring
    * Habilita 3 caracteristicas, la autoconfiguración del proyecto, la busqueda de componentes y la
    * posibilidad de configuar cosas extra.
    *
    *
    * @RestController: Anotación a nivel clase que nos permite identificar el controlador de un servicio tipo REST,
    * e identificarlo como un componente de spring.
    *
    * @GetMapping: Anotación a nivel método que nos permite mapear las peticiones mediante el método GET
    * dentro de nuestra aplicación.
    *
    * @GetMapping + @PathVariable: Nos permiten indicar el parámetro que vamos a recibir en un método
    * del tipo GET como petición dentro de nuestra aplicación.
    * */
}
