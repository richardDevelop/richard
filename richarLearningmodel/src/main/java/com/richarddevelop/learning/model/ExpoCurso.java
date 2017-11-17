package com.richarddevelop.learning.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name ="expo_curso")
public class ExpoCurso implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7612578852401607106L;

	@ManyToOne(optional = false)
	@JoinColumn(name= "exp_id")
	@Id
	private Expositor expositor;
	
	@ManyToOne(optional = false)
	@JoinColumn(name= "cur_id")
	@Id
	private Curso curso;

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
