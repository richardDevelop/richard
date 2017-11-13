package com.evaluacionlinea.dao;
// default package

import java.io.Serializable;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.evaluacionlinea.ConversionServices;
import com.evaluacionlinea.interfaces.IEvaluacionDAO;
import com.evaluacionlinea.model.Cuestionario;
import com.evaluacionlinea.model.EvaCurso;
import com.evaluacionlinea.model.Evaluacion;
import com.evaluacionlinea.model.MonitoreoEva;
import com.evaluacionlinea.model.Respuesta;
import com.evaluacionlinea.model.Resultado;
import com.evaluacionlinea.model.Cuestionario;
import com.evaluacionlinea.model.EvaCurso;
import com.evaluacionlinea.utils.Const;
import com.evaluacionlinea.vo.CuestionarioVO;
import com.evaluacionlinea.vo.EvaluacionVO;
import com.evaluacionlinea.vo.EvaluadosVO;
import com.evaluacionlinea.vo.ResultadoVO;

/**
 	* A data access object (DAO) providing persistence and search support for Evaluacion entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see .Evaluacion
  * @author MyEclipse Persistence Tools 
 */

public class EvaluacionDAO extends BaseServiceDao implements IEvaluacionDAO  {
	private static final Logger log = LoggerFactory.getLogger(EvaluacionDAO.class);

	/**
	 * 
	 * @param transientInstance
	 */
    public Serializable save(Evaluacion transientInstance) {
    	return (Serializable) store(transientInstance);
    }
    

