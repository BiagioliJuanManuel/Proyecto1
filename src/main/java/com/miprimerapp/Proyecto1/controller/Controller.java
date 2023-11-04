package com.miprimerapp.Proyecto1.controller;

import com.miprimerapp.Proyecto1.dto.PersonaDto;
import com.miprimerapp.Proyecto1.entity.Persona;
import org.springframework.web.bind.annotation.*;

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

    // Clase 20/09

    @GetMapping("/saludo")
    public String saludarRequestParam(@RequestParam String name,@RequestParam String lastname){
        return "Hola "+ name + " " + lastname;
    }

    //PathVariable se utiliza para recuperar valores propios, que son parte de la Url o URI, y que a su vez forman parte de la peticion.
    //ejemplo: localhost:8080/empleado/jose/perez
    //no tiene sentido para: localhost:8080/empleado/id/5
    /*

    Como usamos la arquitectura multicapa,
    nunca vamos a tratar de forma directa desde el controlador
    a un objeto de la capa entidades (entity)

    @PostMapping("/guardar")
    public void agregarPersona(@RequestBody Persona persona){
        System.out.println(persona);
    }

    como alternativa siempre vamos a usar los DTO (Data Transfer Object)
    a los cuales siempre debemos respetar su inmutabilidad al no usar
    ni getters ni setters, como plantea el patron de diseño.

    investigar Records <---- TODO

    */
    @PostMapping("/guardar")
    public PersonaDto agregarPersona(@RequestBody PersonaDto persona){
        System.out.println(persona);
        return persona;
    }

}
