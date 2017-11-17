package com.richarddevelop.learning.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// default package

/**
 * Usuario entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "usuario")
public class Users implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7611987047067763761L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="usu_id")
	private Integer usuId;
	
	@Column(name ="usuario")
	private String usuario;

	@Column(name ="activo")
	private Boolean activo;

	private String password;

	// Constructors

	/** default constructor */
	public Users() {
	}

	public Users(Users users) {
		this.usuId = users.getUsuId();
		this.usuario = users.getUsuario();
		this.password = users.getPassword();
		this.activo = users.getActivo();
	}

	/** minimal constructor */
	public Users(String usuario, String contra, Boolean activo) {
		this.usuario = usuario;
		this.password = contra;
		this.activo = activo;
	}

	// Property accessors

	public Integer getUsuId() {
		return this.usuId;
	}

	public void setUsuId(Integer usuId) {
		this.usuId = usuId;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getActivo() {
		return this.activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

}