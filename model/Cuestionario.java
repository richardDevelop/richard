package com.evaluacionlinea.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.evaluacionlinea.ConversionServices;
import com.evaluacionlinea.vo.CursoVO;
import com.evaluacionlinea.vo.PreguntaVO;


/**
 * Cuestionario entity. @author MyEclipse Persistence Tools
 */

public class Cuestionario  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer cueId;
     private TipoCuestionario tipoCuestionario;
     private Curso curso;
     private String nombre;
     private String descripcion;
     private String usuario;
     private Timestamp falta;
     private Timestamp fmodifica;
     private Boolean activo;
     private Set <Pregunta> preguntas;
     private String estatus;


    // Constructors

    public String getEstatus() {
		return estatus;
	}


	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}


	/** default constructor */
    public Cuestionario() {
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
	 * @return the tipoCuestionario
	 */
	public TipoCuestionario getTipoCuestionario() {
		return tipoCuestionario;
	}


	/**
	 * @param tipoCuestionario the tipoCuestionario to set
	 */
	public void setTipoCuestionario(TipoCuestionario tipoCuestionario) {
		this.tipoCuestionario = tipoCuestionario;
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
	}




	/**
	 * @return the preguntas
	 */
	public Set<Pregunta> getPreguntas() {
		return preguntas;
	}


	/**
	 * @param preguntas the preguntas to set
	 */
	public void setPreguntas(Set<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}
	
	/**
	 * Regresa lista de preguntaVO
	 * @return
	 */
	public List<PreguntaVO> getPreguntasVO(){
		List<PreguntaVO> list = new ArrayList<PreguntaVO>();
		try {
			for (Pregunta pregunta: this.preguntas) {
				list.add((PreguntaVO) ConversionServices.transformPojoToVo(pregunta));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public CursoVO getCursoVO () {
		return (CursoVO) ConversionServices.transformPojoToVo(this.curso);
	}
}