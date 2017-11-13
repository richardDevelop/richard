package com.evaluacionlinea.model;
// default package

/**
 * Area entity. @author MyEclipse Persistence Tools
 */

public class Area  implements java.io.Serializable {

	//SerialVersion
	private static final long serialVersionUID = 1L;
    // Fields    

     private Integer areId;
     private String nombre;
     private String descripcion;
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