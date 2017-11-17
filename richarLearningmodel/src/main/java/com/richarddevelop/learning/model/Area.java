package com.richarddevelop.learning.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// default package

/**
 * Area entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "area")
public class Area implements java.io.Serializable {

	// SerialVersion
	private static final long serialVersionUID = 1L;
	// Fields

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="are_id")
	private Integer areId;
	@Column(name = "nombre", nullable = false)
	private String nombre;
	@Column(name = "descripcion", nullable = false)
	private String descripcion;
	@Column(name = "activo", nullable = false)
	private Boolean activo;

	// Constructors

	/** default constructor */
	public Area() {
	}

	/** minimal constructor */
	public Area(String nombre, Boolean activo) {
		this.nombre = nombre;
		this.activo = activo;
	}

	/** full constructor */
	public Area(String nombre, String descripcion, Boolean activo) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.activo = activo;
	}

	// Property accessors

	public Integer getAreId() {
		return this.areId;
	}

	public void setAreId(Integer areId) {
		this.areId = areId;
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