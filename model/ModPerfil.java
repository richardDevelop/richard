package com.evaluacionlinea.model;




/**
 * ModPerfil entity. @author MyEclipse Persistence Tools
 */

public class ModPerfil implements java.io.Serializable {

	//SerialVersion
	private static final long serialVersionUID = 1L;
	
    // Fields    

     private ModPerfilId id;


    // Constructors

    /** default constructor */
    public ModPerfil() {
    }

    
    /** full constructor */
    public ModPerfil(ModPerfilId id) {
        this.id = id;
    }

   
    // Property accessors

    public ModPerfilId getId() {
        return this.id;
    }
    
    public void setId(ModPerfilId id) {
        this.id = id;
    }
}