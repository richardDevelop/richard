package com.evaluacionlinea.interfaces;

import java.util.List;

import com.evaluacionlinea.model.Area;
import com.evaluacionlinea.model.Nivel;

public interface IArea {

	/**
	 * Metodo mediante el cual se consulta el catálogo de aras
	 * @return List<Area>
	 */
	List<Area> getAll();
	
	/**
	 * Metodo mediante el cual se obtiene el catalogo de niveles
	 * @return List<Nivel>
	 */
	List<Nivel> getAllNiveles();
	
	/**
	 * Metodo mediante el cual se obtiene las areas que estan relacionadas al usuario, de acuerdo al perfil
	 * @param usuId
	 * @param perfId
	 * @return
	 */
	List<Area> getByPerfil(Integer usuId, Integer perfId);
}
