package com.richarddevelop.learning.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "expositor")
public class Expositor implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6156363676747089005L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "exp_id")
	private Integer expId;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "activo")
	private Boolean activo;
	
	
	
	public Expositor(){
		
	}

	/**
	 * @return the expId
	 */
	public Integer getExpId() {
		return expId;
	}

	/**
	 * @param expId the expId to set
	 */
	public void setExpId(Integer expId) {
		this.expId = expId;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the activo
	 */
	public Boolean getActivo() {
		return activo;
	}

	/**
	 * @param activo the activo to set
	 */
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	
	
	
	
	

}
