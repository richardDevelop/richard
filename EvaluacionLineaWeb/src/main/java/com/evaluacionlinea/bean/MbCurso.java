package com.evaluacionlinea.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.event.RowEditEvent;

import com.evaluacionlinea.interfaces.ICuestionario;
import com.evaluacionlinea.model.Curso;
import com.evaluacionlinea.utils.Const;
import com.evaluacionlinea.vo.CursoVO;
import com.evaluacionlinea.vo.PersonaVO;
@ManagedBean(name = "dtCursoView")
@ViewScoped
public class MbCurso implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3815395499810144835L;
	
	private List<CursoVO> lstCurso;
	
	@ManagedProperty(value="#{cuestionarioBean}")
	private ICuestionario cuestionario;
	
	private List<SelectItem> lstEstatus;
	private String estatus;
	
	private List<SelectItem> lstArea;
	private String area;
	
	private String areaGral;
	
	private String areaTbl;
	
	private String nombre;
	
	private String estatusTbl;
	
	private String pageRedi;
	
	private int numPage = 0;
	
	private HttpServletRequest httpServletRequest;
	
	private PersonaVO per;
	
	
	
	/**
	 * @param cuestionario the cuestionario to set
	 */
	public void setCuestionario(ICuestionario cuestionario) {
		this.cuestionario = cuestionario;
	}

	public String getEstatusTbl() {
		return estatusTbl;
	}

	public void setEstatusTbl(String estatusTbl) {
		this.estatusTbl = estatusTbl;
	}

	public String getAreaTbl() {
		return areaTbl;
	}

	public void setAreaTbl(String areaTbl) {
		this.areaTbl = areaTbl;
	}

	
	
	public String getAreaGral() {
		return areaGral;
	}

	public void setAreaGral(String areaGral) {
		this.areaGral = areaGral;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
     /**
	 * @return the lstCurso
	 */
	public List<CursoVO> getLstCurso() {
		return lstCurso;
	}

	/**
	 * @param lstCurso the lstCurso to set
	 */
	public void setLstCurso(List<CursoVO> lstCurso) {
		this.lstCurso = lstCurso;
	}



	public List<SelectItem> getLstEstatus() {
		return lstEstatus;
	}

	public void setLstEstatus(List<SelectItem> lstEstatus) {
		this.lstEstatus = lstEstatus;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public List<SelectItem> getLstArea() {
		return lstArea;
	}

	public void setLstArea(List<SelectItem> lstArea) {
		this.lstArea = lstArea;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	
    public String getPageRedi() {
		return pageRedi;
	}

	public void setPageRedi(String pageRedi) {
		this.pageRedi = pageRedi;
	}

	
	
	/**
	 * @return the numPage
	 */
	public int getNumPage() {
		return numPage;
	}

	/**
	 * @param numPage the numPage to set
	 */
	public void setNumPage(int numPage) {
		this.numPage = numPage;
	}

	@PostConstruct
	public void getListCurso() {
		// lstCurso = curso.getLstCurso();
		 pageRedi = "../evaluacion/altaEvaluacion.xhtml";
			try{
				ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
				httpServletRequest = (HttpServletRequest) context.getRequest();
				per = (PersonaVO) httpServletRequest.getSession().getAttribute("sessionUsario");
				
			}catch(Exception e){
				e.getMessage();
			}
		 lstArea = Const.getLstAreaItem(per.getListaUsuArea());
		 getBuildLstEstatus();
		 
	}
	
	private void getBuildLstEstatus(){
		lstEstatus = new ArrayList<SelectItem>();
		SelectItem sel = new SelectItem();
		sel.setLabel("Activo");
		sel.setValue("V");
		SelectItem sele = new SelectItem();
		sele.setLabel("Inactivo");
		sele.setValue("F");
		lstEstatus.add(sel);
		lstEstatus.add(sele);
	}
	
	
	 public void onRowEdit(RowEditEvent event) {
		    Curso cursoUpd = (Curso)event.getObject();
		    cursoUpd.setArea(areaTbl != null ? areaTbl : getAreaId(cursoUpd.getArea()));
		    String est = estatusTbl != null ? estatusTbl : cursoUpd.getEstatus().equals("Activo") ? "V" : "F";
		    cursoUpd.setActivo(est.equals("V") ? true : false);
		    cuestionario.updCurso(cursoUpd);
		    lstCurso = null;
		    areaTbl = "";
		    estatusTbl = "";
		    FacesMessage msg = new FacesMessage("Actualizacion Exitosa", "El Curso con el Id : " +
		    ((Curso) event.getObject()).getCurId().toString() + "a sido actualizado con Exito");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	        
	    }
	     

	    
	    public void newCurso(ActionEvent event){
	    	Curso newCur = new Curso();
	    	newCur.setArea(area);
	    	newCur.setNombre(nombre);
	    	newCur.setActivo(estatus.equals("V") ? true : false);
	    	newCur.setUsuario(per.getUsuario());
	    	newCur.setFalta(new Timestamp(new Date().getTime()));
	    	newCur.setFmodifica(new Timestamp(new Date().getTime()));
	    	cuestionario.saveCurso(newCur);
	    	lstCurso = cuestionario.findCursoByArea(new Integer(area));
	    	refrescarCom();
	    	FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Guardado", "Exitoso");
            FacesContext.getCurrentInstance().addMessage(null, msg);
	    }
	    
	    private void refrescarCom() {
	    	nombre = "";
	    	area = "";
	    	estatus = "";
	    }
	    
	    public void findCursosByArea(){
	    	lstCurso = cuestionario.findCursoByArea(new Integer(areaGral));
	    	areaGral = "";
	    }
	    
	    private String getAreaId(String area){
	    	String areaId = "";
	    	for(SelectItem se : lstArea){
	    		if(se.getLabel().equals(area))
	    			areaId = se.getValue().toString();
	    	}
	    	return areaId;
	    }
	    
	    public void redirctUp() {
	    	if(numPage == 0){
	    		numPage = 1;
	    		pageRedi = "../evaluacion/cargaUsuarios.xhtml";
	    	}else if(numPage == 1) {
	    		numPage = numPage + 1;
	    		pageRedi = "../evaluacion/usuarioEvaluacion.xhtml";
	    	}
	    }
		

	    
	    
	    
	    
	
	
	

}
