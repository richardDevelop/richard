package com.evaluacionlinea.model.pojo;

import com.evaluacionlinea.model.Evaluacion;
import com.evaluacionlinea.model.Users;
// default package



/**
 * UsuEvaluacionId entity. @author MyEclipse Persistence Tools
 */

public class UsuEvaluacionId  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Evaluacion evaluacion;
    private Users users;


    // Constructors

    /** default constructor */
    public UsuEvaluacionId() {
    }

    
   /**
	 * @return the evaluacion
	 */
	public Evaluacion getEvaluacion() {
		return evaluacion;
	}


	/**
	 * @param evaluacion the evaluacion to set
	 */
	public void setEvaluacion(Evaluacion evaluacion) {
		this.evaluacion = evaluacion;
	}


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


public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof UsuEvaluacionId) ) return false;
		 UsuEvaluacionId castOther = ( UsuEvaluacionId ) other; 
         
		 return ( (this.getEvaluacion()==castOther.getEvaluacion()) || ( this.getEvaluacion()!=null && castOther.getEvaluacion()!=null && this.getEvaluacion().equals(castOther.getEvaluacion()) ) )
 && ( (this.getUsers()==castOther.getUsers()) || ( this.getUsers()!=null && castOther.getUsers()!=null && this.getUsers().equals(castOther.getUsers()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getEvaluacion() == null ? 0 : this.getEvaluacion().hashCode() );
         result = 37 * result + ( getUsers() == null ? 0 : this.getUsers().hashCode() );
         return result;
   }   





}