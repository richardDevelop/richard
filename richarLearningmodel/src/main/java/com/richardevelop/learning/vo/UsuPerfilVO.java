/**
 * 
 */
package com.richardevelop.learning.vo;

import java.io.Serializable;

import com.richarddevelop.learning.model.Perfil;
import com.richarddevelop.learning.model.Users;
import com.richarddevelop.learning.util.ConversionServices;

/**
 * @author Othoniel
 *
 */
public class UsuPerfilVO implements Serializable{

	//SerialVersion
	private static final long serialVersionUID = 1L;
	
	// Fields
    private UsuPerfilIdVO idVO;
	/**
	 * 
	 */
	public UsuPerfilVO() {
		// TODO Auto-generated constructor stub
	}
	
	public UsuPerfilVO(Integer perfId) {
    	PerfilVO perfilVO = new PerfilVO();
    	perfilVO.setPerfId(perfId);
    	this.idVO = new UsuPerfilIdVO();
    	this.idVO.setPerfilVO(perfilVO);
    }
    
    /** full constructor */
    public UsuPerfilVO(UsuPerfilIdVO idVO) {
        this.idVO = idVO;
    }
	/**
	 * @return the idVO
	 */
	public UsuPerfilIdVO getIdVO() {
		return idVO;
	}
	/**
	 * @param idVO the idVO to set
	 */
	public void setIdVO(UsuPerfilIdVO idVO) {
		this.idVO = idVO;
	}
	
	

}
