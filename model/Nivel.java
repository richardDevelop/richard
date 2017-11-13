package com.evaluacionlinea.model;
// default package



/**
 * Nivel entity. @author MyEclipse Persistence Tools
 */
public class Nivel implements java.io.Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


    private Integer nivId;
    private String nivel;
    private String descripcion;
    private Boolean activo;
    
    /** default constructor */
    public Nivel() {    	
    }

    public Nivel(Nivel nivel) {
    	this.nivId = nivel.getNivId();
    	this.nivel = nivel.getNivel();
    	this.descripcion = nivel.getDescripcion();
    	this.activo = nivel.getActivo();
    }
    
	/**
	 * @return the nivId
	 */
	public Integer getNivId() {
		return nivId;
	}

	/**
	 * @param nivId the nivId to set
	 */
	public void setNivId(Integer nivId) {
		this.nivId = nivId;
	}

	/**
	 * @return the nivel
	 */
	public String getNivel() {
		return nivel;
	}

	/**
	 * @param nivel the nivel to set
	 */
	public void setNivel(String nivel) {
		this.nivel = nivel;
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
