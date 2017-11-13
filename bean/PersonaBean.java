package com.evaluacionlinea.bean;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.evaluacionlinea.ConversionServices;
import com.evaluacionlinea.interfaces.IPersona;
import com.evaluacionlinea.interfaces.IPersonaDAO;
import com.evaluacionlinea.interfaces.IUsuPerfilDAO;
import com.evaluacionlinea.model.Nivel;
import com.evaluacionlinea.model.Perfil;
import com.evaluacionlinea.model.Persona;
import com.evaluacionlinea.model.Users;
import com.evaluacionlinea.model.UsuArea;
import com.evaluacionlinea.model.UsuPerfil;
import com.evaluacionlinea.utils.Const;
import com.evaluacionlinea.vo.EvaluacionVO;
import com.evaluacionlinea.vo.EvaluadosVO;
import com.evaluacionlinea.vo.PersonaVO;
import com.evaluacionlinea.vo.UsersVO;
import com.evaluacionlinea.vo.UsuAreaVO;
import com.evaluacionlinea.vo.UsuPerfilVO;


@Component("personaBean")
@ImportResource("classpath:ApplicationContex.xml")
public class PersonaBean implements IPersona {
	private static Logger log = Logger.getLogger(PersonaBean.class);
	
	@Autowired
	private IPersonaDAO personaDAO;
	
	@Autowired
	private IUsuPerfilDAO usuPerfilDAO;
	
	/**
	 * 
	 * @param personaDAO
	 */
	public void setPersonaDAO(IPersonaDAO personaDAO) {
		this.personaDAO = personaDAO;
	}

	/**
	 * 
	 * @param usuPerfilDAO
	 */
	public void setUsuPerfilDAO(IUsuPerfilDAO usuPerfilDAO) {
		this.usuPerfilDAO = usuPerfilDAO;
	}


	/**
	 * 
	 */
	@Transactional(readOnly = false)
	public PersonaVO getPersonaByLogin(String usuario, String password) {
		PersonaVO personaVO;
		try {
			
			
			personaVO =  personaDAO.getPersonaByLogin(usuario, password);
			if (null != personaVO && null != personaVO.getUsersVO() && null != personaVO.getUsersVO().getUsuId()
					&& personaVO.getUsersVO().getActivo()) {
				Integer usuId = personaVO.getUsersVO().getUsuId();
				List<UsuPerfilVO> lstUsuPerfilVO = personaDAO.getPerfilPersonaByUsuId(usuId);
				personaVO.setListaUsuPerfilVO(lstUsuPerfilVO);
				personaVO.setListaUsuAreaVO(getListaAreasByUsuId(usuId));
			}
			
		} catch (Exception e) {
			throw new RuntimeException("Error al consultar a la persona Login", e.getCause());
		}
		return personaVO;
	}
	
	@Transactional(readOnly = false)
	public List<PersonaVO> getPersonaByAreaId(Integer areaId) {
		
		return personaDAO.getPersonaByAreaId(areaId);
	}
	
	@Transactional(readOnly = false)
	public List<UsuAreaVO> getListaAreasByUsuId(Integer usuId) {		
		List<UsuAreaVO> lstUsuAraVO = null;
		try {
			
			lstUsuAraVO = personaDAO.getAreasByUsuId(usuId);
			
		} catch (RuntimeException e) {
			log.error("Error al consultar las areas por usuId", e);
	         throw e;
		}
		
		return lstUsuAraVO;
	}
	
	/**
	 * 
	 */
	@Transactional(readOnly = false)
	public Integer guardaPersona(PersonaVO personaVO) {
		Integer perId = null;
		Users usuario;
    	try {
    		
    		personaVO.setFalta(Const.getHoraActual());
    		personaVO.setFmodifica(Const.getHoraActual());
    		
    		usuario = (Users)ConversionServices.transformVoToPojo(personaVO.getUsersVO());
    		usuario = personaDAO.save(usuario);
    		personaVO.setUsersVO((UsersVO)ConversionServices.transformPojoToVo(usuario));
    		perId = personaDAO.save((Persona)ConversionServices.transformVoToPojo(personaVO));
    		
    		//Recorremos la lista de areas y los guardamos con relacion al usuario
    		for (UsuAreaVO usuAreaVO : personaVO.getListaUsuAreaVO()) {
    			UsuArea usuArea = (UsuArea)ConversionServices.transformVoToPojo(usuAreaVO);
				usuArea.getId().setUsers(usuario);
				personaDAO.save(usuArea);
			}
    		
    		//Recorremos la lista de perfiles y los guardamos con relacion al usuario
    		for (UsuPerfilVO usuPerfilVO : personaVO.getListaUsuPerfilVO()) {
    			UsuPerfil usuPerfil = (UsuPerfil)ConversionServices.transformVoToPojo(usuPerfilVO);
				usuPerfil.getId().setUsers(usuario);
				usuPerfilDAO.save(usuPerfil);
			}
			
		} catch (RuntimeException e) {
			log.error("Error al generar una nueva persona,usuario,usuArea", e);
	         throw e;
		}
    	return perId;
	}


