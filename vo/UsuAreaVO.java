/**
 * 
 */
package com.evaluacionlinea.vo;

import java.io.Serializable;

import com.evaluacionlinea.ConversionServices;
import com.evaluacionlinea.model.Area;
import com.evaluacionlinea.model.Users;
import com.evaluacionlinea.model.UsuAreaId;

/**
 * @author Othoniel
 *
 */
public class UsuAreaVO implements Serializable{

	//SerialVersion
	private static final long serialVersionUID = 1L;
	
    // Fields    

     private UsuAreaIdVO idVO;
	/**
	 * 
	 */
	public UsuAreaVO() {
		// TODO Auto-generated constructor stub
	}
	
	public UsuAreaVO(Integer usuId, Integer areaId) {
    	UsersVO usersVO = new UsersVO();
    	AreaVO areaVO = new AreaVO();
    	usersVO.setUsuId(usuId);
    	areaVO.setAreId(areaId);
    	this.idVO = new UsuAreaIdVO();
    	this.idVO.setAreaVO(areaVO);
    	this.idVO.setUsersVO(usersVO);
    }
    
    public UsuAreaVO(Integer areaId) {
    	AreaVO areaVO = new AreaVO();
    	areaVO.setAreId(areaId);
    	this.idVO = new UsuAreaIdVO();
    	this.idVO.setAreaVO(areaVO);
    }
    
    /** full constructor */
    public UsuAreaVO(UsuAreaIdVO idVO) {
        this.idVO = idVO;
    }
	/**
	 * @return the idVO
	 */
	public UsuAreaIdVO getIdVO() {
		return idVO;
	}
	/**
	 * @param idVO the idVO to set
	 */
	public void setIdVO(UsuAreaIdVO idVO) {
		this.idVO = idVO;
	}	
	
	public UsuAreaId getId() {
    	UsuAreaId usuAreaId = new UsuAreaId();
    	usuAreaId.setArea((Area)ConversionServices.transformVoToPojo(this.idVO.getAreaVO()));
    	usuAreaId.setUsers((Users)ConversionServices.transformVoToPojo(this.idVO.getUsersVO()));
        return usuAreaId;
    }
}
