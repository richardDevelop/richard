package com.evaluacionlinea.interfaces;

import java.io.Serializable;
import java.util.List;

import com.evaluacionlinea.model.Evaluacion;
import com.evaluacionlinea.model.MonitoreoEva;
import com.evaluacionlinea.model.Respuesta;
import com.evaluacionlinea.vo.EvaluacionVO;
import com.evaluacionlinea.vo.ResultadoVO;

public interface IEvaluacionDAO {
	
	Serializable save(Evaluacion transientInstance);

	List<Evaluacion> getEvaluacionesByUsuId(final Integer usuId);
	
	List<EvaluacionVO> getEvaluacionesForUser(final String user);
	
	EvaluacionVO getEvaByUserAndEva(final Integer evaId);
	
	List<MonitoreoEva> getMonitoreoEva(final Integer evaId);
	
	List<EvaluacionVO> getEvaluacionesByAreId(final Integer areId);
	
	ResultadoVO getResultadoEvaluacion (final Integer evaId, final Integer usuId);
	
	void save(Respuesta transientInstance);
	
	void delete(Respuesta persistentInstance);
	
	Respuesta findById(final java.lang.Integer id);
	
	void updateRespuestaById(final Respuesta respuesta);
	
	void deleteRespuestaById(final Respuesta respuesta);
}
