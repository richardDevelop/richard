package com.evaluacionlinea.model;
// default package


/**
 * Usuario entity. @author MyEclipse Persistence Tools
 */

public class Users implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer usuId;
     private String usuario;
     private String password;
     private Boolean activo;

    // Constructors

    /** default constructor */
    public Users() {
    }

    public Users(Users users) {
    	this.usuId = users.getUsuId();
    	this.usuario = users.getUsuario();
    	this.password = users.getPassword();
    	this.activo = users.getActivo();
    }
	/** minimal constructor */
    public Users(String usuario, String contraseña, Boolean activo) {
        this.usuario = usuario;
        this.password = contraseña;
        this.activo = activo;
    }
    

   
    // Property accessors

    public Integer getUsuId() {
        return this.usuId;
    }
    
    public void setUsuId(Integer usuId) {
        this.usuId = usuId;
    }

    public String getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActivo() {
        return this.activo;
    }
    
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

}