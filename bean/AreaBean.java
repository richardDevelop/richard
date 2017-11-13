package com.evaluacionlinea.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

import com.evaluacionlinea.interfaces.IArea;
import com.evaluacionlinea.interfaces.IAreaDAO;
import com.evaluacionlinea.model.Area;
import com.evaluacionlinea.model.Nivel;
import com.evaluacionlinea.utils.Const;


@Component("AreaBean")
@ImportResource("classpath:ApplicationContex.xml")
public class AreaBean implements IArea {

	@Autowired
	private IAreaDAO areaDAO;
	
	/**
	 * Metodo mediante el cual se obtiene el catalogo de areas
	 * @return List<Area>
	 */
	public List<Area> getAll() {			
		return areaDAO.getAll();
	}
	
	/**
	 * Metodo mediante el cual se obtiene las areas que estan relacionadas al usuario, de acuerdo al perfil
	 * @param usuId
	 * @param perfId
	 * @return List<Area>
	 */
	public List<Area> getByPerfil(Integer usuId, Integer perfId) {
		List<Area> lstAreas = null;
		
		if (perfId.equals(Const.Perfil_ROOT)) {
			lstAreas =areaDAO.getAll();
		} else {
			lstAreas = areaDAO.getByPerfil(usuId);
		}
		return lstAreas;
	}
	
	/**
	 * Metodo mediante el cual se obtiene el catalogo de niveles
	 * @return List<Nivel>
	 */
	public List<Nivel> getAllNiveles() {	
		return areaDAO.getAllNiveles();		
	}
}
