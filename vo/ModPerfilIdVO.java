/**
 * 
 */
package com.evaluacionlinea.vo;

import java.io.Serializable;

/**
 * @author Othoniel
 *
 */
public class ModPerfilIdVO implements Serializable{

	//SerialVersion
	private static final long serialVersionUID = 1L;
	
    // Fields    

     private ModuloVO moduloVO;
     private PerfilVO perfilVO;
	/**
	 * 
	 */
	public ModPerfilIdVO() {
		// TODO Auto-generated constructor stub
	}
	
	/** full constructor */
    public ModPerfilIdVO(ModuloVO moduloVO, PerfilVO perfilVO) {
        this.moduloVO = moduloVO;
        this.perfilVO = perfilVO;
    }
	/**
	 * @return the moduloVO
	 */
	public ModuloVO getModuloVO() {
		return moduloVO;
	}
	/**
	 * @param moduloVO the moduloVO to set
	 */
	public void setModuloVO(ModuloVO moduloVO) {
		this.moduloVO = moduloVO;
	}
	/**
	 * @return the perfilVO
	 */
	public PerfilVO getPerfilVO() {
		return perfilVO;
	}
	/**
	 * @param perfilVO the perfilVO to set
	 */
	public void setPerfilVO(PerfilVO perfilVO) {
		this.perfilVO = perfilVO;
	}

}
