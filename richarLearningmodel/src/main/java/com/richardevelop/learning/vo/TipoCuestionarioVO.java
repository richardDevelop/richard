/**
 * 
 */
package com.richardevelop.learning.vo;

/**
 * @author WebServer
 *
 */
public class TipoCuestionarioVO implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer tcueId;
    private String nombre;
    private String descripcion;
    private Boolean activo;
	/**
	 * @return the tcueId
	 */
	public Integer getTcueId() {
		return tcueId;
	}
	/**
	 * @param tcueId the tcueId to set
	 */
	public void setTcueId(Integer tcueId) {
		this.tcueId = tcueId;
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
	/**
	 * 
	 */
	public TipoCuestionarioVO() {
		// TODO Auto-generated constructor stub
	}

}
