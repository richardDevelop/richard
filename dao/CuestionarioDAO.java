package com.evaluacionlinea.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.evaluacionlinea.ConversionServices;
import com.evaluacionlinea.interfaces.ICuestionarioDAO;
import com.evaluacionlinea.model.AreCurso;
import com.evaluacionlinea.model.Contesto;
import com.evaluacionlinea.model.Cuestionario;
import com.evaluacionlinea.model.Curso;
import com.evaluacionlinea.model.EvaCurso;
import com.evaluacionlinea.model.Expositor;
import com.evaluacionlinea.model.Pregunta;
import com.evaluacionlinea.model.Resultado;
import com.evaluacionlinea.vo.ContestoVO;
import com.evaluacionlinea.vo.CuestionarioVO;
import com.evaluacionlinea.vo.CursoVO;
import com.evaluacionlinea.vo.EvaCursoVO;
import com.evaluacionlinea.vo.ExpositorVO;
import com.evaluacionlinea.vo.PreguntaVO;
import com.evaluacionlinea.vo.ResultadoVO;

/**
 	* A data access object (DAO) providing persistence and search support for Cuestionario entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see .Cuestionario
  * @author MyEclipse Persistence Tools 
 */
@Repository
public class CuestionarioDAO extends BaseServiceDao implements ICuestionarioDAO {

		private static final Logger log = LoggerFactory.getLogger(CuestionarioDAO.class);
	
