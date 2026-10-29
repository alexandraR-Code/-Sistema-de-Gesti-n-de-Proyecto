package com.krakedev.proyectos.entidade;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity // Marca la clase como un tabla de la base de datos.Cada obejto es igual a una
// fila, cada atributo es una columna
@Table(name = "tareas")
public class Tarea {
	@Id // Indica que esta es la clave primaria de la tabla
	@GeneratedValue(strategy = GenerationType.IDENTITY) // genera un Id automaticamente
	private int id;

	@Column(nullable = false, length = 200)
	private String descripcion;

	@Column(nullable = false)
	private LocalDate fechaLimite;

	@Column(nullable = false)
	private double costoEstimado;

	@ManyToOne // Relacion de muchos a uno, muchos objetos de esta clase pertenecen a un
				// solo objeto de otra clase
	@JoinColumn(name = "proyecto_id", nullable = false) // Creea columna Fk (foranea) que apunta a otra tabla
	private Proyecto proyecto;

	@ManyToMany // Relacion de muchos a muchos
	// Crea una tabla intermedia entre dos entidades
	@JoinTable(name = "tarea_empleados", joinColumns = @JoinColumn(name = "tarea_id"), inverseJoinColumns = @JoinColumn(name = "empleado_id"))

	private List<Empleado> empleados;

	@Column(name = "estado", nullable = true)
	private String estado;

	public Tarea() {
		super();
	}

	public Tarea(String descripcion, LocalDate fechaLimite, double costoEstimado, Proyecto proyecto,
			List<Empleado> empleados, String estado) {
		super();
		this.descripcion = descripcion;
		this.fechaLimite = fechaLimite;
		this.costoEstimado = costoEstimado;
		this.proyecto = proyecto;
		this.empleados = empleados;
		this.estado = estado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public LocalDate getFechaLimite() {
		return fechaLimite;
	}

	public void setFechaLimite(LocalDate fechaLimite) {
		this.fechaLimite = fechaLimite;
	}

	public double getCostoEstimado() {
		return costoEstimado;
	}

	public void setCostoEstimado(double costoEstimado) {
		this.costoEstimado = costoEstimado;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public List<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
