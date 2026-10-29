package com.krakedev.proyectos.repositorie;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krakedev.proyectos.entidade.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

}
