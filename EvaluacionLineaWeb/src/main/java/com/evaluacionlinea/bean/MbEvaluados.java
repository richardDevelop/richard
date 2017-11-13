package com.evaluacionlinea.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import com.evaluacionlinea.interfaces.ICuestionario;
import com.evaluacionlinea.interfaces.IPersona;
import com.evaluacionlinea.model.Persona;
import com.evaluacionlinea.utils.Const;
import com.evaluacionlinea.vo.CuestionarioVO;
import com.evaluacionlinea.vo.EvaluacionVO;
import com.evaluacionlinea.vo.EvaluadosVO;
import com.evaluacionlinea.vo.PersonaVO;

 @ManagedBean(name = "dtEvaluadosListView") 
 @SessionScoped
public class MbEvaluados implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 872057075246520021L;
	
	@ManagedProperty(value="#{cuestionarioBean}")
	private ICuestionario cuestionario;

	private List<EvaluadosVO> lstEvaluados;
	
	private List<SelectItem> lstEstatus;
	private String estatus;
	
	private List<SelectItem> lstArea;
	private String area;
	
	private EvaluacionVO evaluacion = new EvaluacionVO();
	
	private Integer areaId;
	
    private List<CuestionarioVO> lstCues;
	
	private List<SelectItem> lstCuesItem;
	
	private Integer cueId;
	
	private String pageRedi;
	
	
	
	
	private List<PersonaVO> lstPersona;
	
	private List<EvaluadosVO> lstPersonaSave;
	
	private Persona persona;
	
	private HttpServletRequest httpServletRequest;
	
	private PersonaVO usuarioSession;
	
	private boolean showPdf;
	
	private boolean showEncuesta;
	
	private List<SelectItem> lstCuesExpo;
	private Integer cuesExpId;
	
	private List<SelectItem> lstCuesInsta;
	private Integer cuesInstaId;
	
	@ManagedProperty(value="#{personaBean}")
	private IPersona personaBean;
	
	

	
	/**
	 * @param personaBean the personaBean to set
	 */
	public void setPersonaBean(IPersona personaBean) {
		this.personaBean = personaBean;
	}

	/**
	 * @return the lstPersona
	 */
	public List<PersonaVO> getLstPersona() {
		return lstPersona;
	}

	/**
	 * @param lstPersona the lstPersona to set
	 */
	public void setLstPersona(List<PersonaVO> lstPersona) {
		this.lstPersona = lstPersona;
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
   
	
	
	public List<CuestionarioVO> getLstCues() {
		return lstCues;
	}

	public void setLstCues(List<CuestionarioVO> lstCues) {
		this.lstCues = lstCues;
	}

	public List<SelectItem> getLstCuesItem() {
		return lstCuesItem;
	}

	public void setLstCuesItem(List<SelectItem> lstCuesItem) {
		this.lstCuesItem = lstCuesItem;
	}

	public Integer getCueId() {
		return cueId;
	}

	public void setCueId(Integer cueId) {
		this.cueId = cueId;
	}

	
	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public EvaluacionVO getEvaluacion() {
		return evaluacion;
	}

	public void setEvaluacion(EvaluacionVO evaluacion) {
		this.evaluacion = evaluacion;
	}


	/**
	 * @return the lstEvaluados
	 */
	public List<EvaluadosVO> getLstEvaluados() {
		return lstEvaluados;
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

	public void setLstEvaluados(List<EvaluadosVO> lstEvaluados) {
		this.lstEvaluados = lstEvaluados;
	}
	
	
	/**
	 * @return the lstCuesExpo
	 */
	public List<SelectItem> getLstCuesExpo() {
		return lstCuesExpo;
	}

	/**
	 * @param lstCuesExpo the lstCuesExpo to set
	 */
	public void setLstCuesExpo(List<SelectItem> lstCuesExpo) {
		this.lstCuesExpo = lstCuesExpo;
	}

	/**
	 * @return the lstCuesInsta
	 */
	public List<SelectItem> getLstCuesInsta() {
		return lstCuesInsta;
	}

	/**
	 * @param lstCuesInsta the lstCuesInsta to set
	 */
	public void setLstCuesInsta(List<SelectItem> lstCuesInsta) {
		this.lstCuesInsta = lstCuesInsta;
	}

	/**
	 * @return the cuestionario
	 */
	public ICuestionario getCuestionario() {
		return cuestionario;
	}

	/**
	 * @param cuestionario the cuestionario to set
	 */
	public void setCuestionario(ICuestionario cuestionario) {
		this.cuestionario = cuestionario;
	}

	/**
	 * @return the pageRedi
	 */
	public String getPageRedi() {
		return pageRedi;
	}

	/**
	 * @param pageRedi the pageRedi to set
	 */
	public void setPageRedi(String pageRedi) {
		this.pageRedi = pageRedi;
	}
	
	
	

	public boolean isShowPdf() {
		return showPdf;
	}

	public void setShowPdf(boolean showPdf) {
		this.showPdf = showPdf;
	}

	public boolean isShowEncuesta() {
		return showEncuesta;
	}

	public void setShowEncuesta(boolean showEncuesta) {
		this.showEncuesta = showEncuesta;
	}

	/**
	 * @return the lstPersonaSave
	 */
	public List<EvaluadosVO> getLstPersonaSave() {
		return lstPersonaSave;
	}

	/**
	 * @param lstPersonaSave the lstPersonaSave to set
	 */
	public void setLstPersonaSave(List<EvaluadosVO> lstPersonaSave) {
		this.lstPersonaSave = lstPersonaSave;
	}
	
	


	/**
	 * @return the cuesExpId
	 */
	public Integer getCuesExpId() {
		return cuesExpId;
	}

	/**
	 * @param cuesExpId the cuesExpId to set
	 */
	public void setCuesExpId(Integer cuesExpId) {
		this.cuesExpId = cuesExpId;
	}

	/**
	 * @return the cuesInstaId
	 */
	public Integer getCuesInstaId() {
		return cuesInstaId;
	}

	/**
	 * @param cuesInstaId the cuesInstaId to set
	 */
	public void setCuesInstaId(Integer cuesInstaId) {
		this.cuesInstaId = cuesInstaId;
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

	@PostConstruct
	public void init(){
		pageRedi = "../evaluacion/altaEvaluacion.xhtml";
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		httpServletRequest = (HttpServletRequest) context.getRequest();
		usuarioSession =(PersonaVO)httpServletRequest.getSession().getAttribute("sessionUsario");
		cleanValues();
		lstCuesExpo = buildSelectedCus(cuestionario.getCuestionarioByTipo(Const.CUESTIONARIO_EXPOSITOR));
		lstCuesInsta = buildSelectedCus(cuestionario.getCuestionarioByTipo(Const.CUESTIONARIO_INSTALACION));
	}
	
	public void saveEvaluacion() {
		EvaluacionVO ev = evaluacion;
		CuestionarioVO cuest = new CuestionarioVO();
		cuest.setCueId(cueId);
		evaluacion.setCuestionarioVO(cuest);
		evaluacion.setUsuario(usuarioSession.getUsers().getUsuario().trim());
		evaluacion.setActivo(estatus.equals("V") ? true : false);
		//evaluacion.setShowEncuesta(showEncuesta ? 1 : 0);
		evaluacion.setEvaExpId(cuesExpId);
		evaluacion.setEvainstaId(cuesInstaId);
		evaluacion.setEvaId(cuestionario.saveEvaluacion(ev));
		redirctUp();
		
	}
	
	public void handleChange(){ 
		lstCues =cuestionario.getLstCues(areaId);
		lstCuesItem = new ArrayList<SelectItem>();
		for(CuestionarioVO cues :lstCues){
			SelectItem item = new SelectItem(cues.getCueId(), cues.getNombre());
			lstCuesItem.add(item);
		}
	}
	
     public void redirctUp() {
    	try{
    			 
    			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
    			httpServletRequest = (HttpServletRequest) context.getRequest();
    			httpServletRequest.getSession().setAttribute("evaluacion", evaluacion);
    			evaluacion = new EvaluacionVO();
    			httpServletRequest.getSession().setAttribute("areId", areaId);
        		context.redirect(context.getRequestContextPath() + "/pagesAdmin/evaluacion/usuarioEvaluacion.xhtml");
        	}catch(Exception e){
    			e.getMessage();
    	}
    		
    }
    
    
    private void cleanValues(){
    	evaluacion = new EvaluacionVO();
    	 getBuildLstEstatus();
		 lstArea = Const.getLstAreaItem(usuarioSession.getListaUsuArea());
    }
    
    private List<SelectItem> buildSelectedCus(List<CuestionarioVO> lstCues) {
    	List<SelectItem> lstSelecItem = new ArrayList<SelectItem>();
    	for(CuestionarioVO cuesti : lstCues){
    		lstSelecItem.add(new SelectItem(cuesti.getCueId(), cuesti.getNombre()));
    	}
		return lstSelecItem;
    	
    }
    
}
