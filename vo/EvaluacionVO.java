package com.evaluacionlinea.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.evaluacionlinea.model.Cuestionario;

public class EvaluacionVO implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4475977622050484662L;
	private Integer evaId;
    private CuestionarioVO cuestionarioVO;
    private String nombre;
    private String sede;
    private String grupo;
    private Integer duracionHora;
    private Integer duracionMin;
    private Integer calApro;
    private Integer showPDF;

    private Timestamp finiAplicacion;
    private Timestamp ffinAplicacion;
    private Timestamp hrIniAplicacion;
    private Timestamp hrFinAplicacion;
    private String usuario;
    private Timestamp falta;
    private Timestamp fmodifica;
    private Boolean activo;
    private String areaNombre;
    private String estatus;
    private Date duracion;
    private Date inFechAp;
    private Date finFechAp;
    private String hrApIni;
    private String hrApFin;
    private String fecApIni;
    private String fecApFin;
    private Date hrApIniDa;
    private Date hrApFinDa;
    private boolean pdf;
    private boolean encuesta;
    private Set evaCursos = new HashSet(0);
    private Set usuEvaluacions = new HashSet(0);
    private Integer evaExpId;
    private Integer evainstaId;
    
    
    
	public EvaluacionVO() {
		
	}
	/**
	 * @return the evaId
	 */
	public Integer getEvaId() {
		return evaId;
	}
	/**
	 * @param evaId the evaId to set
	 */
	public void setEvaId(Integer evaId) {
		this.evaId = evaId;
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
	 * @return the sede
	 */
	public String getSede() {
		return sede;
	}
	/**
	 * @param sede the sede to set
	 */
	public void setSede(String sede) {
		this.sede = sede;
	}
	/**
	 * @return the grupo
	 */
	public String getGrupo() {
		return grupo;
	}
	/**
	 * @param grupo the grupo to set
	 */
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	/**
	 * @return the duracionHora
	 */
	public Integer getDuracionHora() {
		return duracionHora;
	}
	/**
	 * @param duracionHora the duracionHora to set
	 */
	public void setDuracionHora(Integer duracionHora) {
		this.duracionHora = duracionHora;
	}
	/**
	 * @return the duracionMin
	 */
	public Integer getDuracionMin() {
		return duracionMin;
	}
	/**
	 * @param duracionMin the duracionMin to set
	 */
	public void setDuracionMin(Integer duracionMin) {
		this.duracionMin = duracionMin;
	}
	/**
	 * @return the calApro
	 */
	public Integer getCalApro() {
		return calApro;
	}
	/**
	 * @param calApro the calApro to set
	 */
	public void setCalApro(Integer calApro) {
		this.calApro = calApro;
	}
	/**
	 * @return the showPDF
	 */
	public Integer getShowPDF() {
		return showPDF;
	}
	/**
	 * @param showPDF the showPDF to set
	 */
	public void setShowPDF(Integer showPDF) {
		this.showPDF = showPDF;
		pdf = showPDF == 1 ? true : false;
	}

	/**
	 * @return the finiAplicacion
	 */
	public Timestamp getFiniAplicacion() {
		return finiAplicacion;
	}
	/**
	 * @param finiAplicacion the finiAplicacion to set
	 */
	public void setFiniAplicacion(Timestamp finiAplicacion) {
		this.finiAplicacion = finiAplicacion;
		fecApIni = getStringDate(finiAplicacion);
	}
	/**
	 * @return the ffinAplicacion
	 */
	public Timestamp getFfinAplicacion() {
		return ffinAplicacion;
	}
	/**
	 * @param ffinAplicacion the ffinAplicacion to set
	 */
	public void setFfinAplicacion(Timestamp ffinAplicacion) {
		this.ffinAplicacion = ffinAplicacion;
		fecApFin = getStringDate(ffinAplicacion);
	}
	/**
	 * @return the hrIniAplicacion
	 */
	public Timestamp getHrIniAplicacion() {
		return hrIniAplicacion;
	}
	/**
	 * @param hrIniAplicacion the hrIniAplicacion to set
	 */
	public void setHrIniAplicacion(Timestamp hrIniAplicacion) {
		this.hrIniAplicacion = hrIniAplicacion;
		hrApIni = hrIniAplicacion.getHours() + ":" + hrIniAplicacion.getMinutes();
	}
	/**
	 * @return the hrFinAplicacion
	 */
	public Timestamp getHrFinAplicacion() {
		return hrFinAplicacion;
	}
	/**
	 * @param hrFinAplicacion the hrFinAplicacion to set
	 */
	public void setHrFinAplicacion(Timestamp hrFinAplicacion) {
		this.hrFinAplicacion = hrFinAplicacion;
		hrApFin = hrFinAplicacion.getHours() + ":" + hrFinAplicacion.getMinutes();
	}
	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	/**
	 * @return the falta
	 */
	public Timestamp getFalta() {
		return falta;
	}
	/**
	 * @param falta the falta to set
	 */
	public void setFalta(Timestamp falta) {
		this.falta = falta;
	}
	/**
	 * @return the fmodifica
	 */
	public Timestamp getFmodifica() {
		return fmodifica;
	}
	/**
	 * @param fmodifica the fmodifica to set
	 */
	public void setFmodifica(Timestamp fmodifica) {
		this.fmodifica = fmodifica;
	}
	/**
	 * @return the activo
	 */
	public Boolean getActivo() {
		return activo;
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
	/**
	 * @param activo the activo to set
	 */
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	
	/**
	 * @return the areaNombre
	 */
	public String getAreaNombre() {
		return areaNombre;
	}
	/**
	 * @param areaNombre the areaNombre to set
	 */
	public void setAreaNombre(String areaNombre) {
		this.areaNombre = areaNombre;
	}
	/**
	 * @return the estatus
	 */
	public String getEstatus() {
		return estatus;
	}
	/**
	 * @param estatus the estatus to set
	 */
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	/**
	 * @return the duracion
	 */
	public Date getDuracion() {
		return duracion;
	}
	/**
	 * @param duracion the duracion to set
	 */
	public void setDuracion(Date duracion) {
		this.duracion = duracion;
	}
	/**
	 * @return the inFechAp
	 */
	public Date getInFechAp() {
		return inFechAp;
	}
	/**
	 * @param inFechAp the inFechAp to set
	 */
	public void setInFechAp(Date inFechAp) {
		this.inFechAp = inFechAp;
	}
	/**
	 * @return the finFechAp
	 */
	public Date getFinFechAp() {
		return finFechAp;
	}
	/**
	 * @param finFechAp the finFechAp to set
	 */
	public void setFinFechAp(Date finFechAp) {
		this.finFechAp = finFechAp;
	}
	/**
	 * @return the hrApIni
	 */
	public String getHrApIni() {
		return hrApIni;
	}
	/**
	 * @param hrApIni the hrApIni to set
	 */
	public void setHrApIni(String hrApIni) {
		this.hrApIni = hrApIni;
	}
	/**
	 * @return the hrApFin
	 */
	public String getHrApFin() {
		return hrApFin;
	}
	/**
	 * @param hrApFin the hrApFin to set
	 */
	public void setHrApFin(String hrApFin) {
		this.hrApFin = hrApFin;
	}
	/**
	 * @return the fecApIni
	 */
	public String getFecApIni() {
		return fecApIni;
	}
	/**
	 * @param fecApIni the fecApIni to set
	 */
	public void setFecApIni(String fecApIni) {
		this.fecApIni = fecApIni;
	}
	/**
	 * @return the fecApFin
	 */
	public String getFecApFin() {
		return fecApFin;
	}
	/**
	 * @param fecApFin the fecApFin to set
	 */
	public void setFecApFin(String fecApFin) {
		this.fecApFin = fecApFin;
	}
	/**
	 * @return the hrApIniDa
	 */
	public Date getHrApIniDa() {
		return hrApIniDa;
	}
	/**
	 * @param hrApIniDa the hrApIniDa to set
	 */
	public void setHrApIniDa(Date hrApIniDa) {
		this.hrApIniDa = hrApIniDa;
	}
	/**
	 * @return the hrApFinDa
	 */
	public Date getHrApFinDa() {
		return hrApFinDa;
	}
	/**
	 * @param hrApFinDa the hrApFinDa to set
	 */
	public void setHrApFinDa(Date hrApFinDa) {
		this.hrApFinDa = hrApFinDa;
	}
    
	public String getStringDate(Timestamp fech) {
		if(fech == null)
			return ""; 
		
		Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(fech.getTime()));
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return day + "-" + month + "-" + year;
	}
	/**
	 * @return the evaCursos
	 */
	public Set getEvaCursos() {
		return evaCursos;
	}
	/**
	 * @param evaCursos the evaCursos to set
	 */
	public void setEvaCursos(Set evaCursos) {
		this.evaCursos = evaCursos;
	}
	/**
	 * @return the usuEvaluacions
	 */
	public Set getUsuEvaluacions() {
		return usuEvaluacions;
	}
	/**
	 * @param usuEvaluacions the usuEvaluacions to set
	 */
	public void setUsuEvaluacions(Set usuEvaluacions) {
		this.usuEvaluacions = usuEvaluacions;
	}
	/**
	 * @return the pdf
	 */
	public boolean isPdf() {
		return pdf;
	}
	/**
	 * @param pdf the pdf to set
	 */
	public void setPdf(boolean pdf) {
		this.pdf = pdf;
	}
	/**
	 * @return the encuesta
	 */
	public boolean isEncuesta() {
		return encuesta;
	}
	/**
	 * @param encuesta the encuesta to set
	 */
	public void setEncuesta(boolean encuesta) {
		this.encuesta = encuesta;
	}
	/**
	 * @return the evaExpId
	 */
	public Integer getEvaExpId() {
		return evaExpId;
	}
	/**
	 * @param evaExpId the evaExpId to set
	 */
	public void setEvaExpId(Integer evaExpId) {
		this.evaExpId = evaExpId;
	}
	/**
	 * @return the evainstaId
	 */
	public Integer getEvainstaId() {
		return evainstaId;
	}
	/**
	 * @param evainstaId the evainstaId to set
	 */
	public void setEvainstaId(Integer evainstaId) {
		this.evainstaId = evainstaId;
	}
	
	
	
    

}
