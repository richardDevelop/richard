package com.evaluacionlinea.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean(name="menuView")
@RequestScoped
public class MbMenuAdmin implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6757460945022824676L;
	
	private String respuestaLog;

	public String getRespuestaLog() {
		return respuestaLog;
	}

	public void setRespuestaLog(String respuestaLog) {
		this.respuestaLog = respuestaLog;
	}
	
	public void doPage(int param) {
		try {
			
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			if(param == 1){
				context.redirect(context.getRequestContextPath() + "/pagesAdmin/curso/curso.xhtml");
			}else if(param == 2){
				context.redirect(context.getRequestContextPath() + "/pagesAdmin/curso/cursoLista.xhtml");
			}else if(param == 3){
				context.redirect(context.getRequestContextPath() + "/pagesAdmin/cuestionario/cuestionario.xhtml");
			}else {
				context.redirect(context.getRequestContextPath() + "/pagesAdmin/evaluacion/evaluacion.xhtml");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void goPageEvaluacion(int value){
		try{
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		if(value == 1){
			context.redirect(context.getRequestContextPath() + "/pagesAdmin/evaluacion/userEvaluacion.xhtml");
		}else if(value == 2){
			context.redirect(context.getRequestContextPath() + "/pagesAdmin/cuestionario/cuestionario.xhtml");
		}
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

}
