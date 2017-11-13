package com.evaluacionlinea.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.util.ArrayUtils;

import com.evaluacionlinea.model.Persona;
import com.evaluacionlinea.utils.Const;
import com.evaluacionlinea.vo.PersonaVO;

@ManagedBean(name="MbSession")
@RequestScoped
public class MbSession implements Serializable{
	
	private static final long serialVersionUID = -605270684052357027L;
	
	private PersonaVO personaVO;
	private Persona persona;
	private final HttpServletRequest httpServletRequest;
	private final FacesContext facesContext;
	private FacesMessage facesMessage;
	
	public MbSession() {
		facesContext = FacesContext.getCurrentInstance();		
		httpServletRequest = (HttpServletRequest)facesContext.getExternalContext().getRequest();
		if (httpServletRequest.getSession().getAttribute("sessionUsario") != null) {
			personaVO = (PersonaVO)httpServletRequest.getSession().getAttribute("sessionUsario");
			//validaPerfil();
		} else {			
			NavigationHandler navigHandler = facesContext.getApplication().getNavigationHandler();
			facesMessage=new FacesMessage(FacesMessage.SEVERITY_ERROR, "El usuario no se ha logueado", null);
			facesContext.addMessage(null, facesMessage);
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
			navigHandler.handleNavigation(facesContext, null, "/Login.xhtml?faces-redirect=true");
		}
	}
	
	/**
	 * @return the personaVO
	 */
	public PersonaVO getPersonaVO() {
		return personaVO;
	}

	/**
	 * @param personaVO the personaVO to set
	 */
	public void setPersonaVO(PersonaVO personaVO) {
		this.personaVO = personaVO;
	}

	public void cerrarSession() {
//		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
//		return "/Login.xhtml?faces-redirect=true";
		try {
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			context.redirect(context.getRequestContextPath() + "/Login.xhtml");
		} catch (Exception e) {
			// TODO: handle exception
		}		
	}
	
	

	private void validaPerfil(){
		
		if (null != httpServletRequest.getSession().getAttribute("perfId")) {
			
			try {
				Integer perfId = (Integer) httpServletRequest.getSession().getAttribute("perfId");
				String[] arrUrl = httpServletRequest.getRequestURI().split("/");
				String[] permisos;
				
				Boolean tienePermiso = true;
				
				if (arrUrl.length > 0) {
					String url = arrUrl[arrUrl.length - 1].toString();
					
					if (perfId.equals(Const.Perfil_ROOT)) {
						permisos  = new String []{"Login.xhtml","usuarios.xhtml","cursoLista.xhtml","cuestionario.xhtml","curso.xhtml","evaluacion.xhtml"};
						
						if (!ArrayUtils.contains(permisos, url)) {
							tienePermiso = false;
						}
					}
					
					if (perfId.equals(Const.Perfil_ADMINTRADOR)) {
						permisos  = new String []{"Login.xhtml","cursoLista.xhtml","cuestionario.xhtml","curso.xhtml","evaluacion.xhtml"};
						
						if (!ArrayUtils.contains(permisos, url)) {
							tienePermiso = false;
						}
					}
					
					if (perfId.equals(Const.Perfil_EVALUADO)) {
						permisos  = new String []{"Login.xhtml","Instrucciones.xhtml","Exa.xhtml","Resultado.xhtml"};
						
						if (!ArrayUtils.contains(permisos, url)) {
							tienePermiso = false;
						}
					}
					
					if (!tienePermiso) {
						//httpServletRequest.getSession().setAttribute("perfId", Const.Perfil_EVALUADO);
						//httpServletRequest.getSession().removeAttribute("perfId");
						ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
						context.redirect(context.getRequestContextPath() + "/Login.xhtml");
					}
				}
				
			} catch (Exception e) {
				e.getMessage();
			}
			
		}
	}

	/**
	 * @return the persona
	 */
	public Persona getPersona() {
		return persona;
	}

	/**
	 * @param persona the persona to set
	 */
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
}
