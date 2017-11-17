package com.richarddevelop.learning.model;
// default package

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * TipoCuestionario entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tipocuestionario")
public class TipoCuestionario  implements java.io.Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = -3923736061847517412L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="tcue_id")
	private Integer tcueId;
	
	@Column(name ="nombre")
     private String nombre;
	
	@Column(name ="descripcion")
     private String descripcion;
	
	@Column(name ="activo")
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