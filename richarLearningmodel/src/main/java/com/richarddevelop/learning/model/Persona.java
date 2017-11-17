package com.richarddevelop.learning.model;

// default package

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.richarddevelop.learning.util.ConversionServices;
import com.richardevelop.learning.vo.NivelVO;
import com.richardevelop.learning.vo.UsersVO;
import com.richardevelop.learning.vo.UsuAreaVO;
import com.richardevelop.learning.vo.UsuPerfilVO;

/**
 * Persona entity. @author MyEclipse Persistence Tools
 */

public class Persona implements java.io.Serializable {

	// SerialVersion
	private static final long serialVersionUID = 1L;

	// Fields

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "per_id")
	private Integer perId;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "usu_id")
	private Users users;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "niv_id")
	private Nivel nivel;

	@Column(name = "per_id")
	private String nombre;

	@Column(name = "apePaterno")
	private String apePaterno;

	@Column(name = "apeMaterno")
	private String apeMaterno;

	@Column(name = "numEmpleado")
	private String numEmpleado;

	@Column(name = "email")
	private String email;

	@Column(name = "activo")
	private Boolean activo;

	@Column(name = "usuario")
	private String usuario;

	@Column(name = "fAlta")
	private Timestamp falta;

	@Column(name = "fModifica")
	private Timestamp fmodifica;

	// no estan en el mapeo
//	private List<UsuArea> listaUsuArea;
//	private List<UsuPerfil> listaUsuPerfil;
	private String estatus;

	// Constructors

	/** default constructor */
	public Persona() {
	}

	public Persona(Integer perId, Users users, Nivel nivel, String nombre,
			String apePaterno, String apeMaterno, String numEmpleado,
			String email, Boolean activo, String usuario, Timestamp falta,
			Timestamp fmodifica) {
		this.perId = perId;
		this.users = users;
		this.nivel = nivel;
		this.nombre = nombre;
		this.apePaterno = apePaterno;
		this.apeMaterno = apeMaterno;
		this.numEmpleado = numEmpleado;
		this.email = email;
		this.activo = activo;
		this.usuario = usuario;
		this.falta = falta;
		this.fmodifica = fmodifica;
	}

	public Persona(Persona persona) {
		this.perId = persona.getPerId();
		this.users = new Users(persona.getUsers());
		this.nivel = new Nivel(persona.getNivel());
		this.nombre = persona.getNombre();
		this.apePaterno = persona.getApePaterno();
		this.apeMaterno = persona.getApeMaterno();
		this.numEmpleado = persona.getNumEmpleado();
		this.email = persona.getEmail();
		this.activo = persona.getActivo();
		this.usuario = persona.getUsuario();
		this.falta = persona.getFalta();
		this.fmodifica = new Timestamp(new Date().getTime());
	}

	/**
	 * @return the perId
	 */
	public Integer getPerId() {
		return perId;
	}

	/**
	 * @param perId
	 *            the perId to set
	 */
	public void setPerId(Integer perId) {
		this.perId = perId;
	}

	/**
	 * @return the users
	 */
	public Users getUsers() {
		return users;
	}

	/**
	 * @param users
	 *            the users to set
	 */
	public void setUsers(Users users) {
		this.users = users;
	}

	/**
	 * @return the nivel
	 */
	public Nivel getNivel() {
		return nivel;
	}

	/**
	 * @param nivel
	 *            the nivel to set
	 */
	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
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
	 * @return the apePaterno
	 */
	public String getApePaterno() {
		return apePaterno;
	}

	/**
	 * @param apePaterno
	 *            the apePaterno to set
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
	 * @param apeMaterno
	 *            the apeMaterno to set
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
	 * @param numEmpleado
	 *            the numEmpleado to set
	 */
	public void setNumEmpleado(String numEmpleado) {
		this.numEmpleado = numEmpleado;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
		this.estatus = activo ? "Activo" : "Inactivo";
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

//	/**
//	 * @return the listaUsuArea
//	 */
//	public List<UsuArea> getListaUsuArea() {
//		return listaUsuArea;
//	}
//
//	/**
//	 * @param listaUsuArea
//	 *            the listaUsuArea to set
//	 */
//	public void setListaUsuArea(List<UsuArea> listaUsuArea) {
//		this.listaUsuArea = listaUsuArea;
//	}
//
//	public List<UsuPerfil> getListaUsuPerfil() {
//		return listaUsuPerfil;
//	}
//
//	public void setListaUsuPerfil(List<UsuPerfil> listaUsuPerfil) {
//		this.listaUsuPerfil = listaUsuPerfil;
//	}

	/**
	 * @return the estatus
	 */
	public String getEstatus() {
		return estatus;
	}

	/**
	 * @param estatus
	 *            the estatus to set
	 */
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	// COVERT TO VO
	/**
	 * @return the usersVO
	 */
	public UsersVO getUsersVO() {
		return (UsersVO) ConversionServices.transformPojoToVo(users);
	}

	/**
	 * @return the nivelVO
	 */
	public NivelVO getNivelVO() {
		return (NivelVO) ConversionServices.transformPojoToVo(nivel);
	}

//	/**
//	 * @return the listaUsuAreaVO
//	 */
//	public List<UsuAreaVO> getListaUsuAreaVO() {
//		List<UsuAreaVO> lstUsuAreaVO = new ArrayList<UsuAreaVO>();
//		try {
//			if (this.listaUsuArea != null) {
//				for (UsuArea usuArea : this.listaUsuArea) {
//					lstUsuAreaVO.add((UsuAreaVO) ConversionServices
//							.transformVoToPojo(usuArea));
//				}
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return lstUsuAreaVO;
//	}
//
//	/**
//	 * @return the listaUsuPerfil
//	 */
//	public List<UsuPerfilVO> getListaUsuPerfilVO() {
//		List<UsuPerfilVO> lstUsuPerfilVO = new ArrayList<UsuPerfilVO>();
//		try {
//			if (this.listaUsuPerfil != null) {
//				for (UsuPerfil usuPerfil : this.listaUsuPerfil) {
//					lstUsuPerfilVO.add((UsuPerfilVO) ConversionServices
//							.transformVoToPojo(usuPerfil));
//				}
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return lstUsuPerfilVO;
//	}

}