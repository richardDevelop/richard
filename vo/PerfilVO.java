/**
 * 
 */
package com.evaluacionlinea.vo;

import java.io.Serializable;

/**
 * @author Othoniel
 *
 */
public class PerfilVO implements Serializable{

	//SerialVersion
	private static final long serialVersionUID = 1L;
    // Fields    

     private Integer perfId;
     private String nombre;
     private String descripcion;
     private Boolean activo;
	/**
	 * 
	 */
	public PerfilVO() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the perfId
	 */
	public Integer getPerfId() {
		return perfId;
	}
	/**
	 * @param perfId the perfId to set
	 */
	public void setPerfId(Integer perfId) {
		this.perfId = perfId;
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
