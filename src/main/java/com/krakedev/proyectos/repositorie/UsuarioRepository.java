package com.krakedev.proyectos.repositorie;

import com.krakedev.proyectos.entidade.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	Optional<Usuario> findByUsername(String username);
}
