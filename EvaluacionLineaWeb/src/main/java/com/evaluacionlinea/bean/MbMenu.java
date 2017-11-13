package com.evaluacionlinea.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import com.evaluacionlinea.utils.Const;
import com.evaluacionlinea.vo.PersonaVO;
import com.evaluacionlinea.vo.UsuPerfilVO;

@ManagedBean(name="MbMenu")
@SessionScoped
public class MbMenu implements Serializable{
	
	private static final long serialVersionUID = -605270684052357027L;
	
	private MenuModel menu;
	private PersonaVO usuarioSession;
	private String mostrarMenu;
	
	private final HttpServletRequest httpServletRequest;
	private final FacesContext facesContext;
	
	
	public MbMenu() {
		
		facesContext = FacesContext.getCurrentInstance();		
		httpServletRequest = (HttpServletRequest)facesContext.getExternalContext().getRequest();
		if (httpServletRequest.getSession().getAttribute("sessionUsario") != null) {
			usuarioSession = (PersonaVO)httpServletRequest.getSession().getAttribute("sessionUsario");
		} 
		
		if (null != usuarioSession) {
			
			NavigationHandler navigHandler = facesContext.getApplication().getNavigationHandler();
			List<UsuPerfilVO> lstPerfilVO = usuarioSession.getListaUsuPerfilVO();

			DefaultMenuItem menuItem;
			DefaultSubMenu subMenu;
			menu = new DefaultMenuModel(); 
			mostrarMenu = "none";
			
			
			if (null != lstPerfilVO && lstPerfilVO.size() > 0) {
				
				for (UsuPerfilVO usuPerfilVO : lstPerfilVO) {
					
					//Validamos si el usuario tiene perfil de Monitor
					if (usuPerfilVO.getIdVO().getPerfilVO().getPerfId().equals(Const.Perfil_MONITOR)) {
						httpServletRequest.getSession().setAttribute("perfId", Const.Perfil_MONITOR);
						navigHandler.handleNavigation(facesContext, null, "/pagesAdmin/monitoreoEva.xhtml?faces-redirect=true");
						break;
					}
					
					//validamos si el perfil es EVALUADO
					if (usuPerfilVO.getIdVO().getPerfilVO().getPerfId().equals(Const.Perfil_EVALUADO)) {
						httpServletRequest.getSession().setAttribute("perfId", Const.Perfil_EVALUADO);
						navigHandler.handleNavigation(facesContext, null, "/Instrucciones.xhtml?faces-redirect=true");
						break;
					}
					
					//validamos si el perfil es ADMINISTRADOR
					if (usuPerfilVO.getIdVO().getPerfilVO().getPerfId().equals(Const.Perfil_ADMINTRADOR)) {
						subMenu = new DefaultSubMenu("Evaluaciones","ui-icon-pencil");						
						
						menuItem = new DefaultMenuItem("Curso");
						menuItem.setCommand("#{MbMenu.redirectCurso}");						
						subMenu.addElement(menuItem);
						
						menuItem = new DefaultMenuItem("Cuestionario");
						menuItem.setCommand("#{MbMenu.redirectCuestionario}");
						subMenu.addElement(menuItem);
						
						DefaultSubMenu subSubMenu = new DefaultSubMenu("Config. Evaluacion");
						
						menuItem = new DefaultMenuItem("Nueva");
						menuItem.setCommand("#{MbMenu.redirectNuevaEvaluacion}");
						subSubMenu.addElement(menuItem);
						
						menuItem = new DefaultMenuItem("Consulta");
						menuItem.setCommand("#{MbMenu.redirectEvaluaciones}");
						subSubMenu.addElement(menuItem);						
						subMenu.addElement(subSubMenu);
						
						menu.addElement(subMenu);
						
						menuItem = new DefaultMenuItem("Monitor","ui-icon-pencil");
						menuItem.setCommand("#{MbMenu.redirectMonitor}");
						menu.addElement(menuItem);
						
						//Enviamos como parametro el perfil
						httpServletRequest.getSession().setAttribute("perfId", Const.Perfil_ADMINTRADOR);
						
						mostrarMenu = "inline";
						break;
					}
					
					//validamos si el perfil es ROOT
					if (usuPerfilVO.getIdVO().getPerfilVO().getPerfId().equals(Const.Perfil_ROOT)) {
						
						menuItem = new DefaultMenuItem("Usuarios","ui-icon-pencil");
						menuItem.setCommand("#{MbMenu.redirectUsuarios}");
						menu.addElement(menuItem);
						
						menuItem = new DefaultMenuItem("Monitor","ui-icon-pencil");
						menuItem.setCommand("#{MbMenu.redirectMonitor}");
						menu.addElement(menuItem);
						
						subMenu = new DefaultSubMenu("Eva. Especiales","ui-icon-pencil");	
						menuItem = new DefaultMenuItem("Expositor");
						menuItem.setCommand("#{MbMenu.redirectExpositor}");
						subMenu.addElement(menuItem);	
						
						menuItem = new DefaultMenuItem("Encuestas");
						menuItem.setCommand("#{MbMenu.redirectEvaEspecial}");
						subMenu.addElement(menuItem);	
						menu.addElement(subMenu);
						
						subMenu = new DefaultSubMenu("Evaluaciones","ui-icon-pencil");						
						
						menuItem = new DefaultMenuItem("Curso");
						menuItem.setCommand("#{MbMenu.redirectCurso}");						
						subMenu.addElement(menuItem);
						
						menuItem = new DefaultMenuItem("Cuestionario");
						menuItem.setCommand("#{MbMenu.redirectCuestionario}");
						subMenu.addElement(menuItem);
						
						DefaultSubMenu subSubMenu = new DefaultSubMenu("Config. Evaluacion");
						
						menuItem = new DefaultMenuItem("Nueva");
						menuItem.setCommand("#{MbMenu.redirectNuevaEvaluacion}");
						subSubMenu.addElement(menuItem);
						
						
						menuItem = new DefaultMenuItem("Consulta");
						menuItem.setCommand("#{MbMenu.redirectEvaluaciones}");
						subSubMenu.addElement(menuItem);
												
						
						subMenu.addElement(subSubMenu);
						
						menu.addElement(subMenu);
						
						//Enviamos como parametro el perfil
						httpServletRequest.getSession().setAttribute("perfId", Const.Perfil_ROOT);
						
						mostrarMenu = "inline";
						break;
					}					
				}
				
			} else {
				httpServletRequest.getSession().setAttribute("perfId", Const.Perfil_EVALUADO);
				navigHandler.handleNavigation(facesContext, null, "/Instrucciones.xhtml?faces-redirect=true");
			}
		}
	}
	
