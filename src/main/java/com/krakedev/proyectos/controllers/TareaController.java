package com.krakedev.proyectos.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krakedev.proyectos.entidade.Tarea;
import com.krakedev.proyectos.services.TareaService;

@RestController
@RequestMapping("/api/tarea")
public class TareaController {
	private final TareaService service;

	public TareaController(TareaService service) {
		super();
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<?> guardar(@RequestBody Tarea tarea) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(tarea));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al guardar la tarea: " + e.getMessage());

		}
	}

	@GetMapping
	public ResponseEntity<?> listar() {
		try {
			List<Tarea> tarea = service.listar();
			return ResponseEntity.ok(tarea);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Eroor al listar tarea :" + e.getMessage());

		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscar(@PathVariable int id) {
		try {
			Tarea tarea = service.buscarPorId(id);
			if (tarea == null) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(tarea);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al buscar la tarea: " + e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> actualizar(@PathVariable int id, @RequestBody Tarea datos) {
		try {
			Tarea tarea = service.actualizar(id, datos);
			if (tarea == null) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(tarea);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al actualizar tareas: " + e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable int id) {
		try {
			if (!service.eliminar(id)) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al eliminar la tarea : " + e.getMessage());
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> consultarTareaId(@PathVariable int id) {
		try {
			Tarea tarea = service.buscarPorId(id);
			if (tarea == null) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(tarea);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Erro al consultar la tarea: " + e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarPorId(@PathVariable int id) {
		try {
			if (!service.eliminar(id)) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al eliminar por Id : " + e.getMessage());
		}
	}
}
