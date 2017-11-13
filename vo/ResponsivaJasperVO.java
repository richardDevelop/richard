package com.evaluacionlinea.vo;

import java.util.ArrayList;
import java.util.Collection;

public class ResponsivaJasperVO {
	
	private String nombreEva;
	
	private String horaInEva;
	
	private String horaFinEva;
	
	private String fechaInEva;
	
	private String fechaFinEva;
	
	private String nombreCues;
	
	private Integer numEvaluados;
	
	private String sede;
	
	private String grupo;
	
	private String user;
	
	private String duracion;
	
	private String minAprob;

	/**
	 * @return the nombreEva
	 */
	public String getNombreEva() {
		return nombreEva;
	}

	/**
	 * @param nombreEva the nombreEva to set
	 */
	public void setNombreEva(String nombreEva) {
		this.nombreEva = nombreEva;
	}

	/**
	 * @return the horaInEva
	 */
	public String getHoraInEva() {
		return horaInEva;
	}

	/**
	 * @param horaInEva the horaInEva to set
	 */
	public void setHoraInEva(String horaInEva) {
		this.horaInEva = horaInEva;
	}

	/**
	 * @return the horaFinEva
	 */
	public String getHoraFinEva() {
		return horaFinEva;
	}

	/**
	 * @param horaFinEva the horaFinEva to set
	 */
	public void setHoraFinEva(String horaFinEva) {
		this.horaFinEva = horaFinEva;
	}

	/**
	 * @return the fechaInEva
	 */
	public String getFechaInEva() {
		return fechaInEva;
	}

	/**
	 * @param fechaInEva the fechaInEva to set
	 */
	public void setFechaInEva(String fechaInEva) {
		this.fechaInEva = fechaInEva;
	}

	/**
	 * @return the fechaFinEva
	 */
	public String getFechaFinEva() {
		return fechaFinEva;
	}

	/**
	 * @param fechaFinEva the fechaFinEva to set
	 */
	public void setFechaFinEva(String fechaFinEva) {
		this.fechaFinEva = fechaFinEva;
	}

	/**
	 * @return the nombreCues
	 */
	public String getNombreCues() {
		return nombreCues;
	}

	/**
	 * @param nombreCues the nombreCues to set
	 */
	public void setNombreCues(String nombreCues) {
		this.nombreCues = nombreCues;
	}

	/**
	 * @return the numEvaluados
	 */
	public Integer getNumEvaluados() {
		return numEvaluados;
	}

	/**
	 * @param numEvaluados the numEvaluados to set
	 */
	public void setNumEvaluados(Integer numEvaluados) {
		this.numEvaluados = numEvaluados;
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
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the duracion
	 */
	public String getDuracion() {
		return duracion;
	}

	/**
	 * @param duracion the duracion to set
	 */
	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	/**
	 * @return the minAprob
	 */
	public String getMinAprob() {
		return minAprob;
	}

	/**
	 * @param minAprob the minAprob to set
	 */
	public void setMinAprob(String minAprob) {
		this.minAprob = minAprob;
	}
	
	
	public static Collection<ResponsivaJasperVO> getTestReport() {
		Collection<ResponsivaJasperVO> collec = new ArrayList<ResponsivaJasperVO>();
		collec.add(new ResponsivaJasperVO());
		return collec;
	}
	

}
