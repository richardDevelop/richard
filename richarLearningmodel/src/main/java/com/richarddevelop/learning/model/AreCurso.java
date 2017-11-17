package com.richarddevelop.learning.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * AreCurso entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "arecurso")
public class AreCurso implements Serializable {

	//SerialVersion
	private static final long serialVersionUID = 1L;
	
    // Fields
	@ManyToOne(optional = false)
	@JoinColumn(name="are_id")
	@Id
	private Area area;

	@ManyToOne(optional = false)
	@JoinColumn(name="cur_id")
	@Id
	private Curso curso;

	/**
	 * @param area the area to set
	 */
	public void setArea(Area area) {
		this.area = area;
	}

	/**
	 * @return the area
	 */
	public Area getArea() {
		return area;
	}

	/**
	 * @param curso the curso to set
	 */
	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	/**
	 * @return the curso
	 */
	public Curso getCurso() {
		return curso;
	}
}