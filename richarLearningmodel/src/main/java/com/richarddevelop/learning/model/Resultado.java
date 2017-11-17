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
 * Resultado entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "resultado")
public class Resultado implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5663029467832218483L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "res_id")
	private Integer resId;

	@Column(name = "usu_id")
	private Integer usuId;

	@Column(name = "eva_id")
	private Integer evaId;

	@Column(name = "calificacion")
	private Float calificacion;

	@Column(name = "calAprobatoria")
	private Boolean calAprobatoria;

	@Column(name = "hIncio")
	private Timestamp hincio;

	@Column(name = "hFin")
	private Timestamp hfin;

	@Column(name = "res_total")
	private Integer resTotal;

	// Constructors

	/** default constructor */
	public Resultado() {
	}

	/**
	 * @return the resId
	 */
	public Integer getResId() {
		return resId;
	}

	/**
	 * @param resId
	 *            the resId to set
	 */
	public void setResId(Integer resId) {
		this.resId = resId;
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
	 * @return the calificacion
	 */
	public Float getCalificacion() {
		return calificacion;
	}

	/**
	 * @param calificacion
	 *            the calificacion to set
	 */
	public void setCalificacion(Float calificacion) {
		this.calificacion = calificacion;
	}

	/**
	 * @return the calAprobatoria
	 */
	public Boolean getCalAprobatoria() {
		return calAprobatoria;
	}

	/**
	 * @param calAprobatoria
	 *            the calAprobatoria to set
	 */
	public void setCalAprobatoria(Boolean calAprobatoria) {
		this.calAprobatoria = calAprobatoria;
	}

	/**
	 * @return the hincio
	 */
	public Timestamp getHincio() {
		return hincio;
	}

	/**
	 * @param hincio
	 *            the hincio to set
	 */
	public void setHincio(Timestamp hincio) {
		this.hincio = hincio;
	}

	/**
	 * @return the hfin
	 */
	public Timestamp getHfin() {
		return hfin;
	}

	/**
	 * @param hfin
	 *            the hfin to set
	 */
	public void setHfin(Timestamp hfin) {
		this.hfin = hfin;
	}

	public Integer getResTotal() {
		return resTotal;
	}

	public void setResTotal(Integer resTotal) {
		this.resTotal = resTotal;
	}

}