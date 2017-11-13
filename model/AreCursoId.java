package com.evaluacionlinea.model;

import java.io.Serializable;


/**
 * AreCursoId entity. @author MyEclipse Persistence Tools
 */

public class AreCursoId implements Serializable{

	//SerialVersion
	private static final long serialVersionUID = 1L;
	
    // Fields    

     private Area area;
     private Curso curso;


    // Constructors

    /** default constructor */
    public AreCursoId() {
    }

    
    /** full constructor */
    public AreCursoId(Area area, Curso curso) {
        this.area = area;
        this.curso = curso;
    }

   
    // Property accessors

    public Area getArea() {
        return this.area;
    }
    
    public void setArea(Area area) {
        this.area = area;
    }

    public Curso getCurso() {
        return this.curso;
    }
    
    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}