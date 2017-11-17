package com.richardevelop.learning.vo;

import java.sql.Timestamp;
import java.util.List;

import com.richarddevelop.learning.model.Curso;
import com.richarddevelop.learning.util.ConversionServices;


public class CuestionarioVO implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer cueId;
	private TipoCuestionarioVO tipoCuestionarioVO;
	private CursoVO cursoVO;
	private String nombre;
	private String descripcion;
    private String usuario;
    private Timestamp falta;
    private Timestamp fmodifica;
	private Boolean activo;
	private List<PreguntaVO> preguntasVO;
	private String estatus;
	private String nombreCurso;
	private Curso curso;
	
	
private Integer cursoId;
	/**
	 * @return the tipoCuestionarioVO
	 */
	public TipoCuestionarioVO getTipoCuestionarioVO() {
		return tipoCuestionarioVO;
	}

	/**
	 * @param tipoCuestionarioVO the tipoCuestionarioVO to set
	 */
	public void setTipoCuestionarioVO(TipoCuestionarioVO tipoCuestionarioVO) {
		this.tipoCuestionarioVO = tipoCuestionarioVO;
	}

	/**
	 * @return the cueId
	 */
	public Integer getCueId() {
		return cueId;
	}

	/**
	 * @param cueId the cueId to set
	 */
	public void setCueId(Integer cueId) {
		this.cueId = cueId;
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
	 * @return the curId
	 */
	public Integer getCursoId() {
		return cursoId;
	}

	/**
	 * @param curId the curId to set
	 */
	public void setCursoId(Integer cursoId) {
		this.cursoId = cursoId;
	}

	/**
	 * @return the nombreCurso
	 */
	public String getNombreCurso() {
		return nombreCurso;
	}

	/**
	 * @param nombreCurso the nombreCurso to set
	 */
	public void setNombreCurso(String nombreCurso) {
		this.nombreCurso = nombreCurso;
	}

	/**
	 * @return the activo
	 */
	public Boolean getActivo() {
		return activo;
	}

	/**
	 * @param activo the activo to set
	 */
	public void setActivo(Boolean activo) {
		this.activo = activo;
		this.estatus = activo ? "Activo" : "Inactivo";
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

	public CursoVO getCursoVO() {
		return cursoVO;
	}

	public void setCursoVO(CursoVO cursoVO) {
		this.cursoVO = cursoVO;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public List<PreguntaVO> getPreguntasVO() {
		return preguntasVO;
	}

	public void setPreguntasVO(List<PreguntaVO> preguntasVO) {
		this.preguntasVO = preguntasVO;
	}

	/**
	 * @return the curso
	 */
	public Curso getCurso() {
		return curso;
	}

	/**
	 * @param curso the curso to set
	 */
	public void setCurso(Curso curso) {
		this.curso = curso;
		cursoVO = (CursoVO) ConversionServices.transformPojoToVo(curso);
	}


}