	@Override
	@Transactional(readOnly = false)
	public void cargaUsuario(List<EvaluadosVO> lstPersona, EvaluacionVO ev, Integer areId) {
		try{
			for(EvaluadosVO evaluado : lstPersona){
				Persona per = new Persona();
				per.setNombre(evaluado.getNombre());
				per.setApePaterno(evaluado.getApellidoPaterno());
				per.setActivo(true);
				per.setApeMaterno(evaluado.getApellidoMaterno());
				per.setEmail(evaluado.getMail().toLowerCase());
				per.setNumEmpleado(evaluado.getNumEmpleado());
				per.setUsuario(ev.getUsuario());
				per.setFalta(new Timestamp(new Date().getTime()));
				per.setFmodifica(new Timestamp(new Date().getTime()));
				per.setNivel(new Nivel());
				
				Users users = new Users();
				users.setActivo(true);
				String pass = Const.generaPassword((PersonaVO)ConversionServices.transformPojoToVo(per));
//				users.setUsuario(evaluado.getMail().toLowerCase());
//				users.setPassword(pass);
//				per.setUsers(users);
//				per.setNivel(personaDAO.getNivelById(evaluado.getNivel()));
//				Users usuarioStore = personaDAO.save(per.getUsers());
//	    		per.setUsers(usuarioStore);
//	    		personaDAO.save(per);
	    		//saveUsuEv(ev, usuarioStore);
				if(!evaluado.getMail().trim().equals("")){
					personaDAO.ejecutaSP(evaluado.getMail().toLowerCase(), pass, 
		    				evaluado.getNombre(), evaluado.getApellidoPaterno(), evaluado.getApellidoMaterno(), 
		    				evaluado.getNumEmpleado(), areId,
		    			      evaluado.getOficina(), evaluado.getRegion(), ev.getUsuario(), ev.getEvaId());	
				}
				
	    	}
			
		}catch (RuntimeException e) {
			log.error("Error al guardar uusuarios", e);
	         throw e;
		}
		
	}
	

	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false)
	@Override
	public List<Users> getAllUser() {
		try{
			return personaDAO.findAll();
		}catch (RuntimeException e) {
			log.error("Error al consultar uusuarios", e);
	         throw e;
		}
	}
	

	/**
	 * Metodo mediante el cual se actualiza la persona
	 * @param persona
	 */
	@Transactional(readOnly = false)
	public void saveOrUpdate(PersonaVO personaVO) {
		try {
    		
    		Persona persona = (Persona)ConversionServices.transformVoToPojo(personaVO);
    		
    		//Asignamos la fecha de modificación
    		persona.setFmodifica(new Timestamp(new Date().getTime()));
    		personaDAO.saveOrUpdate(persona.getUsers());
    		personaDAO.saveOrUpdate(persona);    		
    		
    		Users users = new Users(persona.getUsers());

			personaDAO.eliminaGuarda(persona.getListaUsuArea(), users);
			usuPerfilDAO.eliminaGuarda(persona.getListaUsuPerfil(), users);

		} catch (RuntimeException e) {
			log.error("Error al actualizar a la persona,usuario,usuArea", e);
	         throw e;
		}
	}
	
	/**
	 * Metodo mediante el cual se consulta la persona por email.
	 * @param email
	 * @return Persona
	 */
	@Transactional(readOnly = false)
	public Persona getPersonaByEmail(String email) {
		
		return personaDAO.getPersonaByEmail(email);
	}


	@Override
	@Transactional(readOnly = false)
	public List<PersonaVO> getUsuByEvaId(Integer evaId) {
		return personaDAO.getUsuByEvaId(evaId);
	}
	
	
	
	/**
	 * Metodo mediante el cual se obtiene la relacion usuario vs perfil
	 * @param usuId
	 * @return List<UsuPerfilVO>
	 */
	@Transactional(readOnly = false)
	public List<UsuPerfilVO> getPerfilByUsuId(Integer usuId) {		
		List<UsuPerfilVO> lstUsuPerfilVO = null;
		try {
			
			lstUsuPerfilVO = usuPerfilDAO.getPerfilByUsuId(usuId);
			
		} catch (RuntimeException e) {
			log.error("Error al consultar las areas por usuId", e);
	         throw e;
		}
		return lstUsuPerfilVO;
	}



	/**
	 * Metodo mediante el cual se obtiene el catalogo de Perfiles 
	 * @return List<Perfil>
	 */
	@Transactional(readOnly = false)
	public List<Perfil> getPerfiles() {
		
		return personaDAO.getAll();
	}
}
