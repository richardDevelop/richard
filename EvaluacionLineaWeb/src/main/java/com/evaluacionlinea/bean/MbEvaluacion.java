package com.evaluacionlinea.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.event.FileUploadEvent;

import com.evaluacionlinea.interfaces.ICuestionario;
import com.evaluacionlinea.interfaces.IPersona;
import com.evaluacionlinea.model.Evaluacion;
import com.evaluacionlinea.vo.EvaluacionVO;
import com.evaluacionlinea.vo.EvaluadosVO;
import com.evaluacionlinea.vo.PersonaVO;

@ManagedBean(name = "mnEvaluacion")
@ViewScoped
public class MbEvaluacion implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	@ManagedProperty(value="#{cuestionarioBean}")
	private ICuestionario cuestionario;

	@ManagedProperty(value="#{personaBean}")
	private IPersona personaBean;

	private FileUploadEvent fileUp;

	private HttpServletRequest httpServletRequest;

	private PersonaVO usuarioSession;

	private Evaluacion evaluacionAdd;

	private List<SelectItem> lstEstatus;
	private String estatus;

	private List<SelectItem> lstArea;
	private String area;

	private List<EvaluacionVO> lstEvaluacion;

	private EvaluacionVO eva;

	private List<EvaluadosVO> lstPersonaSave;
	
	

	/**
	 * @param cuestionario the cuestionario to set
	 */
	public void setCuestionario(ICuestionario cuestionario) {
		this.cuestionario = cuestionario;
	}

	/**
	 * @param personaBean the personaBean to set
	 */
	public void setPersonaBean(IPersona personaBean) {
		this.personaBean = personaBean;
	}

	/**
	 * @return the area
	 */
	public String getArea() {
		return area;
	}

	/**
	 * @param area
	 *            the area to set
	 */
	public void setArea(String area) {
		this.area = area;
	}

	/**
	 * @return the eva
	 */
	public EvaluacionVO getEva() {
		return eva;
	}

	/**
	 * @param eva
	 *            the eva to set
	 */
	public void setEva(EvaluacionVO eva) {
		this.eva = eva;
	}

	/**
	 * @return the fileUp
	 */
	public FileUploadEvent getFileUp() {
		return fileUp;
	}

	/**
	 * @param fileUp
	 *            the fileUp to set
	 */
	public void setFileUp(FileUploadEvent fileUp) {
		this.fileUp = fileUp;
	}

	/**
	 * @return the evaluacionAdd
	 */
	public Evaluacion getEvaluacionAdd() {
		return evaluacionAdd;
	}

	/**
	 * @param evaluacionAdd
	 *            the evaluacionAdd to set
	 */
	public void setEvaluacionAdd(Evaluacion evaluacionAdd) {
		this.evaluacionAdd = evaluacionAdd;
	}

	/**
	 * @return the lstEstatus
	 */
	public List<SelectItem> getLstEstatus() {
		return lstEstatus;
	}

	/**
	 * @param lstEstatus
	 *            the lstEstatus to set
	 */
	public void setLstEstatus(List<SelectItem> lstEstatus) {
		this.lstEstatus = lstEstatus;
	}

	/**
	 * @return the estatus
	 */
	public String getEstatus() {
		return estatus;
	}

	/**
	 * @param estatus
	 *            the estatus to set
	 */
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	/**
	 * @return the lstArea
	 */
	public List<SelectItem> getLstArea() {
		return lstArea;
	}

	/**
	 * @param lstArea
	 *            the lstArea to set
	 */
	public void setLstArea(List<SelectItem> lstArea) {
		this.lstArea = lstArea;
	}

	/**
	 * @return the lstEvaluacion
	 */
	public List<EvaluacionVO> getLstEvaluacion() {
		return lstEvaluacion;
	}

	/**
	 * @param lstEvaluacion
	 *            the lstEvaluacion to set
	 */
	public void setLstEvaluacion(List<EvaluacionVO> lstEvaluacion) {
		this.lstEvaluacion = lstEvaluacion;
	}

	@PostConstruct
	public void init() {
		ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();
		httpServletRequest = (HttpServletRequest) context.getRequest();
		usuarioSession = (PersonaVO) httpServletRequest.getSession()
				.getAttribute("sessionUsario");
		lstEvaluacion = cuestionario.getEvaluacionesForUser(usuarioSession
				.getUsers().getUsuario().trim());
	}


	
	public void goToEditEv() {
		try{
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			httpServletRequest = (HttpServletRequest) context.getRequest();
    		httpServletRequest.getSession().setAttribute("evaEdit", eva);
			context.redirect(context.getRequestContextPath() + "/pagesAdmin/evaluacion/evaluacionEdit.xhtml");
		}catch(Exception e){
			e.getMessage();
		}
		
		
	}
	
	

}
