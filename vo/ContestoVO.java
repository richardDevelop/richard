/**
 * 
 */
package com.evaluacionlinea.vo;

import java.sql.Timestamp;

import com.evaluacionlinea.utils.Const;

/**
 * @author WebServer
 *
 */
public class ContestoVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer contId;
    private Integer preId;
    private Integer usuId;
    private Integer evaId;
    private Integer resId;
    private Integer curId;
    private Timestamp falta;
    private Integer tcue;
    
    
	/**
	 * 
	 */
	public ContestoVO() {
		// TODO Auto-generated constructor stub
	}
	
	public ContestoVO(PreguntaVO pregunta, Integer evaId, Integer curId, Integer usuId, Integer tcue) {
    	this.preId = pregunta.getPreId();
    	this.curId = curId;
    	this.evaId = evaId;
    	this.usuId = usuId;
    	this.tcue = tcue;
    	this.falta = Const.getHoraActual();
    }
	
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
	 * @return the tcue
	 */
	public Integer getTcue() {
		return tcue;
	}

	/**
	 * @param tcue the tcue to set
	 */
	public void setTcue(Integer tcue) {
		this.tcue = tcue;
	}

}
