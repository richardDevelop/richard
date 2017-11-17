package com.richarddevelop.learning.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Embeddable
public class UserEvaluacionId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6686325303868303686L;

	@ManyToOne(optional = false)
	@JoinColumn(name = "eva_id")
	private Evaluacion evaluacion;

	@ManyToOne(optional = false)
	@JoinColumn(name = "usu_id")
	private Users users;

	/**
	 * @return the users
	 */
	public Users getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(Users users) {
		this.users = users;
	}

	/**
	 * @param evaluacion the evaluacion to set
	 */
	public void setEvaluacion(Evaluacion evaluacion) {
		this.evaluacion = evaluacion;
	}

	/**
	 * @return the evaluacion
	 */
	public Evaluacion getEvaluacion() {
		return evaluacion;
	}
	
	
}
