/**
 * 
 */
package com.evaluacionlinea.vo;

import java.io.Serializable;

import com.evaluacionlinea.model.Area;
import com.evaluacionlinea.model.Users;

/**
 * @author Othoniel
 *
 */
public class UsuAreaIdVO implements Serializable{

	// Fields

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UsersVO usersVO;
	private AreaVO areaVO;
	/**
	 * 
	 */
	public UsuAreaIdVO() {
		// TODO Auto-generated constructor stub
	}
	
	/** full constructor */
    public UsuAreaIdVO(UsersVO usersVO, AreaVO areaVO) {
        this.usersVO = usersVO;
        this.areaVO = areaVO;
    }
	/**
	 * @return the usersVO
	 */
	public UsersVO getUsersVO() {
		return usersVO;
	}
	/**
	 * @param usersVO the usersVO to set
	 */
	public void setUsersVO(UsersVO usersVO) {
		this.usersVO = usersVO;
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

}
