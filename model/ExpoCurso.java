package com.evaluacionlinea.model;

import java.io.Serializable;

public class ExpoCurso implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ExpoCursoId id;

	
	
	public ExpoCurso() {
	}



	public ExpoCurso(ExpoCursoId id) {
		this.id = id;
	}



	/**
	 * @return the id
	 */
	public ExpoCursoId getId() {
		return id;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(ExpoCursoId id) {
		this.id = id;
	}
	
	
	
	
	
	
}
