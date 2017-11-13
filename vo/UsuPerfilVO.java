/**
 * 
 */
package com.evaluacionlinea.vo;

import java.io.Serializable;

import com.evaluacionlinea.ConversionServices;
import com.evaluacionlinea.model.Perfil;
import com.evaluacionlinea.model.Users;
import com.evaluacionlinea.model.UsuPerfilId;

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
	
	
	public UsuPerfilId getId() {
    	UsuPerfilId usuPerfilId = new UsuPerfilId();
    	usuPerfilId.setPerfil((Perfil)ConversionServices.transformVoToPojo(this.idVO.getPerfilVO()));
    	usuPerfilId.setUsers((Users)ConversionServices.transformVoToPojo(this.idVO.getUsersVO()));
        return usuPerfilId;
    }
}
