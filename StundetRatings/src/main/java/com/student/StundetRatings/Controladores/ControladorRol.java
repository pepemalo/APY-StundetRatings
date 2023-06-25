package com.student.StundetRatings.Controladores;

import com.student.StundetRatings.Modelos.Rol;
import com.student.StundetRatings.Repositorios.RepositorioRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ControladorRol {
    @Autowired
    RepositorioRol repositorioRol;

    /*
     * DOCUMENTO:
        El método "crearRol" es una función POST que recibe un objeto Rol en formato JSON en el cuerpo de la petición. 
        Se encarga de crear un nuevo rol en la base de datos utilizando el repositorio de roles. 
        Si el rol es creado con éxito, se retorna el objeto Rol creado con su respectivo ID asignado por la base de datos.  
        El código está bien estructurado y es fácil de entender. Se utiliza la anotación 
        @PostMapping para indicar que se trata de una petición POST. Además, se utiliza la ruta "rol/crear" 
        para acceder al método de creación de roles.  
        En general, es una buena implementación que cumple con su objetivo de crear un nuevo rol en la base de datos.
     * 
    */
    //CREATE
    @PostMapping("rol/crear")
    public Rol crearRol(@RequestBody Rol rol){
        return repositorioRol.save(rol);
    }

    /**
     * DOCUMENTACION:
     *  El método "listarRoles" es una función GET que devuelve una lista de todos los roles almacenados en la base de datos 
        utilizando el repositorio de roles.  
        El código está bien estructurado y es fácil de entender. Se utiliza la anotación 
        @GetMapping para indicar que se trata de una petición GET. Además, se utiliza la ruta "rol/listar" 
        para acceder al método de listado de roles.  
        En general, es una buena implementación que cumple con su objetivo de listar todos los roles almacenados en la base de datos.
    */
    //READ
    @GetMapping("rol/listar")
    public List<Rol> listarRoles(){
        return  repositorioRol.findAll();
    }

    /*
     * DOCUMENTACION:
        El método "updateRol" es una función PUT que recibe un ID de rol en la ruta de la petición y un objeto Rol en formato JSON 
        en el cuerpo de la petición. Se encarga de actualizar el rol correspondiente en la base de datos utilizando el repositorio de roles. Si el ID existe en la base de datos, se actualiza el nombre y la descripción del rol con los datos proporcionados en el objeto Rol y se retorna un mensaje indicando que el rol fue actualizado. Si el ID no existe en la base de datos, se retorna un mensaje indicando que el ID no fue encontrado.  
        El código está bien estructurado y es fácil de entender. Se utiliza la anotación 
        @PutMapping para indicar que se trata de una petición PUT. Además, se utiliza la ruta "rol/update/{id}" 
        para acceder al rol que se desea actualizar.  
        En general, es una buena implementación que cumple con su objetivo de actualizar un rol específico en la base de datos.
    */
    //UPDATE
    @PutMapping("rol/update/{id}")
    public  String updateRol(@PathVariable String id, @RequestBody Rol rol ){
        String mensaje = "";
        Rol rolConsulta = repositorioRol.findById(id).orElse(null);
        if(rolConsulta != null){
            rolConsulta.setNombre(rol.getNombre());
            rolConsulta.setDescripcion(rol.getDescripcion());
            repositorioRol.save(rol);
            mensaje="Rol con Id"+id+" Fue Actualizado";

        }else {
            mensaje = "Id no Econtrado...";
        }

        return mensaje;
    }

    /*
     *DOCUMENTACION:
        El método "deleteRol" es una función DELETE que recibe un ID de rol en la ruta de la petición. 
        Se encarga de eliminar el rol correspondiente de la base de datos utilizando el repositorio de roles. 
        Si el ID existe en la base de datos, se elimina el rol y se retorna un mensaje indicando que el rol fue eliminado con éxito. 
        Si el ID no existe en la base de datos, se retorna un mensaje indicando que el ID no fue encontrado.  
        El código está bien estructurado y es fácil de entender. Se utiliza la anotación 
        @DeleteMapping para indicar que se trata de una petición DELETE. Además, se utiliza la ruta "rol/delete/{id}" 
        para acceder al rol que se desea eliminar.  
        En general, es una buena implementación que cumple con su objetivo de eliminar un rol específico de la base de datos.
    */
    //DELETE
    @DeleteMapping("rol/delete/{id}")
    public String deleteRol(@PathVariable String id){
        String mensaje = "";

        Optional<Rol> rolConsulta = repositorioRol.findById(id);
        if ((rolConsulta).isPresent()) {
            repositorioRol.deleteById(id);
            mensaje = "Eliminado con Éxito id ::: " + id;
        } else {
            mensaje = "Id no Encontrado";
        }

        return mensaje;
    }



}
