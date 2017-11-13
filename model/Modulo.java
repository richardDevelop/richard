package com.evaluacionlinea.model;
// default package

/**
 * Modulo entity. @author MyEclipse Persistence Tools
 */

public class Modulo implements java.io.Serializable {

	//SerialVersion
	private static final long serialVersionUID = 1L;

    // Fields    

     private Integer modId;
     private String nombre;
     private String descripcion;
     private Boolean activo;


    // Constructors

    /** default constructor */
    public Modulo() {
    }

	/** minimal constructor */
    public Modulo(String nombre, Boolean activo) {
        this.nombre = nombre;
        this.activo = activo;
    }
    
    /** full constructor */
    public Modulo(String nombre, String descripcion, Boolean activo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activo = activo;
    }

   
    // Property accessors

    public Integer getModId() {
        return this.modId;
    }
    
    public void setModId(Integer modId) {
        this.modId = modId;
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