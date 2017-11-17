package com.richarddevelop.learning.model;

// default package

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * EvaCurso entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "eva_encurso")
public class EvaCurso implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5229366550822949003L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ecur_id")
	private Integer ecurId;

	@Column(name = "eva_id")
	private Integer evaId;

	@Column(name = "usu_id")
	private Integer usuId;

	@Column(name = "bloque")
	private Integer bloque;

	@Column(name = "hInicio")
	private Timestamp hinicio;

	@Column(name = "hBloque")
	private Timestamp hbloque;

	// Constructors

	/** default constructor */
	public EvaCurso() {
	}

	/**
	 * @return the ecurId
	 */
	public Integer getEcurId() {
		return ecurId;
	}

	/**
	 * @param ecurId
	 *            the ecurId to set
	 */
	public void setEcurId(Integer ecurId) {
		this.ecurId = ecurId;
	}

	/**
	 * @return the evaId
	 */
	public Integer getEvaId() {
		return evaId;
	}

	/**
	 * @param evaId
	 *            the evaId to set
	 */
	public void setEvaId(Integer evaId) {
		this.evaId = evaId;
	}

	/**
	 * @return the usuId
	 */
	public Integer getUsuId() {
		return usuId;
	}

	/**
	 * @param usuId
	 *            the usuId to set
	 */
	public void setUsuId(Integer usuId) {
		this.usuId = usuId;
	}

	/**
	 * @return the bloque
	 */
	public Integer getBloque() {
		return bloque;
	}

	/**
	 * @param bloque
	 *            the bloque to set
	 */
	public void setBloque(Integer bloque) {
		this.bloque = bloque;
	}

	/**
	 * @return the hinicio
	 */
	public Timestamp getHinicio() {
		return hinicio;
	}

	/**
	 * @param hinicio
	 *            the hinicio to set
	 */
	public void setHinicio(Timestamp hinicio) {
		this.hinicio = hinicio;
	}

	/**
	 * @return the hbloque
	 */
	public Timestamp getHbloque() {
		return hbloque;
	}

	/**
	 * @param hbloque
	 *            the hbloque to set
	 */
	public void setHbloque(Timestamp hbloque) {
		this.hbloque = hbloque;
	}

}