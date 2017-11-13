/**
 * 
 */
package com.evaluacionlinea.vo;

import java.sql.Timestamp;

/**
 * @author WebServer
 *
 */
public class ResultadoVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer resId;
    private Integer usuId;
    private Integer evaId;
    private Float calificacion;
    private Boolean calAprobatoria;
    private Timestamp hincio;
    private Timestamp hfin;
    private Integer resTotal;

	
	/**
	 * 
	 */
	public ResultadoVO() {
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
	 * @return the calificacion
	 */
	public Float getCalificacion() {
		return calificacion;
	}


	/**
	 * @param calificacion the calificacion to set
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
	 * @param calAprobatoria the calAprobatoria to set
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
	 * @param hincio the hincio to set
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
	 * @param hfin the hfin to set
	 */
	public void setHfin(Timestamp hfin) {
		this.hfin = hfin;
	}


	/**
	 * @return the resTotal
	 */
	public Integer getResTotal() {
		return resTotal;
	}


	/**
	 * @param resTotal the resTotal to set
	 */
	public void setResTotal(Integer resTotal) {
		this.resTotal = resTotal;
	}

}
