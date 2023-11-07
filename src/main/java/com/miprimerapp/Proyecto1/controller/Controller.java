package com.miprimerapp.Proyecto1.controller;

import com.miprimerapp.Proyecto1.dto.request.EdadDto;
import com.miprimerapp.Proyecto1.dto.request.PersonaSaveDto;
import com.miprimerapp.Proyecto1.dto.response.ResponseDto;
import com.miprimerapp.Proyecto1.service.IPersonasService;
import com.miprimerapp.Proyecto1.service.PersonasServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    private IPersonasService service;

    //Spring es quien se encarga de brindar la instancia de PersonasServiceImp
    //es decir la implementacion del servicio.
    public Controller(PersonasServiceImp service){
        this.service = service;
    }

    /*
    @GetMapping("/hola")
    public String saludar(){
        return "Hola Mundo!!";
    }

    @GetMapping("/{name}/{apellido}")
    public String saludarPorNombre(@PathVariable String name, @PathVariable String apellido){
        return "Hola "+name+" "+apellido;
    }

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
    *
    */

    // Clase 20/09

    /*
    @GetMapping("/saludo")
    public String saludarRequestParam(@RequestParam String name,@RequestParam String lastname){
        return "Hola "+ name + " " + lastname;
    }

    //PathVariable se utiliza para recuperar valores propios, que son parte de la Url o URI, y que a su vez forman parte de la peticion.
    //ejemplo: localhost:8080/empleado/jose/perez
    //no tiene sentido para: localhost:8080/empleado/id/5

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

    @PostMapping("/guardar")
    public PersonaDto agregarPersona(@RequestBody PersonaDto persona){
        System.out.println(persona);
        return persona;
    }
     */



    // ----------- CLASE 25/09 ------------ //

    /*
    *   A partir de ahora, en ves de responder con un objeto java como String
    *   utilizamos un objeto generico que nos provee de funcionalidades
    *   para el protocolo http, como por ejemplo es tratar el HEADER,BODY,HTTP STATUS,etc...
    *   Ese objeto es ResponseEntity<> el cual tambien podemos definir su tipo con una clase
    *   pero para uso practico, asignamos un comodin '?', para poder utilizar cualquier
    *   tipo de clase java o definida por nosotros.
    *
    *   A su vez para configurar el BODY del ResponseEntity<> por el constructor, pasamos
    *   por parametro el DTO, en este caso ResponseGreetingDto que de atributo String
    *   tiene un message, el cual la clase ResponseEntity<> transforma el DTO a JSON.
    *
    *   El ResponseEntity nos permite configurar la respuesta, y el DTO para generar
    *   el mensaje u objeto que quiero devolver en el BODY, un objeto JAVA
    *   que el Framework transforma a objeto JSON.


        @GetMapping("/saludo")
        public ResponseEntity<?> saludarRequestParam(@RequestParam String name, @RequestParam String lastname){
            ResponseGreetingsDto respuesta = new ResponseGreetingsDto("Hola "+ name + " " + lastname);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }

        @PostMapping("/guardar")
        public ResponseEntity<?> agregarPersona(@RequestBody PersonaDto persona){
            System.out.println(persona); // a modo de practica/prueba imprimimos por consola
                                         // en produccion es una muy mala practica
            return new ResponseEntity<>(persona, HttpStatus.OK);
        }


    *   Armamos el paquete service y repository, donde utilizamos el patron compossite
    *   para generar instancias unicas mediante interfaces o mejor dicho mediante
    *   implementaciones de interfaces, con el objetivo de mejorar la escalabilidad
    *   del sistema a futuro.
    *
    *   A su vez esto nos permite aplicar, la Inversion de Control e Inyeccion de Dependencia
    *   donde le damos la potestad al Framework de la creación o instanciación de objetos
    *   en las diferentes capas, lo que nos permite a su vez, aplicar otro patron como
    *   la inyeccion de dependencia, logrando que esas instancias se intercomuniquen
    *   entre capa y capa, esta es una forma, de las muchas que hay, de implementar la inversion.
    *
    *   El objetivo es tener un codigo lo suficientemente desacoplado que nos permita
    *   un facil mantenimiento y una gran escalabilidad.
    *
    *   La inyeccion de dependencia, siempre es entre capas, es decir:
    *   ~ Controller<--->Service : el controlador siempre va a tener una inyeccion de dependencia del servicio.
    *   ~ Service<--->Repository : el servicio tiene una inyeccion de dependencia del repositorio.
    *   !--- NUNCA VA A HABER UNA INYECCION DEL CONTROLLADOR AL REPOSITORIO ---!
    *   !--- O MEJOR DICHO NUNCA EL CONTROLADOR SE VA A CONECTAR CON EL REPOSITORIO DE MANERA DIRECTA ---!
    *
    *   Investigar @Autowired (produce distintos tipos de falla en Testing, no es recomendado)
    *
     */

   @PostMapping("/guardar")
    public ResponseEntity<?> agregarPersona(@RequestBody PersonaSaveDto persona){
       ResponseDto respuesta = service.guardarPersona(persona);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/obtenerTodos")
    public ResponseEntity<?> obtenerTodos(){
       return new ResponseEntity<>(service.buscarTodos(), HttpStatus.OK);
    }

    /*  // ----------- CLASE 25/09 ------------ //
    *
    *   clase de repaso, nuevo feature buscar por edad.
     */

    @GetMapping("/buscar/{edad}")
    public ResponseEntity<?> obtenerPorEdad(@PathVariable int edad){

        return new ResponseEntity<>(service.buscarPorEdad(new EdadDto(edad)),HttpStatus.OK);
    }
}
