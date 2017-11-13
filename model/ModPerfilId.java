package com.evaluacionlinea.model;




/**
 * ModPerfilId entity. @author MyEclipse Persistence Tools
 */

public class ModPerfilId  implements java.io.Serializable {

	//SerialVersion
	private static final long serialVersionUID = 1L;
	
    // Fields    

     private Modulo modulo;
     private Perfil perfil;


    // Constructors

    /** default constructor */
    public ModPerfilId() {
    }

    
    /** full constructor */
    public ModPerfilId(Modulo modulo, Perfil perfil) {
        this.modulo = modulo;
        this.perfil = perfil;
    }

   
    // Property accessors

    public Modulo getModulo() {
        return this.modulo;
    }
    
    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public Perfil getPerfil() {
        return this.perfil;
    }
    
    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ModPerfilId) ) return false;
		 ModPerfilId castOther = ( ModPerfilId ) other; 
         
		 return ( (this.getModulo()==castOther.getModulo()) || ( this.getModulo()!=null && castOther.getModulo()!=null && this.getModulo().equals(castOther.getModulo()) ) )
 && ( (this.getPerfil()==castOther.getPerfil()) || ( this.getPerfil()!=null && castOther.getPerfil()!=null && this.getPerfil().equals(castOther.getPerfil()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getModulo() == null ? 0 : this.getModulo().hashCode() );
         result = 37 * result + ( getPerfil() == null ? 0 : this.getPerfil().hashCode() );
         return result;
   }   





}