    /**
     * 
     * @param transientInstance
     * @return
     */
    public Serializable save(final Object transientInstance) {
       return (Serializable) getHibernateTemplate().execute(
               new HibernateCallback() {
                   public Object doInHibernate(Session s)throws HibernateException, SQLException {
                       return s.save(transientInstance);
                   }
               });
    }
    
    
    /**
     * 
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
	public CuestionarioVO findById(final java.lang.Integer id) {
    	Cuestionario cuestionario = (Cuestionario) getHibernateTemplate().execute(
                new HibernateCallback() {
                    public Object doInHibernate(Session s)throws HibernateException, SQLException {
                        return s.createCriteria(Cuestionario.class)
                        		.add(Restrictions.eq("cueId", id))
                        		.uniqueResult();
                       
                    }
                });
    	CuestionarioVO cuestionarioVO = (CuestionarioVO) ConversionServices.transformPojoToVo(cuestionario);
    	return cuestionarioVO;
        
        
    }
    
    /**
     * 
     * @param areId
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<CuestionarioVO> getCuestionarioByAreaId(final Integer areId){
    	
    	return getHibernateTemplate().executeFind(new HibernateCallback() {
             public Object doInHibernate(Session session) throws HibernateException{
            	 DetachedCriteria subQueryAreId = DetachedCriteria.forClass(AreCurso.class, "areCu")
         				.add(Restrictions.eq("areCu.id.area.areId", areId))
         				.setProjection(Projections.property("areCu.id.curso.curId"));
         		Criteria cri = session.createCriteria(Cuestionario.class, "cuestionario")
         				.setProjection(Projections.projectionList()
         				.add(Projections.property("cuestionario.cueId").as("cueId"))
         				.add(Projections.property("cuestionario.nombre").as("nombre"))
         				.add(Projections.property("cuestionario.curso").as("curso"))
         			    .add(Projections.property("cuestionario.activo").as("activo")))
         				.add(Subqueries.propertyIn("cuestionario.curso.curId", subQueryAreId));
         		return cri.setResultTransformer(Transformers.aliasToBean(CuestionarioVO.class)).list();
             }
         });
    	 
    }
        
    
    /**
     * Retorna respuestas cuestionario Usuario
     * @param cueId
     * @param usuId
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<ContestoVO> findContestoCueUser(final Integer usuId, final Integer evaId, final Integer tcue) {
    	return getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException{
            	List<ContestoVO> listVO = new ArrayList<ContestoVO>();;
            	List<Contesto> listpojo =  session.createCriteria(Contesto.class)
    				.add(Restrictions.eq("usuId", usuId))
    				.add(Restrictions.eq("evaId", evaId))
    				.add(Restrictions.eq("tcue", tcue))
    				.list();
            	if(listpojo.size() > 0) {
        			for(Contesto contesto : listpojo) {
        				listVO.add((ContestoVO) ConversionServices.transformPojoToVo(contesto));
        			}
        		}
            	return listVO;
            }
        });
    }
    
    /**
     * 
     * @param vaId
     * @return
     */
    @SuppressWarnings("unchecked")
	public Object[] getTimeCurso(final Integer vaId) {
    	return getHibernateTemplate().execute(
                new HibernateCallback() {
                    public Object doInHibernate(Session s)throws HibernateException, SQLException {
                    	 Query query = s.createSQLQuery(
                				 "select e.duracionHora, e.duracionMin from evaluacion e where e.activo = true and e.eva_id = :evaId")
                				 .setParameter("evaId", vaId);
                		 return (Object[]) query.uniqueResult();
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
	public EvaCursoVO findEvaCurso(final Integer evaId, final Integer usuId) {
    	return getHibernateTemplate().execute(
                new HibernateCallback() {
                    public Object doInHibernate(Session s)throws HibernateException, SQLException {
                    	EvaCursoVO evacursoVO = null;
                    	EvaCurso evaCurso = (EvaCurso) s.createCriteria(EvaCurso.class)
             				.add(Restrictions.eq("usuId", usuId))
             				.add(Restrictions.eq("evaId", evaId))
             				.uniqueResult();
                    	if (evaCurso != null) {
             			evacursoVO = (EvaCursoVO) ConversionServices.transformPojoToVo(evaCurso);
             		}
             		return evacursoVO;
                    }
                });

    }
    
    /**
     * 
     * @param usuId
     * @param evaId
     * @return
     */
    @SuppressWarnings("unchecked")
	public ResultadoVO findResultadoByUsuario(final Integer usuId, final Integer evaId) {
    	return getHibernateTemplate().execute(
                new HibernateCallback() {
                    public Object doInHibernate(Session s)throws HibernateException, SQLException {
                    	ResultadoVO resultadoVO = null;
                    	Resultado resultado = (Resultado) s.createCriteria(Resultado.class)
		    			.add(Restrictions.eq("usuId", usuId))
		    			.add(Restrictions.eq("evaId", evaId))
		    			.uniqueResult();
						if (resultado != null) {
							resultadoVO = (ResultadoVO) ConversionServices.transformPojoToVo(resultado);
						}
						return resultadoVO;
                    }
                });
    }
    
    
    public Integer save(Curso transientInstance) {
    	return  (Integer) store(transientInstance);
    }

    
    @SuppressWarnings("unchecked")
	public List<CursoVO> findCursoByArea(final Integer areId){
    	return getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException{
            	List<Curso> lstCursos;
            	List<CursoVO> lstCursoVO = new ArrayList<CursoVO>();
            	DetachedCriteria subQuery = DetachedCriteria.forClass(AreCurso.class, "areCu")
        				.add(Restrictions.eq("areCu.id.area.areId", areId))
        				.setProjection(Projections.property("areCu.id.curso.curId"));
        		lstCursos = session.createCriteria(Curso.class, "curso")
        				.add(Subqueries.propertyIn("curso.curId", subQuery)).list();
        		
        		for(Curso curso : lstCursos){
        			lstCursoVO.add((CursoVO)ConversionServices.transformPojoToVo(curso));
        		}
        		return lstCursoVO;
            }
    	});
    
    		
    
		
   }
    
    @SuppressWarnings("unchecked")
	public void updateCurso(final Curso curso){
    	getHibernateTemplate().execute(
                new HibernateCallback() {
                    public Object doInHibernate(Session session)throws HibernateException, SQLException {
                    	String hqlVersionedUpdate = "update Curso set nombre = :nombre, activo = :activo where curId = :curId ";
                		int updatedEntities = session.createQuery( hqlVersionedUpdate )
                		        .setString( "nombre", curso.getNombre() )
                		        .setBoolean("activo", curso.getActivo())
                		        .setInteger("curId", curso.getCurId())
                		        .executeUpdate();
                		return null;
                    }
                });
    		

    }
    
    
    
    public Serializable save(Pregunta transientInstance) {
        return store(transientInstance);
    }
    
    
    @SuppressWarnings("unchecked")
	public List<PreguntaVO> getPreguntaByCueId(final Integer cueId){
    	return getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException{
            	List<PreguntaVO> lstPreguntaVO = new ArrayList<PreguntaVO>();
            	List<Pregunta> lstPregunta = session.createCriteria(Pregunta.class)
            			.add(Restrictions.eq("cueId", cueId))
            			.addOrder(Order.asc("preId"))
            			.list();
            	for(Pregunta pre : lstPregunta) {
            		lstPreguntaVO.add((PreguntaVO) ConversionServices.transformPojoToVo(pre));
            	}
            	return lstPreguntaVO;
            }
    	});

    }
    
