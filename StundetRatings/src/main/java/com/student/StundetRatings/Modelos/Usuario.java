package com.student.StundetRatings.Modelos;

//Importaciones


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

//Decoradores
@Data
@Document
public class Usuario {

    @Id
    private String _id;
    private String nombre;
    private String apellido;
    private String nombreUsuario;
    private String correo;
    private String contrasena;
    @DBRef
    private Rol rol ;

    // Constructor vacío ()
    public Usuario() {
    }

    // Constructor con todos los atributos
    public Usuario(String nombre, String apellido, String nombreUsuario, String correo, String contrasena) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
// Getters y Setters

    public String get_id() {
        return _id;
    }

    public void setId(Long id) {
        this._id = _id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
