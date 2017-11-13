package com.evaluacionlinea.model;


/**
 * 
 * @author gdejesus
 *
 */
public class Respuesta  implements java.io.Serializable {

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer respId;
	private Integer preId;
	private String respuesta;
	private String orden;
	private Boolean esCorrecta;
	//Vista
	private String strCorrecta;
	private String opcion;

    // Constructors

    /** default constructor */
    public Respuesta() {
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
//		this.opcion = orden.equals(0) ? 
//				"a" : orden.equals(1) ? "b" : "c";
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
		this.strCorrecta = esCorrecta ? "Correcta" : "Incorrecta";
	}

	/**
	 * @return the strCorrecta
	 */
	public String getStrCorrecta() {
		return strCorrecta;
	}

	/**
	 * @param strCorrecta the strCorrecta to set
	 */
	public void setStrCorrecta(String strCorrecta) {
		this.strCorrecta = strCorrecta;
	}

	/**
	 * @return the opcion
	 */
	public String getOpcion() {
		return opcion;
	}

	/**
	 * @param opcion the opcion to set
	 */
	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}
	
	
	
	
    
}