package com.richarddevelop.learning.model;

// default package

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * UsuEvaluacion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "usuevaluacion")
public class UsuEvaluacion implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3711402917773458235L;
	// Fields

	@EmbeddedId
	private UserEvaluacionId userEvaluacionId;
	@Column(name = "usuario")
	private String usuario;
	@Column(name = "fAlta")
	private Timestamp falta;
	@Column(name = "fModifica")
	private Timestamp fmodifica;
	@Column(name = "activo")
	private Boolean activo;

	// Constructors

	/** default constructor */
	public UsuEvaluacion() {
	}
	
	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
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










	/**
	 * @param userEvaluacionId the userEvaluacionId to set
	 */
	public void setUserEvaluacionId(UserEvaluacionId userEvaluacionId) {
		this.userEvaluacionId = userEvaluacionId;
	}










	/**
	 * @return the userEvaluacionId
	 */
	public UserEvaluacionId getUserEvaluacionId() {
		return userEvaluacionId;
	}

}