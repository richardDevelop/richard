/**
 * 
 */
package com.evaluacionlinea.bean;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.evaluacionlinea.interfaces.IExamen;
import com.evaluacionlinea.model.Cuestionario;
import com.evaluacionlinea.model.Evaluacion;
import com.evaluacionlinea.utils.Const;
import com.evaluacionlinea.vo.ContestoVO;
import com.evaluacionlinea.vo.CuestionarioVO;
import com.evaluacionlinea.vo.EvaluacionVO;
import com.evaluacionlinea.vo.PersonaVO;
import com.evaluacionlinea.vo.ResultadoVO;
import com.itextpdf.text.DocumentException;

/**
 * @author gdejesus
 *
 */
@ManagedBean(name="mbResultado")
@RequestScoped
public class MbResultado implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB(beanName="examenBean")
	IExamen examenBean;

	private PersonaVO persona;
	private Integer evaId;
	private Integer cueId;
	private String promedio;
	private String pregVal;
	private String nombre;
	private Boolean enableShowPdf;
	EvaluacionVO eva;
	private CuestionarioVO cuestionarioVO;
	
	private ResultadoVO result;

	//variables temporales cuestionario
	
	

	/**
	 * constructor
	 */
	public MbResultado() {
		FacesContext contex = FacesContext.getCurrentInstance();		
		HttpServletRequest httpServletRequest = (HttpServletRequest)contex.getExternalContext().getRequest();
		cueId = Integer.parseInt(httpServletRequest.getSession().getAttribute("cueId").toString());
		persona = (PersonaVO)httpServletRequest.getSession().getAttribute("sessionUsario");
		nombre = persona.getApePaterno() + " " + persona.getApeMaterno() + " " + persona.getNombre();
		evaId = Integer.parseInt(httpServletRequest.getSession().getAttribute("evaId").toString());
	}
	
	@PostConstruct
	public void init() {
		result = examenBean.findUsuResultado(persona.getUsers().getUsuId(), evaId);
		if(result == null){
			//builtPromedio();
			result = examenBean.getResultadoEvaluacion(evaId, persona.getUsers().getUsuId());
		} 
		promedio = result.getCalificacion().toString();
		eva = examenBean.findEvaByUsuario(evaId);
		//cuestionarioVO = examenBean.findCuestionariobyId(cueId);
		cuestionarioVO = eva.getCuestionarioVO();
		setEnableShowPdf(eva.getShowPDF()== null || eva.getShowPDF() == 0);
		setPregVal(result.getResTotal().toString());
	}
	
	
	
	public void showPdf() {
		try {
			builtPDF();
			ExternalContext externalcontex= FacesContext.getCurrentInstance().getExternalContext();
			HttpServletRequest httpServletRequest = (HttpServletRequest)externalcontex.getRequest();
			httpServletRequest.getSession().setAttribute("content", builtPDF());
			externalcontex.redirect("../examenPage/ExamenPdf.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	public byte[] builtPDF() throws DocumentException {
		List<ContestoVO> listContesto = examenBean.findContestoCueUser(persona.getUsers().getUsuId(), evaId, Const.TIPO_CUE_EXAMEN);
        return Const.builtPDFResultado(cuestionarioVO, listContesto, persona, result);
	}
	
	/**
	 * @return the persona
	 */
	public PersonaVO getPersona() {
		return persona;
	}

	/**
	 * @param persona the persona to set
	 */
	public void setPersona(PersonaVO persona) {
		this.persona = persona;
	}

	/**
	 * @return the promedio
	 */
	public String getPromedio() {
		return promedio;
	}

	/**
	 * @param promedio the promedio to set
	 */
	public void setPromedio(String promedio) {
		this.promedio = promedio;
	}

	/**
	 * @return the pregVal
	 */
	public String getPregVal() {
		return pregVal;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @param pregVal the pregVal to set
	 */
	public void setPregVal(String pregVal) {
		this.pregVal = pregVal;
	}

	/**
	 * @return the enableShowPdf
	 */
	public Boolean getEnableShowPdf() {
		return enableShowPdf;
	}

	/**
	 * @param enableShowPdf the enableShowPdf to set
	 */
	public void setEnableShowPdf(Boolean enableShowPdf) {
		this.enableShowPdf = enableShowPdf;
	}

	/**
	 * @return the cuestionarioVO
	 */
	public CuestionarioVO getCuestionarioVO() {
		return cuestionarioVO;
	}

	/**
	 * @param cuestionarioVO the cuestionarioVO to set
	 */
	public void setCuestionarioVO(CuestionarioVO cuestionarioVO) {
		this.cuestionarioVO = cuestionarioVO;
	}

}
