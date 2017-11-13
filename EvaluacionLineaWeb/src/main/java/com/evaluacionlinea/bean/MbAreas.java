package com.evaluacionlinea.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import com.evaluacionlinea.interfaces.IArea;
import com.evaluacionlinea.model.Area;
import com.evaluacionlinea.model.Persona;
import com.evaluacionlinea.vo.PersonaVO;

@ManagedBean(name="MbAreas")
@ViewScoped
public class MbAreas implements Serializable{

	private static final long serialVersionUID = -605270684052357027L;
	
	private PersonaVO usuarioSession;
	private List<SelectItem> lstAreas;
	private Area area;
	private Integer perfId;
	
	private final HttpServletRequest httpServletRequest;
	private final FacesContext facesContext;
	
	@ManagedProperty(value="#{areaBean}")
	private IArea areaBean;
	
	
	
	/**
	 * @param areaBean the areaBean to set
	 */
	public void setAreaBean(IArea areaBean) {
		this.areaBean = areaBean;
	}
	public MbAreas() {
		area = new Area();
		
		facesContext = FacesContext.getCurrentInstance();		
		httpServletRequest = (HttpServletRequest)facesContext.getExternalContext().getRequest();
		if (httpServletRequest.getSession().getAttribute("sessionUsario") != null) {
			usuarioSession = (PersonaVO)httpServletRequest.getSession().getAttribute("sessionUsario");
		} 
		
		if (null != httpServletRequest.getSession().getAttribute("perfId")) {
			perfId = (Integer) httpServletRequest.getSession().getAttribute("perfId");
		}
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
	public List<SelectItem> getListaAreas() {
		if (null == lstAreas) {
			this.lstAreas = new ArrayList<SelectItem>();
		}
		
		if (lstAreas.size() == 0) {
			List<Area> lsta = areaBean.getByPerfil(usuarioSession.getUsersVO().getUsuId(), perfId);
			lstAreas.clear();
			if (null != lsta) {
				for (Area area : lsta) {
					SelectItem areaItem = new SelectItem(area.getAreId(), area.getNombre());
					lstAreas.add(areaItem);
				}
			}
		}		
		return lstAreas;
	} 
}
