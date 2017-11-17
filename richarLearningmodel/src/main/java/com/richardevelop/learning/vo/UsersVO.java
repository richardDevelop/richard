/**
 * 
 */
package com.richardevelop.learning.vo;

import java.io.Serializable;

/**
 * @author Othoniel
 *
 */
public class UsersVO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer usuId;
	private String usuario;
	private String password;
	private Boolean activo;
     
	/**
	 * 
	 */
	public UsersVO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the usuId
	 */
	public Integer getUsuId() {
		return usuId;
	}

	/**
	 * @param usuId the usuId to set
	 */
	public void setUsuId(Integer usuId) {
		this.usuId = usuId;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the activo
	 */
	public Boolean getActivo() {
		return activo;
	}

	/**
	 * @param activo the activo to set
	 */
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

}
