package com.krakedev.proyectos.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.krakedev.proyectos.entidade.Tarea;
import com.krakedev.proyectos.repositorie.EmpleadoRepository;
import com.krakedev.proyectos.repositorie.ProyectoRepository;
import com.krakedev.proyectos.repositorie.TareaRepository;

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

	public Tarea guardar(Tarea tarea) {
		return tareaRepository.save(tarea);
	}

	public List<Tarea> listar() {
		return tareaRepository.findAll();
	}

	public Tarea buscarPorId(int id) {
		return tareaRepository.findById(id).orElse(null);
	}

	public boolean eliminar(int id) {
		if (tareaRepository.existsById(id)) {
			tareaRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	public Tarea actualizar(int id, Tarea datos) {
		Tarea tarea = buscarPorId(id);
		if (tarea != null) {
			tarea.setDescripcion(datos.getDescripcion());
			tarea.setFechaLimite(datos.getFechaLimite());
			tarea.setCostoEstimado(datos.getCostoEstimado());

			return tareaRepository.save(tarea);

		} else {
			return null;
		}

	}

}
