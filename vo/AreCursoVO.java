/**
 * 
 */
package com.evaluacionlinea.vo;

import java.io.Serializable;

/**
 * @author Othoniel
 *
 */
public class AreCursoVO implements Serializable{

	//SerialVersion
	private static final long serialVersionUID = 1L;
	
    // Fields
    private AreCursoIdVO idVO;
	/**
	 * 
	 */
	public AreCursoVO() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the idVO
	 */
	public AreCursoIdVO getIdVO() {
		return idVO;
	}
	/**
	 * @param idVO the idVO to set
	 */
	public void setIdVO(AreCursoIdVO idVO) {
		this.idVO = idVO;
	}

}
