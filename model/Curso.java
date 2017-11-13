package com.evaluacionlinea.model;
// default package

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


/**
 * Curso entity. @author MyEclipse Persistence Tools
 */

public class Curso  implements java.io.Serializable {


    // Fields    

     private Integer curId;
     private String nombre;
     private String descripcion;
     private Timestamp falta;
     private Timestamp fmodifica;
     private Boolean activo;
     private String usuario;
     private String area;
     private String estatus;


    // Constructors



	/** default constructor */
    public Curso() {
    }


    // Property accessors

    public Integer getCurId() {
        return this.curId;
    }
    
    public void setCurId(Integer curId) {
        this.curId = curId;
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

    public Timestamp getFalta() {
        return this.falta;
    }
    
    public void setFalta(Timestamp falta) {
        this.falta = falta;
    }

    public Timestamp getFmodifica() {
        return this.fmodifica;
    }
    
    public void setFmodifica(Timestamp fmodifica) {
        this.fmodifica = fmodifica;
    }

    public Boolean getActivo() {
        return this.activo;
    }
    
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

	public String getEstatus() {
		return estatus;
	}


	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
   

    public String getArea() {
		return area;
	}


	public void setArea(String area) {
		this.area = area;
	}








}