    @SuppressWarnings("unchecked")
	public void updatePreguntaById(final Pregunta pregunta) {
    	getHibernateTemplate().execute(
                new HibernateCallback() {
                    public Object doInHibernate(Session session)throws HibernateException, SQLException {
                    	String hqlVersionedUpdate = "update Pregunta set pregunta = :nombre where preId = :preId ";
                    	getSession().createQuery( hqlVersionedUpdate )
                    	.setString( "nombre", pregunta.getPregunta() )
                    	.setInteger("preId", pregunta.getPreId())
                    	.executeUpdate();
                    	return null;
                    }
                });
    		
       
    }
    
    
    @SuppressWarnings("unchecked")
	public void deletePregunta(final Pregunta pregunta) {
    	getHibernateTemplate().execute(
                new HibernateCallback() {
                    public Object doInHibernate(Session session)throws HibernateException, SQLException {
                    	String hqlDeleteRes = "delete Respuesta where preId = :preId ";
                    	session.createQuery( hqlDeleteRes )
                    	.setInteger("preId", pregunta.getPreId())
                    	.executeUpdate();
                    	String hqlDeletePre = "delete Pregunta where preId = :preId ";
                    	getSession().createQuery( hqlDeletePre )
                    	.setInteger("preId", pregunta.getPreId())
                    	.executeUpdate();
                    	return null;
                    }
                });

    }
    
    
    @SuppressWarnings("unchecked")
	public List<ExpositorVO> getExpoByCurId(final Integer curId) {
    	return getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException{
            	List<ExpositorVO> lstExpo = new ArrayList<ExpositorVO>();
            	List<Expositor> lstExp = session.createCriteria(Expositor.class)
            			.add(Restrictions.eq("curso.curId", curId))
            			.list();
            	
            	for(Expositor ex : lstExp){
            		ExpositorVO expositor = (ExpositorVO)ConversionServices.transformPojoToVo(ex);
            	//	expositor.setCursoVO((CursoVO)ConversionServices.transformPojoToVo(ex.getCurso()));
            		lstExpo.add(expositor);
            	}
            	return lstExpo;
            }
    	});
    	
    }
    
    public void save(Expositor expositor){
    		store(expositor);
       
    }
    
    @SuppressWarnings("unchecked")
	public List<CuestionarioVO> getCuestByTipo(final Integer tipoCue){
    	return getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException{
            	Criteria cri = session.createCriteria(Cuestionario.class, "cuestionario")
            			.setProjection(Projections.projectionList()
            					.add(Projections.property("cuestionario.cueId").as("cueId"))
            					.add(Projections.property("cuestionario.nombre").as("nombre"))
            					.add(Projections.property("cuestionario.activo").as("activo")))
            					.add(Restrictions.eq("tipoCuestionario.tcueId", tipoCue));
            	return cri.setResultTransformer(Transformers.aliasToBean(CuestionarioVO.class)).list();
            	
            }
    	});
    }
    
}