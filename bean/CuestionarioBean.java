package com.evaluacionlinea.bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.evaluacionlinea.ConversionServices;
import com.evaluacionlinea.dao.AreaDAO;
import com.evaluacionlinea.interfaces.IAreaDAO;
import com.evaluacionlinea.interfaces.ICuestionario;
import com.evaluacionlinea.interfaces.ICuestionarioDAO;
import com.evaluacionlinea.interfaces.IEvaluacionDAO;
import com.evaluacionlinea.model.AreCurso;
import com.evaluacionlinea.model.AreCursoId;
import com.evaluacionlinea.model.Area;
import com.evaluacionlinea.model.Cuestionario;
import com.evaluacionlinea.model.Curso;
import com.evaluacionlinea.model.Evaluacion;
import com.evaluacionlinea.model.Expositor;
import com.evaluacionlinea.model.MonitoreoEva;
import com.evaluacionlinea.model.Pregunta;
import com.evaluacionlinea.model.Respuesta;
import com.evaluacionlinea.utils.Const;
import com.evaluacionlinea.vo.CuestionarioVO;
import com.evaluacionlinea.vo.CursoVO;
import com.evaluacionlinea.vo.EvaluacionVO;
import com.evaluacionlinea.vo.ExpositorVO;
import com.evaluacionlinea.vo.PreguntaVO;

@Component("cuestionarioBean")
@ImportResource("classpath:ApplicationContex.xml")
public class CuestionarioBean implements ICuestionario{
	
	@Autowired
	private ICuestionarioDAO cuestionarioDAO;
	
	@Autowired
	private IEvaluacionDAO evaluacionDAO;
	
	@Autowired
	private IAreaDAO areaDao;
	
	/**
	 * @param cuestionarioDAO the cuestionarioDAO to set
	 */
	public void setCuestionarioDAO(ICuestionarioDAO cuestionarioDAO) {
		this.cuestionarioDAO = cuestionarioDAO;
	}


	/**
	 * @param evaluacionDAO the evaluacionDAO to set
	 */
	public void setEvaluacionDAO(IEvaluacionDAO evaluacionDAO) {
		this.evaluacionDAO = evaluacionDAO;
	}


	/**
	 * @param areaDao the areaDao to set
	 */
	public void setAreaDao(AreaDAO areaDao) {
		this.areaDao = areaDao;
	}


	@Override
	public List<CuestionarioVO> getLstCues(Integer areId) {
		List<CuestionarioVO> lstCues = null;
		try{
			lstCues = cuestionarioDAO.getCuestionarioByAreaId(areId);		}catch(Exception e){
			
		}
		
		return lstCues;
	}
	

	@Override
	public Integer saveCuestionario(Cuestionario cuestionario) {
		return (Integer) cuestionarioDAO.save(cuestionario);
	}
	
	
	@SuppressWarnings("deprecation")
	@Override
	public Integer saveEvaluacion(EvaluacionVO evaluacionVO) {
		Integer evaId = 0;
		try{
			Evaluacion evaluacion = new Evaluacion();
			evaluacionVO.setDuracionHora(evaluacionVO.getDuracion().getHours());
			evaluacionVO.setDuracionMin(evaluacionVO.getDuracion().getMinutes());
			Timestamp timeIni = getHourDate(evaluacionVO.getInFechAp(),evaluacionVO.getHrApIniDa());
			Timestamp timeFin = getHourDate(evaluacionVO.getFinFechAp(),evaluacionVO.getHrApFinDa());
			evaluacionVO.setHrIniAplicacion(timeIni);
			evaluacionVO.setHrFinAplicacion(timeFin);
			evaluacionVO.setFiniAplicacion(timeIni);
			evaluacionVO.setFfinAplicacion(timeFin);
			evaluacionVO.setFalta(timeIni);
			evaluacionVO.setFmodifica(timeIni);
			evaluacion = (Evaluacion)ConversionServices.transformVoToPojo(evaluacionVO);
			Cuestionario cue = new Cuestionario();
			cue.setCueId(evaluacionVO.getCuestionarioVO().getCueId());
			evaluacion.setCuestionario(cue);
			evaId = (Integer) evaluacionDAO.save(evaluacion);
			
		}catch(Exception e){
			e.getMessage();
		}
		return evaId;
		
	}
	
	@SuppressWarnings("deprecation")
	private Timestamp getHourDate(Date fech, Date hr){
		Timestamp timeHrIn = new Timestamp(fech.getYear(), fech.getMonth(), fech.getDate(), 
				hr.getHours(), hr.getMinutes(), hr.getSeconds(), 0);
		return timeHrIn;
	}

	@Override
	public Integer savePregunta(Pregunta pregunta) {
		return (Integer) cuestionarioDAO.save(pregunta);
	}

	@Override
	public void saveRespuesta(Respuesta respuesta) {
		evaluacionDAO.save(respuesta);
	}

	@Override
	public Cuestionario getCuestionarioById(Integer cueId) {
		return null;
	}

