package com.evaluacionlinea.interfaces;

import java.util.List;

import com.evaluacionlinea.model.Users;
import com.evaluacionlinea.model.UsuPerfil;
import com.evaluacionlinea.vo.UsuPerfilVO;

public interface IUsuPerfilDAO {
	
	void save(UsuPerfil transientInstance);
	
	void deleteByUsuId(final Integer usuId);
	
	List<UsuPerfilVO> getPerfilByUsuId(final Integer usuId);
	
	void eliminaGuarda(final List<UsuPerfil> lstUsuPerfilNuevos, 
			final Users users);
	
	

}
