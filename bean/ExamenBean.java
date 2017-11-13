/**
 * 
 */
package com.evaluacionlinea.bean;

import java.util.List;

import javax.ejb.Stateless;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.evaluacionlinea.ConversionServices;
import com.evaluacionlinea.interfaces.ICuestionarioDAO;
import com.evaluacionlinea.interfaces.IEvaluacionDAO;
import com.evaluacionlinea.interfaces.IExamen;
import com.evaluacionlinea.model.Contesto;
import com.evaluacionlinea.model.EvaCurso;
import com.evaluacionlinea.model.Resultado;
import com.evaluacionlinea.vo.ContestoVO;
import com.evaluacionlinea.vo.CuestionarioVO;
import com.evaluacionlinea.vo.EvaCursoVO;
import com.evaluacionlinea.vo.EvaluacionVO;
import com.evaluacionlinea.vo.ResultadoVO;

/**
 * @author WebServer
 *
 */

@Component("examenBean")
@ImportResource("classpath:ApplicationContex.xml")
public class ExamenBean implements IExamen {

	@Autowired
	private ICuestionarioDAO cuestionarioDao;
	
	@Autowired
	private IEvaluacionDAO evaluacionDAO;
	
	/**
	 * @param cuestionarioDao the cuestionarioDao to set
	 */
	public void setCuestionarioDao(ICuestionarioDAO cuestionarioDao) {
		this.cuestionarioDao = cuestionarioDao;
	}


	/**
	 * @param evaluacionDAO the evaluacionDAO to set
	 */
	public void setEvaluacionDAO(IEvaluacionDAO evaluacionDAO) {
		this.evaluacionDAO = evaluacionDAO;
	}


	/** 
	 * @see com.evaluacionlinea.interfaces.IExamen#findCuestionariobyId(java.lang.Integer)
	 */
	@Override
	public CuestionarioVO findCuestionariobyId(Integer id) {
		return cuestionarioDao.findById(id);
	}


	@Override
	public void saveCuesRes(List<ContestoVO> cont) {
		for (ContestoVO contesto : cont) {
			cuestionarioDao.save((Contesto) ConversionServices.transformVoToPojo(contesto));
		}
		
	}

	@Override
	public List<ContestoVO> findContestoCueUser(Integer usuId, Integer evaId, Integer tcue) {
		return cuestionarioDao.findContestoCueUser(usuId, evaId, tcue);
	}

	@Override
	public Integer getTimeEvaluacion(Integer cueId) {
		Object[] list = cuestionarioDao.getTimeCurso(cueId);
		Integer seg = 0;
		if (list != null) {
			Integer horas = (Integer) list[0];
			Integer minutos = (Integer) list[1];
			if(horas != null) {
				seg = (horas * 60)*60;
			}
			seg += minutos* 60;
			
		}
		return seg;
	}

	@Override
	public EvaCursoVO findEvaCurso(Integer evaId, Integer usuId) {
		return cuestionarioDao.findEvaCurso(evaId, usuId);
	}

	@Override
	public int saveEvacurso(EvaCursoVO evacursoVO) {
		EvaCurso evacurso = (EvaCurso) ConversionServices.transformVoToPojo(evacursoVO);
		return (Integer) cuestionarioDao.save(evacurso);
	}

	@Override
	public void updateEvacurso(EvaCursoVO evaCursoVO) {
		EvaCurso evaCurso = (EvaCurso) ConversionServices.transformVoToPojo(evaCursoVO);
		//cuestionarioDao.update(evaCurso);
	}

	@Override
	public ResultadoVO findUsuResultado(Integer usuId, Integer evaId) {
		return cuestionarioDao.findResultadoByUsuario(usuId, evaId);
	}

	@Override
	public void saveResultado(Resultado result) {
		cuestionarioDao.save(result);
	}

	@Override
	public EvaluacionVO findEvaByUsuario(Integer evaId) {
		return evaluacionDAO.getEvaByUserAndEva(evaId);
	}

	@Override
	public ResultadoVO getResultadoEvaluacion(Integer evaId, Integer usuId) {
		return evaluacionDAO.getResultadoEvaluacion(evaId, usuId);
	}
}
