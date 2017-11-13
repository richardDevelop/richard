package com.evaluacionlinea.model;

import java.io.Serializable;

/**
 * AreCurso entity. @author MyEclipse Persistence Tools
 */

public class AreCurso implements Serializable {

	//SerialVersion
	private static final long serialVersionUID = 1L;
	
    // Fields
    private AreCursoId id;


    // Constructors

    /** default constructor */
    public AreCurso() {
    }

    
    /** full constructor */
    public AreCurso(AreCursoId id) {
        this.id = id;
    }

   
    // Property accessors

    public AreCursoId getId() {
        return this.id;
    }
    
    public void setId(AreCursoId id) {
        this.id = id;
    }
}