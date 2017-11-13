package com.evaluacionlinea.interfaces;

import java.util.List;

import com.evaluacionlinea.model.Contesto;
import com.evaluacionlinea.model.Evaluacion;
import com.evaluacionlinea.model.Resultado;
import com.evaluacionlinea.vo.ContestoVO;
import com.evaluacionlinea.vo.CuestionarioVO;
import com.evaluacionlinea.vo.EvaCursoVO;
import com.evaluacionlinea.vo.EvaluacionVO;
import com.evaluacionlinea.vo.ResultadoVO;

public interface IExamen {

	/**
	 * Recupera Cuestionario aplicar
	 * @param id
	 * @return
	 */
	CuestionarioVO findCuestionariobyId(Integer id);
	
	/**
	 * Guarda Respuestas examen
	 * @param cont
	 */
	void saveCuesRes(List<ContestoVO> cont);
	
	/**
	 * 
	 * @param usuId
	 * @param evaId
	 * @param tcue
	 * @return
	 */
	List<ContestoVO> findContestoCueUser(Integer usuId, Integer evaId, Integer tcue);

	/**
	 * Obtiene el tiempo de la evaluacion en segundos
	 * @param cueId
	 * @return
	 */
	Integer getTimeEvaluacion(Integer cueId);
	
	/**
	 * busca evaluacion en cuerso
	 * @param evaId
	 * @param usuId
	 * @return
	 */
	EvaCursoVO findEvaCurso(Integer evaId, Integer usuId);
	
	/**
	 * Guarda evaluacion en curso
	 * @param evacurso
	 * @return serializable
	 */
	int saveEvacurso (EvaCursoVO evacurso);
	
	/**
	 * 
	 * @param evaCurso
	 */
	void updateEvacurso(EvaCursoVO evaCurso);
	
	/**
	 * Busca el resultado del usuario
	 * @param usuId
	 * @return
	 */
	ResultadoVO findUsuResultado (Integer usuId, Integer evaId);
	
	/**
	 * Guarda Resultado
	 * @param result
	 */
	void saveResultado(Resultado result);
	
	/**
	 * 
	 * @param evaId
	 * @return
	 */
	EvaluacionVO findEvaByUsuario(Integer evaId);
	
	/**
	 * 
	 * @param evaid
	 * @param usuId
	 * @return
	 */
	ResultadoVO getResultadoEvaluacion(Integer evaid, Integer usuId);
}
