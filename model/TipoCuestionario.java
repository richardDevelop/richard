package com.evaluacionlinea.model;
// default package

import java.util.HashSet;
import java.util.Set;


/**
 * TipoCuestionario entity. @author MyEclipse Persistence Tools
 */

public class TipoCuestionario  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer tcueId;
     private String nombre;
     private String descripcion;
     private Boolean activo;


    // Constructors

    /** default constructor */
    public TipoCuestionario() {
    }


   
    // Property accessors

    public Integer getTcueId() {
        return this.tcueId;
    }
    
    public void setTcueId(Integer tcueId) {
        this.tcueId = tcueId;
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