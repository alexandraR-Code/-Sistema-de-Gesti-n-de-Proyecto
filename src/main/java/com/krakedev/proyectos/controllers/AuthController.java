package com.krakedev.proyectos.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krakedev.proyectos.entidade.Usuario;
import com.krakedev.proyectos.services.UsuarioService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final UsuarioService usuarioService;

	public AuthController(UsuarioService usuarioService) {
		super();
		this.usuarioService = usuarioService;
	}

	@PostMapping("/registrar")
	public ResponseEntity<?> registrar(@RequestBody Usuario usuario) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.guardar(usuario));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al reegistrar usuario: " + e.getMessage());
		}
	}

}
