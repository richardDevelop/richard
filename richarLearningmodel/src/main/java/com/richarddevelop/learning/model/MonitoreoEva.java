/**
 * 
 */
package com.richarddevelop.learning.model;

import java.io.Serializable;
import java.sql.Time;

/**
 * @author Othoniel
 *
 */
public class MonitoreoEva implements Serializable {

	//SerialVersion
	private static final long serialVersionUID = 1L;

	// Fields
	private Integer evaId;
	private String nombre;
	private String apePaterno;
	private String apeMaterno;
	private String numEmpleado;
	private String usuario;
	private Boolean enCurso;
	private String imgEnCurso;
	private Integer bloque;
	private Time tiempoEnRealizar;
	private Boolean evaTermino;
	private String imgEvaTermino;
	private Float calificacion;
	private Boolean calAprobada;
	private String nivel;
	private Integer perId;
	
	/**
	 * Constructor 
	 */
	public MonitoreoEva() {
		// TODO Auto-generated constructor stub
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
	 * @return the apePaterno
	 */
	public String getApePaterno() {
		return apePaterno;
	}

	/**
	 * @param apePaterno the apePaterno to set
	 */
	public void setApePaterno(String apePaterno) {
		this.apePaterno = apePaterno;
	}

	/**
	 * @return the apeMaterno
	 */
	public String getApeMaterno() {
		return apeMaterno;
	}

	/**
	 * @param apeMaterno the apeMaterno to set
	 */
	public void setApeMaterno(String apeMaterno) {
		this.apeMaterno = apeMaterno;
	}

	/**
	 * @return the numEmpleado
	 */
	public String getNumEmpleado() {
		return numEmpleado;
	}

	/**
	 * @param numEmpleado the numEmpleado to set
	 */
	public void setNumEmpleado(String numEmpleado) {
		this.numEmpleado = numEmpleado;
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
	 * @return the enCurso
	 */
	public Boolean getEnCurso() {
		return enCurso;
	}

	/**
	 * @param enCurso the enCurso to set
	 */
	public void setEnCurso(Boolean enCurso) {
		this.enCurso = enCurso;
	}

	/**
	 * @return the imgEnCurso
	 */
	public String getImgEnCurso() {
		return imgEnCurso;
	}

	/**
	 * @param imgEnCurso the imgEnCurso to set
	 */
	public void setImgEnCurso(String imgEnCurso) {
		this.imgEnCurso = imgEnCurso;
	}

	/**
	 * @return the bloque
	 */
	public Integer getBloque() {
		return bloque;
	}

	/**
	 * @param bloque the bloque to set
	 */
	public void setBloque(Integer bloque) {
		this.bloque = bloque;
	}

	/**
	 * @return the tiempoEnRealizar
	 */
	public Time getTiempoEnRealizar() {
		return tiempoEnRealizar;
	}

	/**
	 * @param tiempoEnRealizar the tiempoEnRealizar to set
	 */
	public void setTiempoEnRealizar(Time tiempoEnRealizar) {
		this.tiempoEnRealizar = tiempoEnRealizar;
	}

	/**
	 * @return the evaTermino
	 */
	public Boolean getEvaTermino() {
		return evaTermino;
	}

	/**
	 * @param evaTermino the evaTermino to set
	 */
	public void setEvaTermino(Boolean evaTermino) {
		this.evaTermino = evaTermino;
	}

	/**
	 * @return the imgEvaTermino
	 */
	public String getImgEvaTermino() {
		return imgEvaTermino;
	}

	/**
	 * @param imgEvaTermino the imgEvaTermino to set
	 */
	public void setImgEvaTermino(String imgEvaTermino) {
		this.imgEvaTermino = imgEvaTermino;
	}

	/**
	 * @return the calificacion
	 */
	public Float getCalificacion() {
		return calificacion;
	}

	/**
	 * @param calificacion the calificacion to set
	 */
	public void setCalificacion(Float calificacion) {
		this.calificacion = calificacion;
	}

	/**
	 * @return the calAprobada
	 */
	public Boolean getCalAprobada() {
		return calAprobada;
	}

	/**
	 * @param calAprobada the calAprobada to set
	 */
	public void setCalAprobada(Boolean calAprobada) {
		this.calAprobada = calAprobada;
	}

	/**
	 * @return the perId
	 */
	public Integer getPerId() {
		return perId;
	}

	/**
	 * @param perId the perId to set
	 */
	public void setPerId(Integer perId) {
		this.perId = perId;
	}

	/**
	 * @return the nivel
	 */
	public String getNivel() {
		return nivel;
	}

	/**
	 * @param nivel the nivel to set
	 */
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
}
