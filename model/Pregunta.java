package com.evaluacionlinea.model;
// default package

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.evaluacionlinea.ConversionServices;
import com.evaluacionlinea.vo.PreguntaVO;
import com.evaluacionlinea.vo.RespuestaVO;


/**
 * Pregunta entity. @author MyEclipse Persistence Tools
 */

public class Pregunta  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer preId;
	private Integer cueId;
	private String pregunta;
	private Set<Respuesta> respuestas;
	//solo para la vista
	private Contesto contesto;
	private List<Respuesta> lstRespuesta;
	private int orderPre;


    // Constructors

    /** default constructor */
    public Pregunta() {
    }


	/**
	 * @return the pregId
	 */
	public Integer getPreId() {
		return preId;
	}


	/**
	 * @param pregId the pregId to set
	 */
	public void setPreId(Integer preId) {
		this.preId = preId;
	}


	/**
	 * @return the cueId
	 */
	public Integer getCueId() {
		return cueId;
	}


	/**
	 * @param cueId the cueId to set
	 */
	public void setCueId(Integer cueId) {
		this.cueId = cueId;
	}


	/**
	 * @return the pregunta
	 */
	public String getPregunta() {
		return pregunta;
	}


	/**
	 * @param pregunta the pregunta to set
	 */
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}


	/**
	 * @return the respuestas
	 */
	public Set<Respuesta> getRespuestas() {
		return respuestas;
	}


	/**
	 * @param respuestas the respuestas to set
	 */
	public void setRespuestas(Set<Respuesta> respuestas) {
		this.respuestas = respuestas;
	}


	/**
	 * @return the contesto
	 */
	public Contesto getContesto() {
		return contesto;
	}


	/**
	 * @param contesto the contesto to set
	 */
	public void setContesto(Contesto contesto) {
		this.contesto = contesto;
	}


	/**
	 * @return the lstRespuesta
	 */
	public List<Respuesta> getLstRespuesta() {
		return lstRespuesta;
	}


	/**
	 * @param lstRespuesta the lstRespuesta to set
	 */
	public void setLstRespuesta(List<Respuesta> lstRespuesta) {
		this.lstRespuesta = lstRespuesta;
	}


	/**
	 * @return the orderPre
	 */
	public int getOrderPre() {
		return orderPre;
	}


	/**
	 * @param orderPre the orderPre to set
	 */
	public void setOrderPre(int orderPre) {
		this.orderPre = orderPre;
	}
	
	public List<RespuestaVO> getRespuestasVO(){
		List<RespuestaVO> list = new ArrayList<RespuestaVO>();
		try {
			for (Respuesta respuesta: this.respuestas) {
				list.add((RespuestaVO) ConversionServices.transformPojoToVo(respuesta));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	
	
}