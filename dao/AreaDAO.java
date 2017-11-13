package com.evaluacionlinea.dao;
// default package

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.evaluacionlinea.interfaces.IAreaDAO;
import com.evaluacionlinea.model.AreCurso;
import com.evaluacionlinea.model.Area;
import com.evaluacionlinea.model.Nivel;

/**
 	* A data access object (DAO) providing persistence and search support for Area entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see .Area
  * @author MyEclipse Persistence Tools 
 */

public class AreaDAO extends BaseServiceDao implements IAreaDAO {
	     private static final Logger log = LoggerFactory.getLogger(AreaDAO.class);
	
   
    
    public void save(Area transientInstance) {
        log.debug("saving Area instance");
        try {
        	//transaction = session.beginTransaction();
        	getHibernateTemplate().save(transientInstance);
        	//transaction.commit();
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	
    
   
    public List findAll() {
		log.debug("finding all Area instances");		
     	try {
			//transaction = session.beginTransaction();
			final String queryString = "from Area";
	        Query queryObject =  getHibernateTemplate().execute(
	                new HibernateCallback() {
	                    public Object doInHibernate(Session s)throws HibernateException, SQLException {
	                        return s.createQuery(queryString);
	                        
	                    }
	                });
	         //transaction.commit();
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
    
   
    /**
     * Metodo mediante el cual se obtiene el catalogo de Areas
     * @return List<Area>
     */
    public List<Area> getAll() {
    	List<Area> lstAreas = null;
    	
     	final String sql = "from Area";
    	try {
    		lstAreas =  getHibernateTemplate().execute(
	                new HibernateCallback() {
	                    public Object doInHibernate(Session s)throws HibernateException, SQLException {
	                        return s.createQuery(sql).list();
	                        
	                    }
	                });
    		//transaction.commit();
			
		} catch (Exception e) {
			 log.error("Al consultar el catalogo de areas", e);
	         //transaction.rollback();
		}
    	return lstAreas;
    }
    
    /**
     * Metodo mediante el cual se obtiene el catalogo de niveles
     * @return List<Nivel>
     */
    public List<Nivel> getAllNiveles() {
    	List<Nivel> lstNiveles = null;
    	
     	final String sql = "from Nivel";
    	try {
    		lstNiveles = getHibernateTemplate().execute(
	                new HibernateCallback() {
	                    public Object doInHibernate(Session s)throws HibernateException, SQLException {
	                        return s.createQuery(sql).list();
	                        
	                    }
	                });
    		//transaction.commit();
			
		} catch (Exception e) {
			 log.error("Al consultar el catalogo de niveles", e);
	         //transaction.rollback();
		}
    	return lstNiveles;
    }
    
    public Area findAreaByIdMio(final Integer areId){    	
    	Area area = null;
    	
     	try{
    		//transaction = session.beginTransaction();
    		area =  (Area) getHibernateTemplate().execute(
	                new HibernateCallback() {
	                    public Object doInHibernate(Session s)throws HibernateException, SQLException {
	                        return s.createCriteria(Area.class)
	                        		.add(Restrictions.eq("areId", areId)).uniqueResult();
	                        
	                    }
	                });
    				
    		
    		//transaction.commit();
    	}catch(Exception e){
    		log.error("attach failed", e);
	    }
		return area;    	
    }
    
    /**
     * Metodo mediante el cual se obtiene las areas que estan relacionadas al usuario
     * @return List<Area>
     */
    public List<Area> getByPerfil(Integer usuId) {
    	List<Area> lstAreas = null;
    	String sql = "select * from area where are_id in (select are_id from usuarea where usu_id = :usuId)";
    	try {
    		lstAreas = (List<Area>)getSession().createSQLQuery(sql)
    				.addEntity(Area.class)
    				.setInteger("usuId", usuId).list();
		} catch (Exception e) {
			 log.error("Al consultar el catalogo de areas", e);
		} 
    	return lstAreas;
    }
    


    

    
     public void updateCurso(final AreCurso curso){
    	try{
    		final String hqlVersionedUpdate = "update AreCurso set id.area.areId = :areId where id.curso.curId = :curId ";
    		getHibernateTemplate().execute(
	                new HibernateCallback() {
	                    public Object doInHibernate(Session s)throws HibernateException, SQLException {
	                        return s.createQuery( hqlVersionedUpdate )
	                				.setInteger("areId", curso.getId().getArea().getAreId())
	                		        .setInteger("curId", curso.getId().getCurso().getCurId())
	                		        .executeUpdate();
	                        
	                    }
	                });
    		
    	}catch(Exception re ){
    		log.error("attach failed", re);
           
    	}
    }
     
     public void save(AreCurso transientInstance) {
         log.debug("saving AreCurso instance");
         try {
         	getHibernateTemplate().save(transientInstance);
             log.debug("save successful");
         } catch (RuntimeException re) {
             log.error("save failed", re);
             throw re;
         }
     }
}