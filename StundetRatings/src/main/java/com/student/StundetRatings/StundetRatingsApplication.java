package com.student.StundetRatings;

import com.student.StundetRatings.Modelos.Rol;
import com.student.StundetRatings.Modelos.Usuario;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class StundetRatingsApplication {

	public static void main(String[] args) {
		SpringApplication.run(StundetRatingsApplication.class, args);

		//Instaciaciones de las Diferentes Clases
		Usuario estudent = new Usuario("Anderson","Cardozo","acardozoar","prueba@gmail.com","123");
		Rol rolstudent = new Rol("Anderson","Rol de Estudiante");
		Rol profesor = new Rol("Karol","Ensena politica");

		//Fin
		System.out.println("|-------------------------------------|");
		System.out.println("|---------	Estudiantes		 ---------|");
		System.out.println("Nombre :: "+estudent.getNombre());
		System.out.println("Apellido :: "+estudent.getApellido());
		System.out.println("NombreUsuario :: "+estudent.getNombreUsuario());
		System.out.println("|-------------------------------------|");

		System.out.println("|-------------------------------------|");
		System.out.println("|---------			ROLES	 ---------|");
		System.out.println("Nombre :: "+profesor.getNombre());
		System.out.println("Descripcion :: "+profesor.getDescripcion());
		System.out.println("|-------------------------------------|");

	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

}
