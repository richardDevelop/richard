package com.evaluacionlinea.model;

import java.io.Serializable;

public class Expositor implements Serializable {
	
	private Integer expId;
	
	private String nombre;
	
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
