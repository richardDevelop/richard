package com.evaluacionlinea.interfaces;

import java.util.List;

import javax.faces.model.SelectItem;

import com.evaluacionlinea.model.Cuestionario;
import com.evaluacionlinea.model.Curso;
import com.evaluacionlinea.model.Evaluacion;
import com.evaluacionlinea.model.Expositor;
import com.evaluacionlinea.model.MonitoreoEva;
import com.evaluacionlinea.model.Pregunta;
import com.evaluacionlinea.model.Respuesta;
import com.evaluacionlinea.vo.CuestionarioVO;
import com.evaluacionlinea.vo.CursoVO;
import com.evaluacionlinea.vo.EvaluacionVO;
import com.evaluacionlinea.vo.ExpositorVO;
import com.evaluacionlinea.vo.PreguntaVO;

public interface ICuestionario {

	List<CuestionarioVO> getLstCues(Integer areId);

	Integer saveCuestionario(Cuestionario cuestionario);

	Integer saveEvaluacion(EvaluacionVO evaluacion);

	Integer savePregunta(Pregunta pregunta);

	void saveRespuesta(Respuesta respuesta);

	Cuestionario getCuestionarioById(Integer cueId);

	List<PreguntaVO> getPreguntasByCueId(Integer cueId);

	void saveUpdatePregunta(Pregunta pregunta);

	void deletePregunta(Pregunta pregunta);

	void updateRespuesta(Respuesta respuesta);

	void deleteRespuesta(Respuesta repuesta);

	/**
	 * Metodo mediante el cual se obtienen las evaluaciones a las que tiene
	 * acceso de acuerdo al día y la hora en que se esta logueando
	 * 
	 * @param usuId
	 * @return List<Evaluacion>
	 */
	List<Evaluacion> getEvaluacionesByUsuId(Integer usuId);

	List<EvaluacionVO> getEvaluacionesForUser(String user);

	/**
	 * Metodo mediante el cual se monitorea las evaluaciones en curso
	 * 
	 * @param evaId
	 * @return List<MonitoreoEva>
	 */
	List<MonitoreoEva> getMonitoreoEva(Integer evaId);

	/**
	 * Metodo mediante el cual se obtienen las evaluaciones relacionadas a un
	 * area
	 * 
	 * @param areId
	 * @return List<EvaluacionVO>
	 */
	List<EvaluacionVO> getEvaluacionesByAreId(Integer areId);

	List<SelectItem> getLstArea();

	void saveCurso(Curso curso);

	List<CursoVO> findCursoByArea(Integer areId);

	void updCurso(Curso curso);
	
	List<ExpositorVO> getExpositorByCurId(Integer curId);
	
	void saveExpositor(List<Expositor> lstExpoSave);
	
	List<CuestionarioVO> getCuestionarioByTipo(Integer tipoCues);
}
