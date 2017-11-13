package com.evaluacionlinea.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import com.evaluacionlinea.interfaces.IPersona;
import com.evaluacionlinea.model.Perfil;

@ManagedBean(name="MbPerfil")
@ViewScoped
public class MbPerfil implements Serializable {

	private static final long serialVersionUID = -605270684052357027L;
	
	private List<SelectItem> lstPerfil;
	
	@ManagedProperty(value="#{personaBean}")
	private IPersona personaBean;
	
	
	/**
	 * @param personaBean the personaBean to set
	 */
	public void setPersonaBean(IPersona personaBean) {
		this.personaBean = personaBean;
	}

	public MbPerfil() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the lstPerfil
	 */
	public List<SelectItem> getLstPerfil() {
		return lstPerfil;
	}

	/**
	 * @param lstPerfil the lstPerfil to set
	 */
	public void setLstPerfil(List<SelectItem> lstPerfil) {
		this.lstPerfil = lstPerfil;
	}

	public List<SelectItem> getListaPerfiles() {
		if (null == lstPerfil) {
			this.lstPerfil = new ArrayList<SelectItem>();
		}
		
		if (lstPerfil.size() == 0) {
			List<Perfil> lsta = personaBean.getPerfiles();
			lstPerfil.clear();
			
			if (null != lsta) {
				for (Perfil perfil : lsta) {
					SelectItem perfilItem = new SelectItem(perfil.getPerfId(), perfil.getNombre());
					lstPerfil.add(perfilItem);
				}
			}
		}
		return lstPerfil;
	} 
}
