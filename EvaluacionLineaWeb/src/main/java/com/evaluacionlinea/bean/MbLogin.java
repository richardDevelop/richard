package com.evaluacionlinea.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.evaluacionlinea.interfaces.IPersona;
import com.evaluacionlinea.vo.PersonaVO;

@ManagedBean(name="MbLogin")
@RequestScoped
public class MbLogin {

	private String usuario;
	private String passowrd;	
	private final HttpServletRequest httpServletRequest;
	private final FacesContext facesContext;
	private FacesMessage facesMessage;
	
	@ManagedProperty(value="#{personaBean}")
	IPersona personaBean;
	
	public MbLogin() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		
		facesContext = FacesContext.getCurrentInstance();		
		httpServletRequest = (HttpServletRequest)facesContext.getExternalContext().getRequest();
		
	}
	
	public String login() {		

		PersonaVO personaVO= personaBean.getPersonaByLogin(usuario, passowrd);
		
		if (null != personaVO && null != personaVO.getPerId()) {
			String redirect;
			if (personaVO.getActivo()) {
				httpServletRequest.getSession().setAttribute("sessionUsario", personaVO);
				facesMessage=new FacesMessage(FacesMessage.SEVERITY_INFO, "Acceso Correcto", null);
				redirect = "usuAlta";
			} else {
				facesMessage=new FacesMessage(FacesMessage.SEVERITY_INFO, "El usuario esta inactivo", null);
				redirect = "Login";
			}
			facesContext.addMessage(null, facesMessage);
			return redirect;
		} else {
			facesMessage=new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario o contraseña invalidos", null);
			facesContext.addMessage(null, facesMessage);
			return "Login";
		}
		
	}
	
	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}
	/**
	 * @return the passowrd
	 */
	public String getPassowrd() {
		return passowrd;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	/**
	 * @param passowrd the passowrd to set
	 */
	public void setPassowrd(String passowrd) {
		this.passowrd = passowrd;
	}

	public void setPersonaBean(IPersona personaBean) {
		this.personaBean = personaBean;
	}
	
}
