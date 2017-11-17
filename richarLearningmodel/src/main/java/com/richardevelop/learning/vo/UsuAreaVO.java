/**
 * 
 */
package com.richardevelop.learning.vo;

import java.io.Serializable;

/**
 * @author Othoniel
 *
 */
public class UsuAreaVO implements Serializable{

	//SerialVersion
	private static final long serialVersionUID = 1L;
	private UsersVO usersVO;
	private AreaVO areaVO;
	
	
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


	/**
	 * 
	 */
	public UsuAreaVO() {
		// TODO Auto-generated constructor stub
	}


    /** full constructor */
    public UsuAreaVO(UsuAreaVO idVO) {
       
    }

}