    /**
     * Metodo mediante el cual se obtienen las evaluaciones a las que tiene acceso de acuerdo al día y la hora en que se esta logueando
     * @param usuId
     * @return List<Evaluacion>
     */
    @SuppressWarnings("unchecked")
	public List<Evaluacion> getEvaluacionesByUsuId(final Integer usuId) {
    	final String sql = "SELECT {eva.*},{cue.*}, res.res_id as contestada " +
    			" FROM evaluacion eva left join cuestionario cue on cue.cue_id = eva.cue_id "+ 
    			" left join resultado res on res.usu_id = :usuId " +
    			" where eva.fIniAplicacion <= :fechaHoraActual and eva. fFinAplicacion >= :fechaHoraActual " +
    			" and eva.activo = true and eva.eva_id in (SELECT eva_id FROM usuevaluacion where usu_id = :usuId and activo = true)";
    	return getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException{
            	List<Evaluacion> lstResult = null;
            	List<Object[]> rows  = (List<Object[]>)  session.createSQLQuery(sql)
            			.addEntity("eva",Evaluacion.class)
            			.addJoin("cue", "eva.cuestionario")
            			.addScalar("contestada")
            			.setParameter("fechaHoraActual", new Timestamp(new Date().getTime()))
            			.setInteger("usuId", usuId).list();
            	
            	lstResult = new ArrayList<Evaluacion>();
            	for (Object[] row : rows) {
            		Evaluacion eva = new Evaluacion();
            		eva = (Evaluacion) row[0];
            		if (null != row[1]) {
            			eva.setContestada(true);
            		} else {eva.setContestada(false);}
            		
            		lstResult.add(eva);
            	}
            	
            	return lstResult;
            }
    	});
    }
    
    @SuppressWarnings("unchecked")
	public List<EvaluacionVO> getEvaluacionesForUser(final String user){
    	return getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException{
            	Criteria cri = session.createCriteria(Evaluacion.class, "eva")
         				.setProjection(Projections.projectionList()
         				.add(Projections.property("eva.evaId").as("evaId"))
         				.add(Projections.property("eva.nombre").as("nombre"))
         				.add(Projections.property("eva.sede").as("sede"))
         				.add(Projections.property("eva.grupo").as("grupo"))
         			    .add(Projections.property("eva.activo").as("activo"))
         			    .add(Projections.property("eva.calApro").as("calApro"))
         			    .add(Projections.property("eva.finiAplicacion").as("finiAplicacion"))
         			    .add(Projections.property("eva.ffinAplicacion").as("ffinAplicacion"))
         			    .add(Projections.property("eva.hrIniAplicacion").as("hrIniAplicacion"))
         			    .add(Projections.property("eva.hrFinAplicacion").as("hrFinAplicacion"))
         			    .add(Projections.property("eva.duracionHora").as("duracionHora"))
         			    .add(Projections.property("eva.duracionMin").as("duracionMin"))
         			   .add(Projections.property("eva.showPDF").as("showPDF")))
         				.add(Restrictions.eq("eva.usuario", user.trim()));
            	return cri.setResultTransformer(Transformers.aliasToBean(EvaluacionVO.class)).list();
            		
			}
		});
    }
    
    @SuppressWarnings("unchecked")
	public EvaluacionVO getEvaByUserAndEva(final Integer evaId) {
    	final String sql = "SELECT {eva.*},{cue.*} FROM evaluacion eva left join cuestionario cue on cue.cue_id = eva.cue_id " +
    			" WHERE eva.eva_id = :evaId"; 
    	return getHibernateTemplate().execute(new HibernateCallback() {
    		 public Object doInHibernate(Session s)throws HibernateException, SQLException {
                	EvaluacionVO evaluacionVO;
                	Object[] rows  = (Object[])  s.createSQLQuery(sql)
                			.addEntity("eva",Evaluacion.class)
                			.addJoin("cue", "eva.cuestionario")
                			.setInteger("evaId", evaId).uniqueResult();
                	
                	evaluacionVO = (EvaluacionVO) ConversionServices.transformPojoToVo((Evaluacion) rows[0]);
                	evaluacionVO.setCuestionarioVO((CuestionarioVO) ConversionServices.transformPojoToVo((Cuestionario)rows[1]));
                	return evaluacionVO;
				}
			});
    }
    
    /**
     * Metodo mediante el cual se monitorea las evaluaciones en curso
     * @param evaId
     */
    @SuppressWarnings("unchecked")
	public List<MonitoreoEva> getMonitoreoEva(final Integer evaId) {
    	return getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException{
            	List<MonitoreoEva> lstResult = null;
            	Query query =  session.getNamedQuery("spMonitoreoEva");
            	
            	List<Object> result= (List<Object>)query.setParameter("evaId", evaId).list();
            	
            	lstResult = new ArrayList<MonitoreoEva>();
            	Iterator<Object> it = result.iterator();
            	while (it.hasNext()) {
            		Object[] obj = (Object[]) it.next();
            		MonitoreoEva mEva = new MonitoreoEva();
            		
            		mEva.setEvaId(Integer.parseInt(String.valueOf(obj[0])));
            		mEva.setNombre(String.valueOf(obj[1]));
            		mEva.setApePaterno(String.valueOf(obj[2]));
            		mEva.setApeMaterno(String.valueOf(obj[3]));
            		mEva.setNumEmpleado(String.valueOf(obj[4]));
            		mEva.setUsuario(String.valueOf(obj[5]));
            		mEva.setEnCurso(Boolean.parseBoolean(String.valueOf(obj[6])));
            		
            		//Se valida si el usuario esta realizando la evaluacion
            		if (mEva.getEnCurso()) {
            			mEva.setBloque(Integer.parseInt(String.valueOf(obj[7])));
            			if (null != obj[8]) {
            				mEva.setTiempoEnRealizar(Time.valueOf(String.valueOf(obj[8])));
            			}
            		}
            		
            		mEva.setEvaTermino(Boolean.parseBoolean(String.valueOf(obj[9])));
            		
            		//Se valida si el usuario ya terminó la evaluacion
            		if (mEva.getEvaTermino()) {
            			mEva.setCalificacion(Float.parseFloat(String.valueOf(obj[10])));
            			mEva.setCalAprobada(Boolean.parseBoolean(String.valueOf(obj[11])));
            		}
            		
            		mEva.setNivel(String.valueOf(obj[12]));
            		if (null != obj[13]) {
            			mEva.setPerId(Integer.parseInt(String.valueOf(obj[13])));
            		}				
            		
            		//asigna la imagen de acuerdo a si el usuario estarealizando la evaluacion
            		if (mEva.getEnCurso() && mEva.getEvaTermino()) {
            			mEva.setImgEnCurso("finalizo.png");
            		} else if(mEva.getEnCurso()) {
            			mEva.setImgEnCurso("enProceso.png");
            		} else {
            			mEva.setImgEnCurso("noIngreso.png");
            		}
            		
            		if (mEva.getEvaTermino()) {
            			if (mEva.getCalAprobada()) {
            				mEva.setImgEvaTermino("aprobo.png");
            			} else {
            				mEva.setImgEvaTermino("noAprobo.png");
            			}
            		} else {
            			mEva.setImgEvaTermino("sinImg.png");
            		}
            		
            		lstResult.add(mEva);
            		
            	}
            	return lstResult;
			}
		});
    	
    	
    }
    
    /**
     * Metodo mediante el cual se obtienen las evaluaciones relacionadas a un area
     * @param areId
     * @return List<Evaluacion>
     */
    @SuppressWarnings("unchecked")
	public List<EvaluacionVO> getEvaluacionesByAreId(final Integer areId) {
    	final String sql = "SELECT ev.eva_id, ev.nombre, ev.sede, ev.fIniAplicacion, ev.fFinAplicacion " +
    			",case when ev.fIniAplicacion <= :fechaHoraActual and ev. fFinAplicacion >= :fechaHoraActual then 'EN CURSO' " +    			    			
    			" 	   when :fechaHoraActual< ev.fIniAplicacion then 'PENDIENTE' " +
    			"      when :fechaHoraActual> ev. fFinAplicacion then 'FINALIZO' END as estatus " +
    			",ev.grupo, ev.duracionHora, ev.duracionMin " +    			
    			" FROM arecurso ac, curso cu, cuestionario ce, evaluacion ev " +
    			" where cu.cur_id = ac.cur_id and ce.cur_id = cu.cur_id and ev.cue_id = ce.cue_id "+ 
    			" and ac.are_id = :areId " +
//    			" and ev.fIniAplicacion <= :fechaHoraActual and ev. fFinAplicacion >= :fechaHoraActual " +
    			" and ev.activo = true";
    	
    	return getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException{
            	List<EvaluacionVO> lstResult = null;
            	List<Object> result= (List<Object>)  session.createSQLQuery(sql)
            			.setParameter("fechaHoraActual", new Timestamp(new Date().getTime()))
            			.setInteger("areId", areId).list();
            	
            	lstResult = new ArrayList<EvaluacionVO>();
            	Iterator<Object> it = result.iterator();
            	while (it.hasNext()) {
            		Object[] obj = (Object[]) it.next();
            		EvaluacionVO eva = new EvaluacionVO();
            		
            		eva.setEvaId(Integer.parseInt(String.valueOf(obj[0])));
            		eva.setNombre(String.valueOf(obj[1]));
            		eva.setSede(String.valueOf(obj[2]));
            		
            		if (null != obj[3]) {
            			eva.setFiniAplicacion(Timestamp.valueOf(String.valueOf(obj[3])));
            		}
            		
            		if (null != obj[4]) {
            			eva.setFfinAplicacion(Timestamp.valueOf(String.valueOf(obj[4])));
            		}
            		
            		eva.setEstatus(String.valueOf(obj[5]));
            		eva.setGrupo(String.valueOf(obj[6]));
            		eva.setDuracion(Const.builDateDuracion(
            				Integer.parseInt(String.valueOf(obj[7])), 
            				Integer.parseInt(String.valueOf(obj[8]))));
            		
            		lstResult.add(eva);
            	}
            	return lstResult;
			}
		});
    }
    
    /**
     * 
     * @param evaId
     * @param usuId
     * @return
     */
    @SuppressWarnings("unchecked")
	public ResultadoVO getResultadoEvaluacion (final Integer evaId, final Integer usuId) {
    	return getHibernateTemplate().execute(new HibernateCallback() {
   		 public Object doInHibernate(Session session)throws HibernateException, SQLException {
   			 ResultadoVO resultadoVO = new ResultadoVO();
   			 Resultado res = new Resultado(); 
   			 Query query = session.getNamedQuery("spGenResultEva");
   			 query.setParameter("usuId", usuId);
   			 query.setParameter("evaId", evaId);
   			 List<Object> result= (List<Object>) query.list();
   			 EvaCurso evaCurso= (EvaCurso) session.createCriteria(EvaCurso.class)
   					 .add(Restrictions.eq("usuId", usuId))
   					 .add(Restrictions.eq("evaId", evaId))
   					 .uniqueResult();
   			 
   			 EvaluacionVO evaVO = getEvaByUserAndEva(evaId);
   			 
   			 if(result.size() > 0){
   				 Object[] obj = (Object[]) result.get(0);
   				 
   				 Integer good = (Integer) obj[0]; 
   				 Integer totalPreg = (Integer) obj[1];
   				 Float cal = (float) (good*10);
   				 cal = cal / totalPreg;
   				 cal = Float.parseFloat((Const.Redondear(cal,1)).toString());
   				 
   				 res.setCalificacion(cal);
   				 res.setEvaId(evaId);
   				 res.setUsuId(usuId);
   				 
   				 if(evaCurso != null) {
   					 res.setHincio(evaCurso.getHinicio());
   					 res.setHfin(evaCurso.getHbloque());
   					 res.setCalAprobatoria(cal >= evaVO.getCalApro());
   				 }
   				 res.setResTotal(good);
   				 store(res);
   				 resultadoVO = (ResultadoVO) ConversionServices.transformPojoToVo(res);
   			 }
   			 return resultadoVO;
   				}
   			});
    }
    

    
    public void save(Respuesta transientInstance) {
         store(transientInstance);
    }
    
	public void delete(Respuesta persistentInstance) {
        delete(persistentInstance);
    }
    
    @SuppressWarnings("unchecked")
	public Respuesta findById(final java.lang.Integer id) {
    	return getHibernateTemplate().execute(new HibernateCallback() {
   		 public Object doInHibernate(Session s)throws HibernateException, SQLException {
   			 Respuesta instance = (Respuesta) s.get("Respuesta", id);
   			 return instance;
			}
		});
        
    }
    
    
    @SuppressWarnings("unchecked")
	public void updateRespuestaById(final Respuesta respuesta) {
    	getHibernateTemplate().execute(new HibernateCallback() {
   		 public Object doInHibernate(Session s)throws HibernateException, SQLException {
	   			 String hqlVersionedUpdate = "update Respuesta set respuesta = :nombre where respId = :respId ";
	   			  s.createQuery( hqlVersionedUpdate )
	   			 .setString( "nombre", respuesta.getRespuesta())
	   			 .setInteger("respId", respuesta.getRespId())
	   			 .executeUpdate();
	   			  return null;
   		 }
   			});
    }
    
    @SuppressWarnings("unchecked")
	public void deleteRespuestaById(final Respuesta respuesta) {
    	getHibernateTemplate().execute(new HibernateCallback() {
   		 public Object doInHibernate(Session s)throws HibernateException, SQLException {
			 String hqlVersionedUpdate = "delete Respuesta  where respId = :respId ";
			 s.createQuery( hqlVersionedUpdate )
			 .setInteger("respId", respuesta.getRespId())
			 .executeUpdate();
			 return null;
			}
		});
    }
    
    
}