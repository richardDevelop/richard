/**
 * 
 */
package com.evaluacionlinea.vo;

import java.io.Serializable;

/**
 * @author Othoniel
 *
 */
public class ModuloVO implements Serializable{

	//SerialVersion
	private static final long serialVersionUID = 1L;

    // Fields    

     private Integer modId;
     private String nombre;
     private String descripcion;
     private Boolean activo;

	/**
	 * 
	 */
	public ModuloVO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the modId
	 */
	public Integer getModId() {
		return modId;
	}

	/**
	 * @param modId the modId to set
	 */
	public void setModId(Integer modId) {
		this.modId = modId;
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
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
