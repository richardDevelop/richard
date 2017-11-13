/**
 * 
 */
package com.evaluacionlinea.bean;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FlowEvent;

import com.evaluacionlinea.interfaces.ICuestionario;
import com.evaluacionlinea.model.Evaluacion;
import com.evaluacionlinea.model.MonitoreoEva;
import com.evaluacionlinea.vo.EvaluacionVO;
import com.evaluacionlinea.vo.PersonaVO;

/**
 * @author Othoniel
 *
 */
@ManagedBean(name="MbMonitoreo")
@ViewScoped
public class MbMonitoreo implements Serializable{

	private static final long serialVersionUID = -605270684052357027L;
	
	private List<MonitoreoEva> lstMonitoreoEva;
	private EvaluacionVO selectedEvaluacion;
	private List<EvaluacionVO> lstEvaluaciones;
	private List<MonitoreoEva> filteredlstPersonas;
	private Integer areaId;	
	
	@ManagedProperty(value="#{cuestionarioBean}")
	private ICuestionario cuestionarioBean;
	
	
	
	/**
	 * @param cuestionarioBean the cuestionarioBean to set
	 */
	public void setCuestionarioBean(ICuestionario cuestionarioBean) {
		this.cuestionarioBean = cuestionarioBean;
	}

	/**
	 * 
	 */
	public MbMonitoreo() {		
	}

	/**
	 * @return the lstMonitoreoEva
	 */
	public List<MonitoreoEva> getLstMonitoreoEva() {
		return lstMonitoreoEva;
	}

	/**
	 * @param lstMonitoreoEva the lstMonitoreoEva to set
	 */
	public void setLstMonitoreoEva(List<MonitoreoEva> lstMonitoreoEva) {
		this.lstMonitoreoEva = lstMonitoreoEva;
	}	
	
	/**
	 * @return the selectedEvaluacion
	 */
	public EvaluacionVO getSelectedEvaluacion() {
		return selectedEvaluacion;
	}

	/**
	 * @param selectedEvaluacion the selectedEvaluacion to set
	 */
	public void setSelectedEvaluacion(EvaluacionVO selectedEvaluacion) {
		this.selectedEvaluacion = selectedEvaluacion;
	}

	/**
	 * @return the lstEvaluaciones
	 */
	public List<EvaluacionVO> getLstEvaluaciones() {
		return lstEvaluaciones;
	}

	/**
	 * @param lstEvaluaciones the lstEvaluaciones to set
	 */
	public void setLstEvaluaciones(List<EvaluacionVO> lstEvaluaciones) {
		this.lstEvaluaciones = lstEvaluaciones;
	}

	/**
	 * @return the filteredlstPersonas
	 */
	public List<MonitoreoEva> getFilteredlstPersonas() {
		return filteredlstPersonas;
	}

	/**
	 * @param filteredlstPersonas the filteredlstPersonas to set
	 */
	public void setFilteredlstPersonas(List<MonitoreoEva> filteredlstPersonas) {
		this.filteredlstPersonas = filteredlstPersonas;
	}

	/**
	 * @return the areaId
	 */
	public Integer getAreaId() {
		return areaId;
	}

	/**
	 * @param areaId the areaId to set
	 */
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	
	public void getMonitoreoEva(ActionEvent event) {
		if (null != selectedEvaluacion) {
			lstMonitoreoEva = cuestionarioBean.getMonitoreoEva(selectedEvaluacion.getEvaId());
		}				
	}
			
	public void BuscarEvaluacionesByAreaId() {
		lstEvaluaciones =  cuestionarioBean.getEvaluacionesByAreId(this.areaId);		
	}
	
	public String onFlowProcess(FlowEvent event) {
        if (event.getNewStep().equals("MonitoreaEva") && null == selectedEvaluacion) {        	
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Debe seleccionar una Evaluacion", "Falta");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "Evaluacion";
		}
        if (event.getNewStep().equals("MonitoreaEva")){
        	getMonitoreoEva(null);
        }
        return event.getNewStep();
    }
	
	public TimeZone getTimeZone() {  
	  TimeZone timeZone = TimeZone.getDefault();  
	  return timeZone;  
	  }  
}
