package com.student.StundetRatings.Repositorios;

import com.student.StundetRatings.Modelos.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface RepositorioUsuarios extends MongoRepository<Usuario,String>{

    @Query("{'correo':'?0'}")
    public  Usuario consultarCorreo(String correo);
}
