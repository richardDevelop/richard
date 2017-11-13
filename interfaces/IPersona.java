package com.evaluacionlinea.interfaces;

import java.util.List;

import com.evaluacionlinea.model.Evaluacion;
import com.evaluacionlinea.model.Perfil;
import com.evaluacionlinea.model.Persona;
import com.evaluacionlinea.model.Users;
import com.evaluacionlinea.vo.EvaluacionVO;
import com.evaluacionlinea.vo.EvaluadosVO;
import com.evaluacionlinea.vo.PersonaVO;
import com.evaluacionlinea.vo.UsuAreaVO;
import com.evaluacionlinea.vo.UsuPerfilVO;

public interface IPersona {
	
	/**
	 * Metodo mediante el cual se obtiene los datos de la persona de usuario logueado
	 * @param usuario
	 * @param password
	 * @return
	 */
	PersonaVO getPersonaByLogin(String usuario, String password);
	
	/**
	 * Metodo mediante el cual se obtienen aquellas personas y usuario que pertenecen a un are
	 * @param areaId
	 * @return
	 */
	List<PersonaVO> getPersonaByAreaId(Integer areaId);
	
	/**
	 * Metodo mediante el cual se obtienen las areas asociadas a un usuario
	 * @param usuId
	 * @return List<UsuAreaVO>
	 */
	List<UsuAreaVO> getListaAreasByUsuId(Integer usuId);
	
	/**
	 * Metodo mediante el cual se genera el registro de una nueva persona
	 * @param personaVO
	 * @return perId
	 */
	Integer guardaPersona(PersonaVO personaVO);
	
	/**
	 * Carga masiva de usuarios
	 * @param lstPersonaSave
	 * @param areId 
	 */
	void cargaUsuario(List<EvaluadosVO> lstPersonaSave, EvaluacionVO ev, Integer areId);
	
	List<Users> getAllUser();
	
	/**
	 * Metodo mediante el cual se actualiza la persona
	 * @param personaVO
	 */
	void saveOrUpdate(PersonaVO personaVO);
	
	/**
	 * Metodo mediante el cual se consulta la persona por email.
	 * @param email
	 * @return Persona
	 */
	Persona getPersonaByEmail(String email);
	
	List<PersonaVO> getUsuByEvaId(Integer evaId);
	
	/**
	 * Metodo mediante el cual se obtiene el catalogo de Perfiles
	 * @return List<Perfil>
	 */
	List<Perfil> getPerfiles();
	
	/**
	 * Metodo mediante el cual se obtiene la relacion usuario vs perfil
	 * @param usuId
	 * @return List<UsuPerfilVO>
	 */
	List<UsuPerfilVO> getPerfilByUsuId(Integer usuId);
}
