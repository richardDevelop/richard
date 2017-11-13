package com.evaluacionlinea.model;

import java.io.Serializable;

public class ExpoCursoId  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Expositor expositor;
	
	private Curso curso;
	
	public ExpoCursoId(){
		
	}
	
	public ExpoCursoId(Expositor expositor, Curso curso){
		this.expositor = expositor;
		this.curso = curso;
	}

	/**
	 * @return the expositor
	 */
	public Expositor getExpositor() {
		return expositor;
	}

	/**
	 * @param expositor the expositor to set
	 */
	public void setExpositor(Expositor expositor) {
		this.expositor = expositor;
	}

	/**
	 * @return the curso
	 */
	public Curso getCurso() {
		return curso;
	}

	/**
	 * @param curso the curso to set
	 */
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
	
	
	
	

}