	/**
	 * @return the usuarioSession
	 */
	public PersonaVO getUsuarioSession() {
		return usuarioSession;
	}

	/**
	 * @param usuarioSession the usuarioSession to set
	 */
	public void setUsuarioSession(PersonaVO usuarioSession) {
		this.usuarioSession = usuarioSession;
	}

	public String redirectUsuarios(){
        return "/pagesAdmin/usuarios/usuarios.xhtml?faces-redirect=true";
    }
	
	
	public String redirectCurso(){
        return "/pagesAdmin/curso/cursoLista.xhtml?faces-redirect=true";
	}
	
	public String redirectCuestionario(){
        return "/pagesAdmin/cuestionario/cuestionario.xhtml?faces-redirect=true";
	}
	
	public String redirectNuevaEvaluacion(){
        return "/pagesAdmin/curso/curso.xhtml?faces-redirect=true";
	}

	public String redirectEvaluaciones(){
        return "/pagesAdmin/evaluacion/evaluacion.xhtml?faces-redirect=true";
	}
	
	public String redirectMonitor(){
        return "/pagesAdmin/monitoreoEva.xhtml?faces-redirect=true";
	}
	
	public String redirectExpositor(){
		return "/pagesAdmin/cuestionario/expositor.xhtml?faces-redirect=true";
	}
	
	public String redirectEvaEspecial(){
		return "/pagesAdmin/cuestionario/evaluacionEspecial.xhtml?faces-redirect=true";
	}
	
	/**
	 * @return the menu
	 */
	public MenuModel getMenu() {
		return menu;
	}

	

	/**
	 * @return the mostrarMenu
	 */
	public String getMostrarMenu() {
		return mostrarMenu;
	}

	/**
	 * @param mostrarMenu the mostrarMenu to set
	 */
	public void setMostrarMenu(String mostrarMenu) {
		this.mostrarMenu = mostrarMenu;
	}	
}
