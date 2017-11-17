/**
 * 
 */
package com.richardevelop.learning.vo;

import java.io.Serializable;

/**
 * @author Othoniel
 *
 */
public class UsuPerfilIdVO implements Serializable{

	private static final long serialVersionUID = 1L;
	private PerfilVO perfilVO;
    private UsersVO usersVO;
	/**
	 * 
	 */
	public UsuPerfilIdVO() {
		// TODO Auto-generated constructor stub
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

}
