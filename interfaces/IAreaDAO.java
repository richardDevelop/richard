package com.evaluacionlinea.interfaces;

import java.util.List;

import com.evaluacionlinea.model.AreCurso;
import com.evaluacionlinea.model.Area;
import com.evaluacionlinea.model.Nivel;

public interface IAreaDAO {

	 void save(Area transientInstance);
	 
	 List findAll();
	 
	 List<Area> getAll();
	 
	 List<Nivel> getAllNiveles();
	 
	 Area findAreaByIdMio(final Integer areId);
	 
	 List<Area> getByPerfil(Integer usuId);
	 
	 void updateCurso(final AreCurso curso);
	 
	 void save(AreCurso transientInstance);
}
