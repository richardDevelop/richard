package com.evaluacionlinea.interfaces;

import java.io.Serializable;
import java.util.List;

import com.evaluacionlinea.model.Curso;
import com.evaluacionlinea.model.Expositor;
import com.evaluacionlinea.model.Pregunta;
import com.evaluacionlinea.vo.ContestoVO;
import com.evaluacionlinea.vo.CuestionarioVO;
import com.evaluacionlinea.vo.CursoVO;
import com.evaluacionlinea.vo.EvaCursoVO;
import com.evaluacionlinea.vo.ExpositorVO;
import com.evaluacionlinea.vo.PreguntaVO;
import com.evaluacionlinea.vo.ResultadoVO;

public interface ICuestionarioDAO {
	
	Serializable save(final Object transientInstance);
	
	CuestionarioVO findById(final java.lang.Integer id);
	
	List<CuestionarioVO> getCuestionarioByAreaId(final Integer areId);
	
	List<ContestoVO> findContestoCueUser(final Integer usuId, final Integer evaId, 
			final Integer tcue);
	
	Object[] getTimeCurso(final Integer vaId);
	
	EvaCursoVO findEvaCurso(final Integer evaId, final Integer usuId);
	
	ResultadoVO findResultadoByUsuario(final Integer usuId, 
			final Integer evaId);
	
	 List<CursoVO> findCursoByArea(final Integer areId);
	 
	 void updateCurso(final Curso curso);
	 
	 Serializable save(Pregunta transientInstance);
	 
	 List<PreguntaVO> getPreguntaByCueId(final Integer cueId);
	 
	 void updatePreguntaById(final Pregunta pregunta);
	 
	 void deletePregunta(final Pregunta pregunta);
	 
	 List<ExpositorVO> getExpoByCurId(final Integer curId);
	 
	 void save(Expositor expositor);
	 
	 List<CuestionarioVO> getCuestByTipo(final Integer tipoCue);
	
	

}
