package com.richardevelop.learning.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.richarddevelop.learning.model.Nivel;
import com.richarddevelop.learning.model.Users;
import com.richarddevelop.learning.model.UsuArea;
import com.richarddevelop.learning.model.UsuPerfil;
import com.richarddevelop.learning.util.ConversionServices;



public class PersonaVO implements Serializable{

	//SerialVersion
	private static final long serialVersionUID = 1L;

	private Integer perId;
    private UsersVO usersVO;
    private NivelVO nivelVO;
    private String nombre;
    private String apePaterno;
    private String apeMaterno;
    private String numEmpleado;
    private String email;
    private Boolean activo;
    private String usuario;
    private Timestamp falta;
    private Timestamp fmodifica;
    private List<UsuAreaVO> listaUsuAreaVO;
    private List<UsuPerfilVO> listaUsuPerfilVO;
    
	public PersonaVO() {
		// TODO Auto-generated constructor stub
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
	 * @return the usersVO
	 */
	public UsersVO getUsersVO() {
		return usersVO;
	}

	/**
	 * @param usersVO the usersVO to set
	 */
	public void setUsersVO(UsersVO usersVO) {
		this.usersVO = usersVO;
	}

	/**
	 * @return the nivelVO
	 */
	public NivelVO getNivelVO() {
		return nivelVO;
	}

	/**
	 * @param nivelVO the nivelVO to set
	 */
	public void setNivelVO(NivelVO nivelVO) {
		this.nivelVO = nivelVO;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
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
	 * @param activo the activo to set
	 */
	public void setActivo(Boolean activo) {
		this.activo = activo;
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
	 * @return the listaUsuAreaVO
	 */
	public List<UsuAreaVO> getListaUsuAreaVO() {
		return listaUsuAreaVO;
	}

	/**
	 * @param listaUsuAreaVO the listaUsuAreaVO to set
	 */
	public void setListaUsuAreaVO(List<UsuAreaVO> listaUsuAreaVO) {
		this.listaUsuAreaVO = listaUsuAreaVO;
	}

	/**
	 * @return the listaUsuPerfilVO
	 */
	public List<UsuPerfilVO> getListaUsuPerfilVO() {
		return listaUsuPerfilVO;
	}

	/**
	 * @param listaUsuPerfilVO the listaUsuPerfilVO to set
	 */
	public void setListaUsuPerfilVO(List<UsuPerfilVO> listaUsuPerfilVO) {
		this.listaUsuPerfilVO = listaUsuPerfilVO;
	}
	
    // COVERT TO POJO
	/**
	 * @return the users
	 */
	public Users getUsers() {
		return (Users) ConversionServices.transformVoToPojo(usersVO);
	}
	
	/**
	 * @return the nivel
	 */
	public Nivel getNivel() {
		return (Nivel)ConversionServices.transformVoToPojo(nivelVO);
	}
	
	/**
	 * @return the listaUsuArea
	 */
	public List<UsuArea> getListaUsuArea() {
		List<UsuArea> lstUsuArea = new ArrayList<UsuArea>();
		try {
			for (UsuAreaVO usuAreaVO : this.listaUsuAreaVO) {
				lstUsuArea.add((UsuArea)ConversionServices.transformVoToPojo(usuAreaVO));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lstUsuArea;
	}
	
	/**
	 * @return the listaUsuPerfil
	 */
	public List<UsuPerfil> getListaUsuPerfil() {
		List<UsuPerfil> lstUsuPerfil = new ArrayList<UsuPerfil>();
		try {
			for (UsuPerfilVO usuPerfilVO : this.listaUsuPerfilVO) {
				lstUsuPerfil.add((UsuPerfil)ConversionServices.transformVoToPojo(usuPerfilVO));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lstUsuPerfil;
	}
}
