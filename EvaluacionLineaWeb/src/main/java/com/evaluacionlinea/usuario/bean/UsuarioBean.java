package com.evaluacionlinea.usuario.bean;

import java.io.Serializable;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.evaluacionlinea.interfaces.LoginInterface;
@ManagedBean
@SessionScoped
public class UsuarioBean implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String user;
	
	private String passwoord;
	@EJB
	private LoginInterface login;
	
	private String respuestaLog;

	/**
	 * @return the respuestaLog
	 */
	public String getRespuestaLog() {
		return respuestaLog;
	}

	/**
	 * @param respuestaLog the respuestaLog to set
	 */
	public void setRespuestaLog(String respuestaLog) {
		this.respuestaLog = respuestaLog;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the passwoord
	 */
	public String getPasswoord() {
		return passwoord;
	}

	/**
	 * @param passwoord the passwoord to set
	 */
	public void setPasswoord(String passwoord) {
		this.passwoord = passwoord;
	}
	
	
    public String doGuardar(){
        setRespuestaLog(login.getLoginByUser(user, passwoord));
        
        return getRespuestaLog();
    }
	

}
