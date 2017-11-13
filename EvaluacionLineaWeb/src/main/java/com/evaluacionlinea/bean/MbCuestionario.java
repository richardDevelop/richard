package com.evaluacionlinea.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.primefaces.event.FileUploadEvent;

import com.evaluacionlinea.interfaces.ICuestionario;
import com.evaluacionlinea.model.Cuestionario;
import com.evaluacionlinea.model.Curso;
import com.evaluacionlinea.model.Evaluacion;
import com.evaluacionlinea.model.Pregunta;
import com.evaluacionlinea.model.Respuesta;
import com.evaluacionlinea.model.TipoCuestionario;
import com.evaluacionlinea.utils.Const;
import com.evaluacionlinea.vo.CuestionarioVO;
import com.evaluacionlinea.vo.CursoVO;
import com.evaluacionlinea.vo.EvaluacionVO;
import com.evaluacionlinea.vo.PersonaVO;

@ManagedBean(name = "mbCuestionario")
@ViewScoped
public class MbCuestionario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -605270684052357027L;

	@ManagedProperty(value="#{cuestionarioBean}")
	private ICuestionario cuestionario;

	private List<SelectItem> lstEstatus;
	private String estatus;

	private List<SelectItem> lstArea;
	private Integer areaId;

	private List<CuestionarioVO> lstCuestionario;

	private List<CursoVO> lstCurso;

	private String nombreCurso;

	private List<SelectItem> lstCursoItem;

	private String nombre;

	private Integer areaIdGral;

	private FileUploadEvent fileUp;

	private HttpServletRequest httpServletRequest;

	private CuestionarioVO cueId;

	private List<Evaluacion> lstEvaluacionByUsuId;

	private PersonaVO usuarioSession;

	private String labelIntrucciones;
	
	

	/**
	 * @param cuestionario the cuestionario to set
	 */
	public void setCuestionario(ICuestionario cuestionario) {
		this.cuestionario = cuestionario;
	}

	public Integer getAreaIdGral() {
		return areaIdGral;
	}

	public void setAreaIdGral(Integer areaIdGral) {
		this.areaIdGral = areaIdGral;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<SelectItem> getLstCursoItem() {
		return lstCursoItem;
	}

	public void setLstCursoItem(List<SelectItem> lstCursoItem) {
		this.lstCursoItem = lstCursoItem;
	}

	public String getNombreCurso() {
		return nombreCurso;
	}

	public void setNombreCurso(String nombreCurso) {
		this.nombreCurso = nombreCurso;
	}

	public List<CuestionarioVO> getLstCuestionario() {
		return lstCuestionario;
	}

	public void setLstCuestionario(List<CuestionarioVO> lstCuestionario) {
		this.lstCuestionario = lstCuestionario;
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

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	/**
	 * @return the cueId
	 */
	public CuestionarioVO getCueId() {
		return cueId;
	}

	/**
	 * @param cueId
	 *            the cueId to set
	 */
	public void setCueId(CuestionarioVO cueId) {
		this.cueId = cueId;
	}

	/**
	 * @return the lstCurso
	 */
	public List<CursoVO> getLstCurso() {
		return lstCurso;
	}

	/**
	 * @param lstCurso
	 *            the lstCurso to set
	 */
	public void setLstCurso(List<CursoVO> lstCurso) {
		this.lstCurso = lstCurso;
	}

	@PostConstruct
	public void getLstCues() {
		try {
			ExternalContext context = FacesContext.getCurrentInstance()
					.getExternalContext();
			httpServletRequest = (HttpServletRequest) context.getRequest();
			usuarioSession = (PersonaVO) httpServletRequest.getSession()
					.getAttribute("sessionUsario");
		} catch (Exception e) {
			e.getMessage();
		}
		validaPerfil();
	}

	private void validaPerfil() {
		Boolean cargaPag = false;
		if (null != httpServletRequest.getSession().getAttribute("perfId")) {
			Integer perfId = (Integer) httpServletRequest.getSession()
					.getAttribute("perfId");

			if (perfId.equals(Const.Perfil_EVALUADO)) {
				cargaPag = true;
				getEvaluacionesByUsuarioId();
			} else if (perfId.equals(Const.Perfil_ROOT)
					|| perfId.equals(Const.Perfil_ADMINTRADOR)) {
				cargaPag = true;
				getBuildLstEstatus();
				lstArea = Const
						.getLstAreaItem(usuarioSession.getListaUsuArea());
			}
		}

		if (!cargaPag) {
			try {
				ExternalContext context = FacesContext.getCurrentInstance()
						.getExternalContext();
				context.redirect(context.getRequestContextPath()
						+ "/Login.xhtml");
			} catch (Exception e) {
				e.getMessage();
			}
		}
	}

	private void getBuildLstEstatus() {
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

	/**
	 * @return the lstEvaluacionByUsuId
	 */
	public List<Evaluacion> getLstEvaluacionByUsuId() {
		return lstEvaluacionByUsuId;
	}

	/**
	 * @param lstEvaluacionByUsuId
	 *            the lstEvaluacionByUsuId to set
	 */
	public void setLstEvaluacionByUsuId(List<Evaluacion> lstEvaluacionByUsuId) {
		this.lstEvaluacionByUsuId = lstEvaluacionByUsuId;
	}

	/**
	 * @return the usuarioSession
	 */
	public PersonaVO getUsuarioSession() {
		return usuarioSession;
	}

	/**
	 * @param usuarioSession
	 *            the usuarioSession to set
	 */
	public void setUsuarioSession(PersonaVO usuarioSession) {
		this.usuarioSession = usuarioSession;
	}

	/**
	 * @return the labelIntrucciones
	 */
	public String getLabelIntrucciones() {
		return labelIntrucciones;
	}

	/**
	 * @param labelIntrucciones
	 *            the labelIntrucciones to set
	 */
	public void setLabelIntrucciones(String labelIntrucciones) {
		this.labelIntrucciones = labelIntrucciones;
	}

	public void handleChange() {
		lstCurso = cuestionario.findCursoByArea(areaId);
		lstCursoItem = new ArrayList<SelectItem>();
		for (CursoVO curso : lstCurso) {
			SelectItem item = new SelectItem(curso.getCurId(),
					curso.getNombre());
			lstCursoItem.add(item);
		}
	}

	public void handleChangeAreaGral() {
		lstCuestionario = cuestionario.getLstCues(areaIdGral);
		areaIdGral = 0;
	}

	public void guardarCuest() {
		Cuestionario cuestionarioSave = new Cuestionario();
		cuestionarioSave.setNombre(nombre);
		cuestionarioSave.setActivo(estatus.equals("V") ? true : false);
		Curso cur = new Curso();
		cur.setCurId(new Integer(nombreCurso));
		cuestionarioSave.setCurso(cur);
		TipoCuestionario tipo = new TipoCuestionario();
		tipo.setTcueId(Const.CUESTIONARIO_NORMAL);
		cuestionarioSave.setTipoCuestionario(tipo);
		cuestionarioSave.setUsuario(usuarioSession.getUsuario());
		cuestionarioSave.setFalta(new Timestamp(new Date().getTime()));
		cuestionarioSave.setFmodifica(new Timestamp(new Date().getTime()));
		Integer cueId = cuestionario.saveCuestionario(cuestionarioSave);
		//guardaCuestionarioExcel(cueId);
		cleanValues();
	}

	// private Curso getCursoById(Integer curId){
	// Curso temp = null;
	// for(Curso curso :lstCurso){
	// if(curso.getCurId().equals(curId)){
	// temp = curso;
	// break;
	// }
	// }
	// return temp;
	// }

	private void cleanValues() {
		nombre = "";
		estatus = "";
		nombreCurso = "";
		areaId = 0;
	}



		

	
	public void goToEditCuestPage() {
		if (cueId == null)
			return;
		try {
			ExternalContext context = FacesContext.getCurrentInstance()
					.getExternalContext();
			httpServletRequest = (HttpServletRequest) context.getRequest();
			httpServletRequest.getSession().setAttribute("idCues", cueId);
			context.redirect(context.getRequestContextPath()
					+ "/pagesAdmin/cuestionario/cuestionarioEdit.xhtml");
		} catch (Exception e) {
			e.getMessage();
		}
	}

	/**
	 * Metodo mediante el cual se obtienen las evaluaciones a las que tiene
	 * acceso de acuerdo al día y la hora en que se esta logueando
	 */
	public List<Evaluacion> getEvaluacionesByUsuarioId() {
		try {
			lstEvaluacionByUsuId = cuestionario
					.getEvaluacionesByUsuId(usuarioSession.getUsers()
							.getUsuId());
			if (null != lstEvaluacionByUsuId && lstEvaluacionByUsuId.size() > 0) {
				labelIntrucciones = "Evaluaciones:";
			} else {
				labelIntrucciones = "Sin Evaluaciones";
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return lstEvaluacionByUsuId;
	}

	public void irEvaluacion(ActionEvent event) {
		String evaId = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("evaId");
		String cueId = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("cueId");
		String contestada = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap()
				.get("contestada");

		if (!evaId.isEmpty() && !cueId.isEmpty() && !contestada.isEmpty()) {
			try {
				ExternalContext context = FacesContext.getCurrentInstance()
						.getExternalContext();
				httpServletRequest = (HttpServletRequest) context.getRequest();
				httpServletRequest.getSession().setAttribute("evaId", evaId);
				httpServletRequest.getSession().setAttribute("cueId", cueId);
				if (!Boolean.parseBoolean(contestada)) {
					context.redirect(context.getRequestContextPath()
							+ "/examenPage/Exa.xhtml");
				} else {
					context.redirect(context.getRequestContextPath()
							+ "/examenPage/Resultado.xhtml");
				}
			} catch (Exception e) {
				e.getMessage();
			}
		}
	}
}
