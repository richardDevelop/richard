package com.richarddevelop.learning.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.richarddevelop.learning.util.ConversionServices;
import com.richardevelop.learning.vo.CursoVO;
import com.richardevelop.learning.vo.PreguntaVO;

/**
 * Cuestionario entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cuestionario")
public class Cuestionario implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cue_id")
	private Integer cueId;

	@ManyToOne(optional = false)
	@JoinColumn(name = "tcue_id")
	private TipoCuestionario tipoCuestionario;

	@ManyToOne(optional = false)
	@JoinColumn(name = "cur_id")
	private Curso curso;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "usuario")
	private String usuario;

	@Column(name = "fAlta")
	private Timestamp falta;

	@Column(name = "FModifica")
	private Timestamp fmodifica;

	@Column(name = "activo")
	private Boolean activo;
	
	@ManyToOne(targetEntity = Pregunta.class)
	@JoinColumn(name = "cur_id", insertable = false, updatable = false)
	private Set<Pregunta> preguntas;
	
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
	 * @param cueId
	 *            the cueId to set
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
	 * @param tipoCuestionario
	 *            the tipoCuestionario to set
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
	 * @param curso
	 *            the curso to set
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
	 * @param nombre
	 *            the nombre to set
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
	 * @param descripcion
	 *            the descripcion to set
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
	 * @param usuario
	 *            the usuario to set
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
	 * @param falta
	 *            the falta to set
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
	 * @param fmodifica
	 *            the fmodifica to set
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
	 * @param activo
	 *            the activo to set
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
	 * @param preguntas
	 *            the preguntas to set
	 */
	public void setPreguntas(Set<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}

	/**
	 * Regresa lista de preguntaVO
	 * 
	 * @return
	 */
	public List<PreguntaVO> getPreguntasVO() {
		List<PreguntaVO> list = new ArrayList<PreguntaVO>();
		try {
			for (Pregunta pregunta : this.preguntas) {
				list.add((PreguntaVO) ConversionServices
						.transformPojoToVo(pregunta));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public CursoVO getCursoVO() {
		return (CursoVO) ConversionServices.transformPojoToVo(this.curso);
	}
}