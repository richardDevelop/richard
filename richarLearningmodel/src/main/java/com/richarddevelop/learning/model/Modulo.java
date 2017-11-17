package com.richarddevelop.learning.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
// default package
import javax.persistence.Table;

/**
 * Modulo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "modulo")
public class Modulo implements java.io.Serializable {

	//SerialVersion
	private static final long serialVersionUID = 1L;

    // Fields    

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "mod_id")
     private Integer modId;
	
	@Column(name = "nombre")
     private String nombre;
	
	@Column(name = "descripcion")
     private String descripcion;
	
	@Column(name = "activo")
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