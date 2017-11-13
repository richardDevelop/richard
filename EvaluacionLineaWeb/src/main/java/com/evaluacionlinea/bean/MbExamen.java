/**
 * 
 */
package com.evaluacionlinea.bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.evaluacionlinea.interfaces.IExamen;
import com.evaluacionlinea.utils.Const;
import com.evaluacionlinea.utils.Tiempo;
import com.evaluacionlinea.vo.ContestoVO;
import com.evaluacionlinea.vo.CuestionarioVO;
import com.evaluacionlinea.vo.EvaCursoVO;
import com.evaluacionlinea.vo.EvaluacionVO;
import com.evaluacionlinea.vo.PersonaVO;
import com.evaluacionlinea.vo.PreguntaVO;

/**
 * @author gdejesus
 *
 */
@ManagedBean(name="mbExamen")
@ViewScoped
public class MbExamen implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value="#{examenBean}")
	IExamen examenBean;

	private CuestionarioVO cuestionario;
	private CuestionarioVO cuesExpositor;
	private List<PreguntaVO> preguntas;
	private String bloque;
	private final Integer noPreguntas = 5;
	private Integer noBloques;
	private Integer bloqueActual;
	private String buttonLabel;
	private Tiempo tiempo;
	private EvaCursoVO evaCurso;
	private Integer timeCue = 5000;
	private Integer noPreguntaActual;
	private Integer totalPreCue;
	private EvaluacionVO evaluacionVO;
	private Boolean isExpositor;
	private final String timeLimit = "Tiempo Limite";
	private final String timetras = "Tiempo transcurrido en el bloque";
	private String labeltLimit = "";
	private String labelTTrans = "";
	private PersonaVO persona;
	
	//variables temporales cuestionario
	private Integer cueId;
	//private Integer usuId = 1;
	private Integer evaId;
	
	
	/**
	 * @param examenBean the examenBean to set
	 */
	public void setExamenBean(IExamen examenBean) {
		this.examenBean = examenBean;
	}

	public MbExamen() throws IOException {
		try {
			FacesContext contex = FacesContext.getCurrentInstance();		
			HttpServletRequest httpServletRequest = (HttpServletRequest)contex.getExternalContext().getRequest();
			cueId = Integer.parseInt(httpServletRequest.getSession().getAttribute("cueId").toString());
			evaId = Integer.parseInt(httpServletRequest.getSession().getAttribute("evaId").toString());
			httpServletRequest.getSession().setAttribute("cueId", cueId);
			persona = (PersonaVO)httpServletRequest.getSession().getAttribute("sessionUsario");
		} catch (Exception e) {
			ExternalContext externalcontex= FacesContext.getCurrentInstance().getExternalContext();
			externalcontex.redirect("../Instrucciones.xhtml");
			
		}
	}
	
	@PostConstruct
    public void init() {
		
		evaCurso = examenBean.findEvaCurso(evaId, persona.getUsers().getUsuId());
		setTimeCue(examenBean.getTimeEvaluacion(evaId));
		evaluacionVO = examenBean.findEvaByUsuario(evaId);
		if (evaluacionVO.getEvaExpId() != null && evaluacionVO.getEvaExpId() > 0) {
			List<ContestoVO> lstContesto = 
					examenBean.findContestoCueUser(persona.getUsers().getUsuId(), evaId, Const.TIPO_CUE_ENCUESTA);
			if(lstContesto.size() > 0)
				initCuestionario();
			else
				initEncuesta();				
		} else {
			initCuestionario();
		}
		
	}
	
	private void initCuestionario() {
		setIsExpositor(false);
		labeltLimit = timeLimit;
		labelTTrans = timetras;
		if(cuestionario == null){
			getCuestionarioBean();
			tiempo = new Tiempo();
			setButtonLabel("Siguiente");
		}
		if (evaCurso == null) {
			evaCurso = new EvaCursoVO();
			evaCurso.setBloque(bloqueActual);
			evaCurso.setEvaId(evaId);
			evaCurso.setHbloque(Const.getHoraActual());
			evaCurso.setHinicio(Const.getHoraActual());
			evaCurso.setUsuId(persona.getUsers().getUsuId());
			evaCurso.setEcurId(examenBean.saveEvacurso(evaCurso));
		} else {
			Integer segTrans = (int) Const.difSegundosTimestamp(evaCurso.getHinicio(),evaCurso.getHbloque());
			timeCue = timeCue - segTrans;
			if(timeCue <= 0){
				finCuestionario();
				return;
			}
		}
		builtCuestionario();
	}
	
	private void initEncuesta() {
		setIsExpositor(true);
		setButtonLabel("Terminar");
		setBloque("Encuesta");
		cuesExpositor = examenBean.findCuestionariobyId(evaluacionVO.getEvaExpId());
		setPreguntas(null);
		
	}
	
	public void builtCuestionario() {
		evaCurso.setBloque(bloqueActual);
		evaCurso.setHbloque(Const.getHoraActual());
		examenBean.updateEvacurso(evaCurso);
		if (!cuestionario.getPreguntasVO().isEmpty() && timeCue > 0){
			setBloque("Bloque " + bloqueActual.toString() + " de " + noBloques.toString());
			setButtonLabel(bloqueActual.equals(noBloques) ? "Finalizar" :"Siguiente");
			noPreguntaActual = (bloqueActual-1)*noPreguntas;
			bloqueActual++;
			setPreguntas(cuestionario.getPreguntasVO());
			tiempo.Contar();
		} else {
			finCuestionario();
		}
	}
	
	public void finCuestionario () {
		try {
			cuestionario = null;
			tiempo = null;
			timeCue = 0;
			ExternalContext externalcontex= FacesContext.getCurrentInstance().getExternalContext();
			externalcontex.redirect("../examenPage/Resultado.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveContesto () {
		if (!getIsExpositor()) {
			tiempo.Detener();
			timeCue -= tiempo.getSegundos();
		}
		if(preguntas != null && !preguntas.isEmpty()) {
			List<ContestoVO> contesto = new ArrayList<ContestoVO>();
			for (PreguntaVO pregunta : preguntas) {
				if(pregunta.getContestoVO().getResId() != null)
					contesto.add(pregunta.getContestoVO());
			}
			examenBean.saveCuesRes(contesto);
		}
		if (getIsExpositor())
			initCuestionario(); 
		else 
			builtCuestionario();
	}
	
	//tiempo limite
	public void onTimeout(){
		timeCue = 0;
		saveContesto();
		finCuestionario();
    }
	
	private void getCuestionarioBean() {
		
		cuestionario = examenBean.findCuestionariobyId(cueId);
		List<ContestoVO> lstContesto = examenBean.findContestoCueUser(persona.getUsers().getUsuId(), evaId, Const.TIPO_CUE_EXAMEN);
		
		totalPreCue = cuestionario.getPreguntasVO().size() ;
		noBloques = totalPreCue / noPreguntas;
		Integer inparnoBloques = cuestionario.getPreguntasVO().size() % noPreguntas;
		noBloques += inparnoBloques.equals(0) ? 0 : 1;
		siguiente:
			for (ContestoVO contesto : lstContesto) {
				for (PreguntaVO pregunta : cuestionario.getPreguntasVO()){
					if (contesto.getPreId().equals(pregunta.getPreId())) {
						cuestionario.getPreguntasVO().remove(pregunta);
						continue siguiente;
					}
				}
			}
		bloqueActual = 1;
		if (!totalPreCue.equals(cuestionario.getPreguntasVO().size())){
			bloqueActual += ((totalPreCue - cuestionario.getPreguntasVO().size()) / noPreguntas);
			 
		}
	}
	/**
	 * @return the preguntas
	 */
	public List<PreguntaVO> getPreguntas() {
		return preguntas;
	}
	/**
	 * @param preguntas the preguntas to set
	 */
	public void setPreguntas(List<PreguntaVO> preguntas) {
		if (getIsExpositor())
			this.preguntas = Const.getPregunta(cuesExpositor, persona.getUsers().getUsuId(), evaId);
		else 
			this.preguntas = Const.getPreguntaRandom(cuestionario, noPreguntas, persona.getUsers().getUsuId(), evaId);
	}
	/**
	 * @return the bloque
	 */
	public String getBloque() {
		return bloque;
	}
	/**
	 * @param bloque the bloque to set
	 */
	public void setBloque(String bloque) {
		this.bloque = bloque;
	}
	

	/**
	 * @return the buttonLabel
	 */
	public String getButtonLabel() {
		return buttonLabel;
	}

	/**
	 * @param buttonLabel the buttonLabel to set
	 */
	public void setButtonLabel(String buttonLabel) {
		this.buttonLabel = buttonLabel;
	}

	/**
	 * @return the timeCue
	 */
	public Integer getTimeCue() {
		return timeCue;
	}

	/**
	 * @param timeCue the timeCue to set
	 */
	public void setTimeCue(Integer timeCue) {
		this.timeCue = timeCue;
	}

	/**
	 * @return the noPreguntaActual
	 */
	public Integer getNoPreguntaActual() {
		return noPreguntaActual;
	}

	/**
	 * @param noPreguntaActual the noPreguntaActual to set
	 */
	public void setNoPreguntaActual(Integer noPreguntaActual) {
		this.noPreguntaActual = noPreguntaActual;
	}

	/**
	 * @return the isExpositor
	 */
	public Boolean getIsExpositor() {
		return isExpositor;
	}

	/**
	 * @param isExpositor the isExpositor to set
	 */
	public void setIsExpositor(Boolean isExpositor) {
		this.isExpositor = isExpositor;
	}

	/**
	 * @return the labeltLimit
	 */
	public String getLabeltLimit() {
		return labeltLimit;
	}

	/**
	 * @param labeltLimit the labeltLimit to set
	 */
	public void setLabeltLimit(String labeltLimit) {
		this.labeltLimit = labeltLimit;
	}

	/**
	 * @return the labelTTrans
	 */
	public String getLabelTTrans() {
		return labelTTrans;
	}

	/**
	 * @param labelTTrans the labelTTrans to set
	 */
	public void setLabelTTrans(String labelTTrans) {
		this.labelTTrans = labelTTrans;
	}
  
}
