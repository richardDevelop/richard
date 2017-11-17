/**
 * 
 */
package com.richardevelop.learning.vo;

import java.io.Serializable;
/**
 * @author Othoniel
 *
 */
public class ModPerfilVO implements Serializable{

	//SerialVersion
	private static final long serialVersionUID = 1L;
	
    // Fields    

     private ModPerfilIdVO idVO;
	/**
	 * 
	 */
	public ModPerfilVO() {
		// TODO Auto-generated constructor stub
	}
	 /** full constructor */
    public ModPerfilVO(ModPerfilIdVO idVO) {
        this.idVO = idVO;
    }

	/**
	 * @return the idVO
	 */
	public ModPerfilIdVO getIdVO() {
		return idVO;
	}
	/**
	 * @param idVO the idVO to set
	 */
	public void setIdVO(ModPerfilIdVO idVO) {
		this.idVO = idVO;
	}

}
