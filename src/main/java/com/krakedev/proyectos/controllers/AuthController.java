package com.krakedev.proyectos.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krakedev.proyectos.entidade.Usuario;
import com.krakedev.proyectos.services.UsuarioService;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestHeader;

import com.krakedev.proyectos.repositorie.UsuarioRepository;
import com.krakedev.proyectos.security.JwtUtil;
import com.krakedev.proyectos.services.TokenBlacklistService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final UsuarioService usuarioService;
	private final UsuarioRepository usuarioRepository;
	private final TokenBlacklistService blacklistService;

	public AuthController(UsuarioService usuarioService, UsuarioRepository usuarioRepository,
			TokenBlacklistService blacklistService) {
		super();
		this.usuarioService = usuarioService;
		this.usuarioRepository = usuarioRepository;
		this.blacklistService = blacklistService;
	}

	@PostMapping("/registrar")
	public ResponseEntity<?> registrar(@RequestBody Usuario usuario) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.guardar(usuario));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al registrar usuario: " + e.getMessage());
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Map<String, String> credenciales) {
		String username = credenciales.get("username");
		String password = credenciales.get("password");

		if (usuarioService.autenticar(username, password)) {
			Usuario usuario = usuarioRepository.findByUsername(username).get();
			String token = JwtUtil.creacionToken(usuario.getUsername(), usuario.getRol());
			return ResponseEntity.ok(Map.of("token", token));
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario o contrasena incorrecta");
	}

	@PostMapping("/logout")
	public ResponseEntity<?> logout(@RequestHeader(value = "Authorization", required = false) String authHeader) {
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			String token = authHeader.substring(7);
			blacklistService.invalidarToken(token);
			return ResponseEntity.ok(Map.of("mensaje", "Sesion cerrada. Token invalidado"));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token no proporcionado");
	}

}
