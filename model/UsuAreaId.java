package com.evaluacionlinea.model;

/**
 * UsuAreaId entity. @author MyEclipse Persistence Tools
 */

public class UsuAreaId  implements java.io.Serializable {
    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Users users;
    private Area area;


    // Constructors

    /** default constructor */
    public UsuAreaId() {
    }

    
    /** full constructor */
    public UsuAreaId(Users users, Area area) {
        this.users = users;
        this.area = area;
    }

   
    // Property accessors

    public Users getUsers() {
        return this.users;
    }
    
    public void setUsers(Users users) {
        this.users = users;
    }

    public Area getArea() {
        return this.area;
    }
    
    public void setArea(Area area) {
        this.area = area;
    }
}