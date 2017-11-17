package com.richarddevelop.learning.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// default package

/**
 * Perfil entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "perfil")
public class Perfil implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -666479958434394561L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "perf_id")
	private Integer perfId;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "activo")
	private Boolean activo;

	// Constructors

	/** default constructor */
	public Perfil() {
	}

	/** minimal constructor */
	public Perfil(String nombre, Boolean activo) {
		this.nombre = nombre;
		this.activo = activo;
	}

	/** full constructor */
	public Perfil(String nombre, String descripcion, Boolean activo) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.activo = activo;
	}

	// Property accessors

	public Integer getPerfId() {
		return this.perfId;
	}

	public void setPerfId(Integer perfId) {
		this.perfId = perfId;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getActivo() {
		return this.activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
}