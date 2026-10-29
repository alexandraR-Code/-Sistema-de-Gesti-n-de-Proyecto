package com.krakedev.proyectos.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.krakedev.proyectos.entidade.Empleado;
import com.krakedev.proyectos.entidade.Proyecto;
import com.krakedev.proyectos.entidade.Tarea;
import com.krakedev.proyectos.repositorie.EmpleadoRepository;
import com.krakedev.proyectos.repositorie.ProyectoRepository;
import com.krakedev.proyectos.repositorie.TareaRepository;

import jakarta.transaction.Transactional;

@Service
public class TareaService {

	private final TareaRepository tareaRepository;
	private final ProyectoRepository proyectoRepository;
	private final EmpleadoRepository empleadoRepository;

	public TareaService(TareaRepository tareaRepository, ProyectoRepository proyectoRepository,
			EmpleadoRepository empleadoRepository) {
		super();
		this.tareaRepository = tareaRepository;
		this.proyectoRepository = proyectoRepository;
		this.empleadoRepository = empleadoRepository;
	}

	@Transactional
	public Tarea guardar(Tarea tarea) {
		if (tarea.getProyecto() == null) {
			throw new IllegalArgumentException("Debe indicar el proyecto d ela tarea");
		}
		int proyectoId = tarea.getProyecto().getId();
		Proyecto proyecto = proyectoRepository.findById(proyectoId)
				.orElseThrow(() -> new IllegalArgumentException("No existe el proyecto con ID" + proyectoId));
		List<Empleado> empleados = new ArrayList<>();
		if (tarea.getEmpleados() != null) {
			for (Empleado empleadoRecibido : tarea.getEmpleados()) {
				Empleado empleado = empleadoRepository.findById(empleadoRecibido.getId()).orElseThrow(
						() -> new IllegalArgumentException("No existe empledo con ID" + empleadoRecibido.getId()));
				empleados.add(empleado);
			}
		}
		tarea.setProyecto(proyecto);
		tarea.setEmpleados(empleados);

		return tareaRepository.save(tarea);
	}

	@Transactional
	public List<Tarea> listar() {
		return tareaRepository.findAll();
	}

	@Transactional
	public Tarea buscarPorId(int id) {
		return tareaRepository.findById(id).orElse(null);
	}

	@Transactional
	public boolean eliminar(int id) {
		if (tareaRepository.existsById(id)) {
			tareaRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	@Transactional
	public Tarea actualizar(int id, Tarea datos) {
		Tarea tarea = buscarPorId(id);
		if (tarea != null) {
			tarea.setDescripcion(datos.getDescripcion());
			tarea.setFechaLimite(datos.getFechaLimite());
			tarea.setCostoEstimado(datos.getCostoEstimado());
			tarea.setEstado(datos.getEstado());

			return tareaRepository.save(tarea);

		} else {
			return null;
		}

	}

}
