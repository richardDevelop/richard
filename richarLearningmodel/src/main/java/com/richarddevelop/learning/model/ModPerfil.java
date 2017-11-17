package com.richarddevelop.learning.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;




/**
 * ModPerfil entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="modperfil")
public class ModPerfil implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3019382500379671268L;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "mod_id")
	@Id
	private Modulo modulo;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "perf_id")
	@Id
    private Perfil perfil;

	/**
	 * @return the modulo
	 */
	public Modulo getModulo() {
		return modulo;
	}

	/**
	 * @param modulo the modulo to set
	 */
	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

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
	
	
}