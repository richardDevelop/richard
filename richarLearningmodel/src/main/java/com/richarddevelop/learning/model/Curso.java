package com.richarddevelop.learning.model;

// default package

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Curso entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "curso")
public class Curso implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8233606274439925294L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cur_id")
	private Integer curId;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "falta")
	private Timestamp falta;

	@Column(name = "fmodifica")
	private Timestamp fmodifica;

	@Column(name = "activo")
	private Boolean activo;

	@Column(name = "usuario")
	private String usuario;

	private String area;

	private String estatus;

	// Constructors

	/** default constructor */
	public Curso() {
	}

	// Property accessors

	public Integer getCurId() {
		return this.curId;
	}

	public void setCurId(Integer curId) {
		this.curId = curId;
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

	public Timestamp getFalta() {
		return this.falta;
	}

	public void setFalta(Timestamp falta) {
		this.falta = falta;
	}

	public Timestamp getFmodifica() {
		return this.fmodifica;
	}

	public void setFmodifica(Timestamp fmodifica) {
		this.fmodifica = fmodifica;
	}

	public Boolean getActivo() {
		return this.activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

}