package com.evaluacionlinea.model;
// default package

import java.sql.Timestamp;


/**
 * EvaCurso entity. @author MyEclipse Persistence Tools
 */

public class EvaCurso  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer ecurId;
	private Integer evaId;
	private Integer usuId;
	private Integer bloque;
	private Timestamp hinicio;
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
	 * @param ecurId the ecurId to set
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
	 * @param evaId the evaId to set
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
	 * @param usuId the usuId to set
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
	 * @param bloque the bloque to set
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
	 * @param hinicio the hinicio to set
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
	 * @param hbloque the hbloque to set
	 */
	public void setHbloque(Timestamp hbloque) {
		this.hbloque = hbloque;
	}


}