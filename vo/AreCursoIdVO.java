/**
 * 
 */
package com.evaluacionlinea.vo;

import java.io.Serializable;

/**
 * @author Othoniel
 *
 */
public class AreCursoIdVO implements Serializable{

	//SerialVersion
		private static final long serialVersionUID = 1L;
		
	    // Fields    

	     private AreaVO areaVO;
	     private CursoVO cursoVO;
	/**
	 * 
	 */
	public AreCursoIdVO() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the areaVO
	 */
	public AreaVO getAreaVO() {
		return areaVO;
	}
	/**
	 * @param areaVO the areaVO to set
	 */
	public void setAreaVO(AreaVO areaVO) {
		this.areaVO = areaVO;
	}
	/**
	 * @return the cursoVO
	 */
	public CursoVO getCursoVO() {
		return cursoVO;
	}
	/**
	 * @param cursoVO the cursoVO to set
	 */
	public void setCursoVO(CursoVO cursoVO) {
		this.cursoVO = cursoVO;
	}

}
