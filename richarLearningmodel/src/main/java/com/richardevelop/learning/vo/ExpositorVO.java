package com.richardevelop.learning.vo;

import java.io.Serializable;

public class ExpositorVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5911648788368094697L;

	private Integer expId;
	
	private String nombre;
	
	private Boolean activo;
	

	
	
	public ExpositorVO(){
		
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
