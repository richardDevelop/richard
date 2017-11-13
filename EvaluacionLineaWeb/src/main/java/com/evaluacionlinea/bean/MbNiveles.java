package com.evaluacionlinea.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import com.evaluacionlinea.interfaces.IArea;
import com.evaluacionlinea.model.Nivel;

@ManagedBean(name="MbNiveles")
@ViewScoped
public class MbNiveles implements Serializable{

	private static final long serialVersionUID = -605270684052357027L;
	
	private List<SelectItem> lstNiveles;
	private Nivel nivel;
	
	@ManagedProperty(value="#{areaBean}")
	private IArea areaBean;
	
	
	
	/**
	 * @param areaBean the areaBean to set
	 */
	public void setAreaBean(IArea areaBean) {
		this.areaBean = areaBean;
	}
	
	public MbNiveles() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the lstNiveles
	 */
	public List<SelectItem> getLstNiveles() {
		return lstNiveles;
	}

	/**
	 * @param lstNiveles the lstNiveles to set
	 */
	public void setLstNiveles(List<SelectItem> lstNiveles) {
		this.lstNiveles = lstNiveles;
	}

	/**
	 * @return the nivel
	 */
	public Nivel getNivel() {
		return nivel;
	}

	/**
	 * @param nivel the nivel to set
	 */
	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}

	public List<SelectItem> getlistaNiveles() {
		this.lstNiveles = new ArrayList<SelectItem>();
		List<Nivel> lst = areaBean.getAllNiveles();
		lstNiveles.clear();
		
		for (Nivel nivel : lst) {
			SelectItem nivelItem = new SelectItem(nivel.getNivId(), nivel.getNivel());
			lstNiveles.add(nivelItem);
		}
		return lstNiveles;
		
	}
}
