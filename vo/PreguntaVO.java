/**
 * 
 */
package com.evaluacionlinea.vo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @author gdejesus
 *
 */
public class PreguntaVO implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer preId;
	private Integer cueId;
	private String pregunta;
	private List<RespuestaVO> respuestasVO;
	private ContestoVO contestoVO;
	private int orderPre;
	

	public PreguntaVO() {
	}


	public PreguntaVO(PreguntaVO pregunta, Integer evaId, Integer curId, Integer usuId, Integer tcue) {
		this.setPreId(pregunta.getPreId());
		this.setCueId(pregunta.getCueId());
		this.setPregunta(pregunta.getPregunta());
		this.contestoVO =  new ContestoVO(pregunta, evaId, curId, usuId, tcue);
		
		this.setRespuestasVO(new ArrayList<RespuestaVO>(pregunta.getRespuestasVO()));
		
		Collections.sort(respuestasVO, new java.util.Comparator<RespuestaVO>() {
			@Override
			public int compare(RespuestaVO o1, RespuestaVO o2) {
				return o1.getOrden().compareTo(o2.getOrden());
			}
		});
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
	public List<RespuestaVO> getRespuestasVO() {
		return respuestasVO;
	}


	/**
	 * @param respuestas the respuestas to set
	 */
	public void setRespuestasVO(List<RespuestaVO> respuestasVO) {
		this.respuestasVO = respuestasVO;
	}


	/**
	 * @return the contesto
	 */
	public ContestoVO getContestoVO() {
		return contestoVO;
	}


	/**
	 * @param contesto the contesto to set
	 */
	public void setContestoVO(ContestoVO contestoVO) {
		this.contestoVO = contestoVO;
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
	
	

}
