/**
 * 
 */
package com.richardevelop.learning.vo;

/**
 * @author WebServer
 *
 */
public class RespuestaVO implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer respId;
	private Integer preId;
	private String respuesta;
	private String orden;
	private Boolean esCorrecta;

	public RespuestaVO() {
	}

	/**
	 * @return the respId
	 */
	public Integer getRespId() {
		return respId;
	}

	/**
	 * @param respId the respId to set
	 */
	public void setRespId(Integer respId) {
		this.respId = respId;
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
	 * @return the respuesta
	 */
	public String getRespuesta() {
		return respuesta;
	}

	/**
	 * @param respuesta the respuesta to set
	 */
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	/**
	 * @return the orden
	 */
	public String getOrden() {
		return orden;
	}

	/**
	 * @param orden the orden to set
	 */
	public void setOrden(String orden) {
		this.orden = orden;
	}

	/**
	 * @return the esCorrecta
	 */
	public Boolean getEsCorrecta() {
		return esCorrecta;
	}

	/**
	 * @param esCorrecta the esCorrecta to set
	 */
	public void setEsCorrecta(Boolean esCorrecta) {
		this.esCorrecta = esCorrecta;
	}

}
