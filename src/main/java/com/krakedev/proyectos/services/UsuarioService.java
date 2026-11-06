package com.krakedev.proyectos.services;

import org.springframework.stereotype.Service;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import com.krakedev.proyectos.entidade.Usuario;
import com.krakedev.proyectos.repositorie.UsuarioRepository;

@Service
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public Usuario guardar(Usuario usuario) {

		String constrasenaEncriptada = BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt());

		usuario.setPassword(constrasenaEncriptada);

		return usuarioRepository.save(usuario);
	}

	public boolean autenticar(String username, String password) {
		Optional<Usuario> usuarioOpt = usuarioRepository.findByUsername(username);
		if (usuarioOpt.isPresent()) {
			Usuario usuario = usuarioOpt.get();

			if (BCrypt.checkpw(password, usuario.getPassword())) {
				return true;
			}
		}
		return false;

	}

}