package com.evaluacionlinea.interfaces;

import java.util.List;

import com.evaluacionlinea.model.Nivel;
import com.evaluacionlinea.model.Perfil;
import com.evaluacionlinea.model.Persona;
import com.evaluacionlinea.model.Users;
import com.evaluacionlinea.model.UsuArea;
import com.evaluacionlinea.model.UsuPerfil;
import com.evaluacionlinea.vo.PersonaVO;
import com.evaluacionlinea.vo.UsuAreaVO;
import com.evaluacionlinea.vo.UsuPerfilVO;

public interface IPersonaDAO {
	
	Integer save(Persona persona);
	
	PersonaVO getPersonaByLogin(final String usuario, final String password);
	
	List<PersonaVO> getPersonaByAreaId(Integer areaId);
	
	Nivel getNivelById(final Integer nivId);
	
	void saveOrUpdate(Persona persona);
	
	Persona getPersonaByEmail(final String  email);
	
	List<UsuPerfilVO> getPerfilPersonaByUsuId(Integer usuId);
	
	List<PersonaVO> getUsuByEvaId(final Integer evaId);
	
	void ejecutaSP(final String usuH, final String pass, final String nombreEva, 
    		final String apePa, final String apeMa, final String numEmploy,final Integer areId,
   		final String oficce, final String region, final String usuUsuario, final Integer evaId);
	
	 Users save(Users users);
	 
	 List findAll();
	 
	 void saveOrUpdate(Users usuario);
	 
	 void save(UsuArea transientInstance);
	 
	 List<UsuAreaVO> getAreasByUsuId(Integer usuId);
	 
	 void deleteByUsuId(final Integer usuId);
	 
	 void eliminaGuarda(List<UsuArea> lstUsuAreaNueva, final Users users);
	 
	 List<Perfil> getAll();
	 
	 void save(UsuPerfil transientInstance);
	 
	 List<UsuPerfilVO> getPerfilByUsuId(Integer usuId);
	

}
