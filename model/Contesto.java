package com.evaluacionlinea.model;
// default package

import java.sql.Timestamp;

import com.evaluacionlinea.utils.Const;


/**
 * Contesto entity. @author MyEclipse Persistence Tools
 */

public class Contesto  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer contId;
    private Integer preId;
    private Integer usuId;
    private Integer evaId;
    private Integer resId;
    private Integer curId;
    private Timestamp falta;
    private Integer tcue;


    // Constructors

    /** default constructor */
    public Contesto() {
    }
//
//    public Contesto(Pregunta pregunta, Integer evaId, Integer curId, Integer usuId) {
//    	this.respuesta = new Respuesta();
//    	this.pregunta = pregunta;
//    	this.curso = new Curso();
//    	this.curso.setCurId(curId);
//    	this.evaId = evaId;
//    	this.usuId = usuId;
//    	this.falta = Const.getHoraActual();
//    }


	/**
	 * @return the contId
	 */
	public Integer getContId() {
		return contId;
	}


	/**
	 * @param contId the contId to set
	 */
	public void setContId(Integer contId) {
		this.contId = contId;
	}

	/**
	 * @return the falta
	 */
	public Timestamp getFalta() {
		return falta;
	}


	/**
	 * @param falta the falta to set
	 */
	public void setFalta(Timestamp falta) {
		this.falta = falta;
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
	 * @return the preId
	 */
	public Integer getPreId() {
		return preId;
	}


	/**
	 * @param preId the preId to set
	 */
	public void setPreId(Integer preId) {
		this.preId = preId;
	}


	/**
	 * @return the resId
	 */
	public Integer getResId() {
		return resId;
	}


	/**
	 * @param resId the resId to set
	 */
	public void setResId(Integer resId) {
		this.resId = resId;
	}


	/**
	 * @return the curId
	 */
	public Integer getCurId() {
		return curId;
	}


	/**
	 * @param curId the curId to set
	 */
	public void setCurId(Integer curId) {
		this.curId = curId;
	}


	public Integer getTcue() {
		return tcue;
	}


	public void setTcue(Integer tcue) {
		this.tcue = tcue;
	}


}