package com.richarddevelop.learning.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * UsuArea entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "usuarea")
public class UsuArea  implements java.io.Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3012539042490282475L;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="usu_id")
	@Id
	private Users users;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="are_id")
	@Id
    private Area area;

	/**
	 * @return the users
	 */
	public Users getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(Users users) {
		this.users = users;
	}

	/**
	 * @return the area
	 */
	public Area getArea() {
		return area;
	}

	/**
	 * @param area the area to set
	 */
	public void setArea(Area area) {
		this.area = area;
	}
	
	
}