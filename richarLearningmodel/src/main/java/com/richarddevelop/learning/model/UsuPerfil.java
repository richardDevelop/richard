package com.richarddevelop.learning.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;





/**
 * UsuPerfil entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "usuarea")
public class UsuPerfil  implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3499687999972980626L;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="perf_id")
	@Id
	private Perfil perfil;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="usu_id")
	@Id
    private Users users;

	/**
	 * @return the perfil
	 */
	public Perfil getPerfil() {
		return perfil;
	}

	/**
	 * @param perfil the perfil to set
	 */
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

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


	
}