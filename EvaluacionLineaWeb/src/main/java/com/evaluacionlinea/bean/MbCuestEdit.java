package com.evaluacionlinea.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.event.RowEditEvent;

import com.evaluacionlinea.interfaces.ICuestionario;
import com.evaluacionlinea.model.Pregunta;
import com.evaluacionlinea.model.Respuesta;
import com.evaluacionlinea.vo.CuestionarioVO;
import com.evaluacionlinea.vo.PreguntaVO;

@ManagedBean(name = "mbCuesEdit")
@ViewScoped
public class MbCuestEdit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value="#{cuestionarioBean}")
	private ICuestionario cuestionarioBean;
	
	private HttpServletRequest httpServletRequest;
	
	private List<PreguntaVO> lstPregunta;
	
	private CuestionarioVO cues;
	
	

	/**
	 * @param cuestionarioBean the cuestionarioBean to set
	 */
	public void setCuestionarioBean(ICuestionario cuestionarioBean) {
		this.cuestionarioBean = cuestionarioBean;
	}



	/**
	 * @return the lstPregunta
	 */
	public List<PreguntaVO> getLstPregunta() {
		return lstPregunta;
	}



	/**
	 * @param lstPregunta the lstPregunta to set
	 */
	public void setLstPregunta(List<PreguntaVO> lstPregunta) {
		this.lstPregunta = lstPregunta;
	}



	@PostConstruct
	public void init(){
		try{
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			httpServletRequest = (HttpServletRequest) context.getRequest();
			cues = (CuestionarioVO) httpServletRequest.getSession().getAttribute("idCues");
			lstPregunta = cuestionarioBean.getPreguntasByCueId(cues.getCueId());
		}catch(Exception e){
			e.getMessage();
		}
	}
	

    
    public void onRowEdit(RowEditEvent event) {
    	Pregunta pregu = (Pregunta)event.getObject();
    	cuestionarioBean.saveUpdatePregunta(pregu);
    	FacesMessage msg = new FacesMessage("Pregunta Actualizada ");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        refresh();
    }
     
    public void onRowCancel(RowEditEvent event) {
    	Pregunta pregu = (Pregunta)event.getObject();
    	cuestionarioBean.deletePregunta(pregu);
    	FacesMessage msg = new FacesMessage("Pregunta Eliminada ");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        refresh();
    }
    
    public void onRowEditRes(RowEditEvent event) {
    	Respuesta respuesta = (Respuesta)event.getObject();
    	cuestionarioBean.updateRespuesta(respuesta);
     	FacesMessage msg = new FacesMessage("Respuesta Actualizada ");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        refresh();
    }
     
    public void onRowCancelRes(RowEditEvent event) {
    	Respuesta res = (Respuesta)event.getObject();
    	cuestionarioBean.deleteRespuesta(res);
    	FacesMessage msg = new FacesMessage("Respuesta Eliminada ");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        refresh();
    }
    
    public void refresh() {
    	
    	try {
    		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			context.redirect(context.getRequestContextPath() + "/pagesAdmin/cuestionario/cuestionarioEdit.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
}
