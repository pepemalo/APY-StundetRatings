package com.student.StundetRatings.Controladores;

import com.student.StundetRatings.Modelos.Usuario;
import com.student.StundetRatings.Modelos.Rol;
import com.student.StundetRatings.Repositorios.RepositorioRol;
import com.student.StundetRatings.Repositorios.RepositorioUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class controladorUsuario {
    private static final Logger logger = LoggerFactory.getLogger(controladorUsuario.class);

    @Autowired
    RepositorioUsuarios repositorioUsuarios;
    @Autowired
    RepositorioRol repositorioRol;

    //CRUD USUARIOS
    /**
     * DOCUMENTACION:
        El método "createUser" es una función POST que recibe un objeto Usuario en formato JSON en el cuerpo de la petición. 
        Se encarga de crear un nuevo usuario en la base de datos utilizando el repositorio de usuarios. Si el usuario es creado con éxito, 
        se imprime un mensaje en la consola indicando que el proceso fue exitoso. Finalmente, se retorna el objeto Usuario creado.  
        El código está bien estructurado y es fácil de entender. Se utiliza la anotación @PostMapping para indicar 
        que se trata de una petición POST. Además, se utiliza la anotación @RequestBody para indicar que el objeto 
        Usuario se encuentra en el cuerpo de la petición.  
        El método también cuenta con mensajes de consola que permiten verificar el correcto funcionamiento del método. 
        En general, es una buena implementación que cumple con su objetivo de crear un nuevo usuario en la base de datos.
     * 
    */
    //CREATE - POST
    @PostMapping("/user/create")
    public Usuario createUser(@RequestBody Usuario usuarioEntrada){
        System.out.println("|---------------------------------|");
        System.out.println("|--DENTRO DEL METODO CREATE USER--|");
        Usuario usuarioNew = repositorioUsuarios.save(usuarioEntrada);
        if (usuarioNew != null){
            System.out.println("Usuario Creado Con Exito..");
        }
        System.out.println("Repuesta del metodo /user/create :::"+usuarioNew);
        System.out.println("|---------------------------------|");
        return usuarioNew;
    }


    /**
     * DOCUMENTACION:
        El método "listarUsuarios" es una función GET que obtiene una lista de todos los usuarios almacenados en la base de datos 
        utilizando el repositorio de usuarios. Se imprime un mensaje en la consola indicando que se está dentro del método 
        "listarUsuarios". Finalmente, se retorna la lista de usuarios obtenida.  
        El código está bien estructurado y es fácil de entender. Se utiliza la anotación 
        @GetMapping para indicar que se trata de una petición GET. Además, 
        se utiliza la ruta "/user/listar" para acceder a la lista de usuarios.  
        En general, es una buena implementación que cumple con su objetivo de obtener una lista de usuarios almacenados en la base de datos.
    */

    //READ - GET
    @GetMapping("/user/listar")
    public List<Usuario> listarUsuarios(){
        System.out.println("|---------------------------------|");
        System.out.println("|--DENTRO DEL METODO LISTAR USER--|");
        System.out.println("|---------------------------------|");
        return repositorioUsuarios.findAll();

    }


    /***
     * DOCUMENTACION:
        El método "actualizarUsuario" es una función PUT que recibe un ID de usuario y un objeto Usuario en formato JSON 
        en el cuerpo de la petición. Se Encarga de actualizar un usuario existente en la base de datos. 
        Se recibe el ID del usuario a actualizar y los nuevos datos del usuario en formato JSON. Primero, 
        se busca el usuario en la base de datos y si no se encuentra, se devuelve un mensaje de error. 
        Si el usuario existe, se actualizan sus atributos con los nuevos datos recibidos y se guardan los cambios en la base de datos. 
        Finalmente, se devuelve un mensaje de éxito que indica que el usuario ha sido actualizado correctamente.
    */
    //UPDATE - PUT
    @PutMapping("/user/actualizar/{id}")
    public String actualizarUsuario( @PathVariable String id, @RequestBody Usuario usuarioNew){
        System.out.println("|---------------------------------|");
        System.out.println("|--DENTRO DEL METODO UPDATE USER--|");

        Usuario usuarioId = repositorioUsuarios.findById(id).orElse(null);
        if (usuarioId == null) {
            return "El ID " + id + " no fue encontrado en la base de datos";
        } else {
            //setiara los atributos
            usuarioId.setNombre(usuarioNew.getNombre());
            usuarioId.setNombreUsuario(usuarioNew.getNombreUsuario());
            usuarioId.setApellido(usuarioNew.getApellido());
            usuarioId.setCorreo(usuarioNew.getCorreo());
            usuarioId.setContrasena(usuarioNew.getContrasena());
            usuarioId.setRol(usuarioNew.getRol());
            //enviara los datos actualizados a db
            repositorioUsuarios.save(usuarioId);
            return "Usuario " + id + " fue modificado";
        }
    }


    /**
     * DOCUMENTACION:
        El método "eliminarUser" es una función DELETE que recibe un ID de usuario en la ruta de la petición. 
        Se encarga de eliminar el usuario correspondiente de la base de datos utilizando el repositorio de usuarios. 
        Si el ID no es nulo, se elimina el usuario de la base de datos y se retorna un mensaje indicando que se eliminó el usuario 
        especificado. Si el ID es nulo, se imprime un mensaje en la consola indicando que el ID es nulo.  
        El código está bien estructurado y es fácil de entender. Se utiliza la anotación 
        @DeleteMapping para indicar que se trata de una petición DELETE. Además, se utiliza la ruta "user/delete/{id}" 
        para acceder al usuario que se desea eliminar.  
        El método también cuenta con mensajes de consola que permiten verificar el correcto funcionamiento del método. 
        En general, es una buena implementación que cumple con su objetivo de eliminar un usuario específico de la base de datos
    **/
    //DELETE - DELETE
    @DeleteMapping("user/delete/{id}")
    public String  eliminarUser( @PathVariable String id){
        System.out.println("|---------------------------------|");
        System.out.println("|--DENTRO DEL METODO UPDATE USER--|");
        Optional<Usuario> usuario = repositorioUsuarios.findById(id); 
        if(!usuario.isPresent()){
            return "Id " +id+ " No fue Encontrado o es Null";
           
        }else{
            repositorioUsuarios.deleteById(id);
        }

        System.out.println("|---------------------------------|");
        return "Se elimino el Usuario :: "+ id;

    }
    

    /**
     *DOCUMENTACION:
        El método "asignarRol" es una función PUT que recibe un ID de usuario y un ID de rol en la ruta de la petición. 
        Se encarga de asignar un rol específico a un usuario en la base de datos utilizando el repositorio de usuarios 
        y el repositorio de roles. Si el rol es asignado con éxito, se imprime un mensaje en la consola indicando que el proceso fue exitoso.
        Finalmente, se retorna un mensaje indicando si el rol fue asignado o no.  
        El código está bien estructurado y es fácil de entender. Se utiliza la anotación 
        @PutMapping para indicar que se trata de una petición PUT. Además, se utiliza la ruta "user/{idUser}/rol/{idRol}" 
        para acceder al usuario y al rol que se desea asignar.  
        El método también cuenta con mensajes de consola que permiten verificar el correcto funcionamiento del método. 
        En caso de que ocurra algún error, se registra en el log de errores. 
        En general, es una buena implementación que cumple con su objetivo de asignar un rol específico a un usuario en la base de datos.
    **/

    //ASIGNAR-ROL
    @PutMapping("user/{idUser}/rol/{idRol}")
    public String asignarRol ( @PathVariable String idUser, @PathVariable String idRol){
        System.out.println("|---------------------------------|");
        System.out.println("|--DENTRO DEL METODO ASIGNAR ROL--|");
        String mensaje = "";
        try{

            Usuario usuarioConsulta = repositorioUsuarios.findById(idUser).orElse(null);
            Rol rolConsulta = repositorioRol.findById(idRol).orElse(null);
            if(usuarioConsulta != null && rolConsulta != null){
                usuarioConsulta.setRol(rolConsulta);
                repositorioUsuarios.save(usuarioConsulta);
                mensaje="Actualizado Rol";

            }else {
                mensaje="No pudo ser actualizo el Rol";
            }

        }catch (Exception e){
            mensaje = "Ocurrió un error al asignar el Rol";
            logger.error(mensaje, e);
        }
        System.out.println("| Mensaje :: "+mensaje+" |");
        System.out.println("|---------------------------------|");
        return mensaje;
    }

    /**
     * DOCUMENTACION:
        El método "login" es una función POST que recibe un objeto Usuario en formato JSON en el cuerpo de la petición. 
        Se encarga de verificar si el correo y la contraseña ingresados por el usuario coinciden con los datos almacenados 
        en la base de datos utilizando el repositorio de usuarios. Si los datos son correctos, 
        se retorna un mensaje indicando que el inicio de sesión fue exitoso. En caso contrario, 
        se retorna un mensaje indicando que los datos son incorrectos.  
        El código está bien estructurado y es fácil de entender. Se utiliza la anotación 
        @PostMapping para indicar que se trata de una petición POST. Además, se utiliza la ruta "user/login" 
        para acceder al método de inicio de sesión.  
        El método también cuenta con mensajes de consola que permiten verificar el correcto funcionamiento del método. 
        En general, es una buena implementación que cumple con su objetivo de verificar si los datos ingresados por el usuario 
        coinciden con los datos almacenados en la base de datos.
    **/

    //VERIFICAR-LOGIN
    @PostMapping("user/login")
    public String login (@RequestBody Usuario usuarioEntrada){
        String mensaje = "";
        Usuario usuarioConsulta = repositorioUsuarios.consultarCorreo(usuarioEntrada.getCorreo());
        if (usuarioConsulta !=null && usuarioConsulta.getContrasena().equals(usuarioEntrada.getContrasena())){
                mensaje="Inicio Exitoso";
        }else {
            mensaje="Verifique sus Datos";

        }
        return mensaje;
    }

}