	@Override
	public List<PreguntaVO> getPreguntasByCueId(Integer cueId) {
		List<PreguntaVO> lstPre = cuestionarioDAO.getPreguntaByCueId(cueId);
		int order  = 1;
		for(PreguntaVO pre : lstPre){
			pre.setOrderPre(order);
			order++;
			
		}
		return lstPre;
	}

	@Override
	public void saveUpdatePregunta(Pregunta pregunta) {
		cuestionarioDAO.updatePreguntaById(pregunta);
	}

	@Override
	public void deletePregunta(Pregunta pregunta) {
		cuestionarioDAO.deletePregunta(pregunta);
		
	}

	@Override
	public void updateRespuesta(Respuesta respuesta) {
		evaluacionDAO.updateRespuestaById(respuesta);
	}

	@Override
	public void deleteRespuesta(Respuesta repuesta) {
		evaluacionDAO.deleteRespuestaById(repuesta);
		
	}

	public List<Evaluacion> getEvaluacionesByUsuId(Integer usuId) {
		return evaluacionDAO.getEvaluacionesByUsuId(usuId);
	}

	@Override
	public List<EvaluacionVO> getEvaluacionesForUser(String user) { 
		List<EvaluacionVO> lstEv = evaluacionDAO.getEvaluacionesForUser(user);
		for(EvaluacionVO eva : lstEv) {
			eva.setHrApFinDa(new Date(eva.getHrFinAplicacion().getTime()));
			eva.setHrApIniDa(new Date(eva.getHrIniAplicacion().getTime()));
			eva.setFinFechAp(new Date(eva.getFfinAplicacion().getTime()));
			eva.setInFechAp(new Date(eva.getFiniAplicacion().getTime()));
			eva.setDuracion(Const.builDateDuracion(eva.getDuracionHora(), 
					eva.getDuracionMin()));
			
		}
		return lstEv;
	}

	/**
	 * Metodo mediante el cual se monitorea las evaluaciones en curso
	 * @param evaId
	 * @return List<MonitoreoEva>
	 */
	public List<MonitoreoEva> getMonitoreoEva(Integer evaId) {
		return evaluacionDAO.getMonitoreoEva(evaId);
	}
	
	/**
	 * Metodo mediante el cual se obtienen las evaluaciones relacionadas a un area
	 * @param areId
	 * @return List<EvaluacionVO>
	 */
	 public List<EvaluacionVO> getEvaluacionesByAreId(Integer areId) {
		 return evaluacionDAO.getEvaluacionesByAreId(areId);
	 }
	 
		@SuppressWarnings("unchecked")
		@Override
		public List<SelectItem> getLstArea() {
			List<SelectItem> lstSel = new ArrayList<SelectItem>();
			try {
			List<Area> lstArea = areaDao.findAll();
			
			for(Area ar : lstArea){
					SelectItem sel = new SelectItem();
					sel.setLabel(ar.getNombre());
					sel.setValue(ar.getAreId());
					lstSel.add(sel);
		    }
			}catch (Exception e) {
				throw new RuntimeException("Error al consultar las Areas", e.getCause());
			}
			return lstSel;
		}

		@Override
		public void saveCurso(Curso curso) {
			try {
				Integer curId = (Integer) cuestionarioDAO.save(curso);
				AreCurso cursoId = new AreCurso();
				curso.setCurId(curId);
				Area ar = areaDao.findAreaByIdMio(new Integer(curso.getArea()));
			    cursoId.setId(new AreCursoId());
			    cursoId.getId().setArea(ar);
			    cursoId.getId().setCurso(curso);
			    areaDao.save(cursoId);
		} catch (Exception e) {
			throw new RuntimeException("Error al guardar el curso", e.getCause());
		}
			
		}
		

		@Override
		public List<CursoVO> findCursoByArea(Integer areId) {
			Area ar = areaDao.findAreaByIdMio(areId);
			List<CursoVO> lstCurso = cuestionarioDAO.findCursoByArea(areId);
			for(CursoVO curso : lstCurso){
				curso.setEstatus(curso.getActivo() ? "Activo" : "Inactivo");
				curso.setArea(ar.getNombre());
			}
			return lstCurso;
		}
		
		
		
		

		@Override
		public void updCurso(Curso curso) {
			try{
				cuestionarioDAO.updateCurso(curso);
				AreCurso cursoId = new AreCurso();
				 cursoId.setId(new AreCursoId());
				 Area ar =  areaDao.findAreaByIdMio(new Integer(curso.getArea()));
				 cursoId.getId().setArea(ar);
				 cursoId.getId().setCurso(curso);
				 areaDao.updateCurso(cursoId);
			}catch(Exception e){
				throw new RuntimeException("Error al actualizar el curso", e.getCause());
			}
			
		}

		@Override
		public List<ExpositorVO> getExpositorByCurId(Integer curId) {
			return cuestionarioDAO.getExpoByCurId(curId);
		}

		@Override
		public void saveExpositor(List<Expositor> lstExpoSave) {
           for(Expositor ex : lstExpoSave){
        	   cuestionarioDAO.save(ex);
           }
		}

		@Override
		public List<CuestionarioVO> getCuestionarioByTipo(Integer tipoCues) {
			return cuestionarioDAO.getCuestByTipo(tipoCues);
		}
		

}
