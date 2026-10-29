package com.krakedev.proyectos.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.krakedev.proyectos.entidade.Empleado;
import com.krakedev.proyectos.repositorie.EmpleadoRepository;

@Service
public class EmpleadoService {

	private final EmpleadoRepository empleadoRepository;

	public EmpleadoService(EmpleadoRepository empleadoRepository) {
		super();
		this.empleadoRepository = empleadoRepository;
	}

	public Empleado guardar(Empleado empleado) {
		return empleadoRepository.save(empleado);
	}

	public List<Empleado> listar() {
		return empleadoRepository.findAll();
	}

	public Empleado buscarPorId(int id) {
		return empleadoRepository.findById(id).orElse(null);
	}

	public boolean eliminar(int id) {
		if (empleadoRepository.existsById(id)) {
			empleadoRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	public Empleado actualizar(int id, Empleado datos) {
		Empleado empleado = buscarPorId(id);
		if (empleado != null) {
			empleado.setNombre(datos.getNombre());
			empleado.setCargo(datos.getCargo());
			empleado.setTareas(datos.getTareas());
			empleado.setDepartamento(datos.getDepartamento());

			return empleadoRepository.save(empleado);

		} else {
			return null;
		}
	}
}
