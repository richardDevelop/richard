package com.evaluacionlinea.dao;
// default package

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.evaluacionlinea.ConversionServices;
import com.evaluacionlinea.interfaces.IPersonaDAO;
import com.evaluacionlinea.model.Nivel;
import com.evaluacionlinea.model.Perfil;
import com.evaluacionlinea.model.Persona;
import com.evaluacionlinea.model.Users;
import com.evaluacionlinea.model.UsuArea;
import com.evaluacionlinea.model.UsuPerfil;
import com.evaluacionlinea.model.pojo.UsuEvaluacion;
import com.evaluacionlinea.vo.PersonaVO;
import com.evaluacionlinea.vo.UsuAreaVO;
import com.evaluacionlinea.vo.UsuPerfilVO;

/**
 	* A data access object (DAO) providing persistence and search support for Persona entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see .Persona
  * @author MyEclipse Persistence Tools 
 */
@Repository
@Component
public class PersonaDAO extends BaseServiceDao implements IPersonaDAO  {
	     private static final Logger log = LoggerFactory.getLogger(PersonaDAO.class);
	
	
    /**
     * 
     * @param persona
     * @return
     */
	@Transactional
    public Integer save(Persona persona) {
        log.debug("saving Persona instance");
        Integer perId=null;
        try {
        	perId =(Integer)getHibernateTemplate().save(persona);
        } catch (RuntimeException re) {
            log.error("save failed", re);
            //session.getTransaction().rollback();
            throw re;
        }
        return perId;
    }
    
   
    
    /**
     * Se valida si el usuario tiene permisos de acceso al sistema
     * @param usuario
     * @param password
     * @return
     */
    @SuppressWarnings("unchecked")
    @Transactional
	public PersonaVO getPersonaByLogin(final String usuario, final String password) {
		log.debug("getting Persona instance with Login: " + usuario);
		PersonaVO personaVO;
		try {			
			Persona persona = (Persona)  getHibernateTemplate().execute(
	                new HibernateCallback() {
	                    public Object doInHibernate(Session s)throws HibernateException, SQLException {
	                        return s.createCriteria(Persona.class, "per").createAlias("per.users","users")
	            			        .add(Restrictions.eq("users.usuario", usuario))
	            			        .add(Restrictions.eq("users.password", password))
	            			        .uniqueResult();
	                    }
	                });
		   personaVO = (PersonaVO)ConversionServices.transformPojoToVo(persona);
	      } catch (RuntimeException re) {
	         log.error("Error al consultar la persona Login", re);
	         throw re;
	      }
		return personaVO;
	}    
    
    @Transactional
    @SuppressWarnings("unchecked")
	public List<PersonaVO> getPersonaByAreaId(Integer areaId) {
		log.debug("consulta de usuarios por el area: " + areaId);
		List<PersonaVO> lstVO;
		final String sql = "{alias}.usu_id in (select usu_id from usuarea where are_id =" + areaId + ")";
		
		try {
			List<Persona> lst= (List<Persona>)  getHibernateTemplate().execute(
	                new HibernateCallback() {
	                    public Object doInHibernate(Session s)throws HibernateException, SQLException {
	                        return s.createCriteria(Persona.class).
	         			           add(Restrictions.sqlRestriction(sql)).list();
	                    }
	                });
					
					
					
			
			lstVO = new ArrayList<PersonaVO>();
			for (Persona persona : lst) {
				lstVO.add((PersonaVO)ConversionServices.transformPojoToVo(persona));
			}
			
		} catch (RuntimeException e) {
			log.error("Error al consulta de usuarios por area", e);
	         throw e;
		}
		return lstVO;
	}
    
    @SuppressWarnings("unchecked")
	@Transactional
    public Nivel getNivelById(final Integer nivId) {
    	try{
    		return (Nivel) getHibernateTemplate().execute(
	                new HibernateCallback() {
	                    public Object doInHibernate(Session s)throws HibernateException, SQLException {
	                        return s.createCriteria(Nivel.class)
	                				.add(Restrictions.eq("nivId", nivId)).uniqueResult();
	                    }
	                });
    				
    		
    	}catch (RuntimeException re) {
	         log.error("Error al consultar el nivel", re);
	         throw re;
    	}
    }
    
    @Transactional
    public void saveOrUpdate(Persona persona) {
        log.debug("saveOrUpdate Persona");
        try {   		
        	getHibernateTemplate().saveOrUpdate(persona);
        } catch (RuntimeException re) {
            log.error("Error al ejecutar saveOrUpdate sobre persona", re);
            //transaction.rollback();
            throw re;
        }
    }
    
    /**
     * Metodo mediante el cual se consulta la persona por email.
     * @param email
     * @return Persona
     */
    @Transactional
    public Persona getPersonaByEmail(final String  email) {
		log.debug("Obtener Persona por email: " + email);
		try {			
			@SuppressWarnings("unchecked")
			Persona persona = (Persona) getHibernateTemplate().execute(
	                new HibernateCallback() {
	                    public Object doInHibernate(Session s)throws HibernateException, SQLException {
	                        return s.createCriteria(Persona.class, "per").createAlias("per.users","users")
	            			        .add(Restrictions.eq("per.email", email))
	            			        .uniqueResult();
	                    }
	                });
					
			return persona;
	      } catch (RuntimeException re) {
	         log.error("Error al consultar la persona por email", re);
	         throw re;
	      }
	}
    
    /**
     * Metodo mediante el cual se obtienen los perfiles del usuario
     * @param usuId
     * @return
     */
    @Transactional
    public List<UsuPerfilVO> getPerfilPersonaByUsuId(Integer usuId){
    	log.debug("obtener los perfiles del usuId: " + usuId);
		List<UsuPerfilVO> lstVO;
		final String sql = "from UsuPerfil where usu_id =" + usuId  + " ORDER BY perf_id DESC";
		
		try {
			@SuppressWarnings("unchecked")
			List<UsuPerfil> lst= (List<UsuPerfil>) getHibernateTemplate().execute(
	                new HibernateCallback() {
	                    public Object doInHibernate(Session s)throws HibernateException, SQLException {
	                        return s.createQuery(sql).list();
	                    }
	                });
					
			lstVO = new ArrayList<UsuPerfilVO>();
			for (UsuPerfil usuPerfil : lst) {
				UsuPerfilVO usuPerfilVO = new UsuPerfilVO();
				usuPerfilVO = (UsuPerfilVO)ConversionServices.transformPojoToVo(usuPerfil);
				lstVO.add(usuPerfilVO);
			}
			
		} catch (RuntimeException e) {
			log.error("Error al consultar los perfiles del usuario", e);
	         throw e;
        }
		return lstVO;
    }
    
    @Transactional
    @SuppressWarnings("unchecked")
	public List<PersonaVO> getUsuByEvaId(final Integer evaId) {
    	try {
    		List<Persona> lstPerson = (List<Persona>)getHibernateTemplate().execute(
	                new HibernateCallback() {
	                    public Object doInHibernate(Session s)throws HibernateException, SQLException {
	                    	DetachedCriteria subQueryAreId = DetachedCriteria.forClass(UsuEvaluacion.class, "evalId")
	                				.add(Restrictions.eq("evalId.id.evaluacion.evaId", evaId))
	                				.setProjection(Projections.property("evalId.id.users.usuId"));
	                        return s.createCriteria(Persona.class, "pers")
	                				.add(Subqueries.propertyIn("pers.users.usuId", subQueryAreId)).list(); 
	                    }
	                });
    		
    		List<PersonaVO> lstPersonaVO = new ArrayList<PersonaVO>();
    		for(Persona per : lstPerson){
    			lstPersonaVO.add((PersonaVO) ConversionServices.transformPojoToVo(per));
    		}
    		
    		return lstPersonaVO;
			
		} catch (RuntimeException e) {
			log.error("Error al consultar las evaluaciones del usuario", e);
	         throw e;
		}
    }
    
    /**
     * Metodo que ejecuta el stored procedure que complementa la carga misiva de usuarios 
     * @param areId
     * @param evaId
     */
    @SuppressWarnings("unchecked")
	@Transactional
    public void ejecutaSP(final String usuH, final String pass, final String nombreEva, 
    		final String apePa, final String apeMa, final String numEmploy,final Integer areId,
   		final String oficce, final String region, final String usuUsuario, final Integer evaId) {
    	try {
			 getHibernateTemplate().execute(
	                new HibernateCallback() {
	                    public Object doInHibernate(Session s)throws HibernateException, SQLException {
	                    	Query query = s.getNamedQuery("insertUsers");
	        			return query.setParameter("usuarioH", usuH)
	   				     .setParameter("pass", pass)
	   				     .setParameter("nombreUs", nombreEva)
	   				     .setParameter("apePa", apePa)
	   				     .setParameter("apeMa", apeMa)
	   				     .setParameter("numEmploy", numEmploy)
	   				     .setParameter("areId", areId)
	   				     .setParameter("oficinaUs", oficce)
	   				     .setParameter("regionUs", region)
	   				     .setParameter("usuUsuario", usuUsuario)
	   				     .setParameter("evaId", evaId)
	   				     .executeUpdate();
	                    }
	                });
    		
				
		} catch (RuntimeException e) {
			log.error("Error ejecutar stored procedure spCompletaUsuarios", e);
	         throw e;
		}
    }
    
    @Transactional
    public Users save(Users users) {
        log.debug("saving Usuario instance");
        try {
        	 getHibernateTemplate().save(users);
        } catch (RuntimeException re) {
        	//session.getTransaction().rollback();
            log.error("save failed", re);
            throw re;
        } 
        return users;
    }
    
    @SuppressWarnings("unchecked")
	@Transactional
    public List findAll() {
		log.debug("finding all Users instances");
		try {
			final String queryString = "from Users";
	         Query queryObject =  getHibernateTemplate().execute(
		                new HibernateCallback() {
		                    public Object doInHibernate(Session s)throws HibernateException, SQLException {
		                        return s.createQuery(queryString);
		                    }
		                });
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    @Transactional
	 public void saveOrUpdate(Users usuario) {
	        log.debug("saveOrUpdate Users");
	        try {	        	
            	getHibernateTemplate().saveOrUpdate(usuario);
	            log.debug("saveOrUpdate successful");
	        } catch (RuntimeException re) {
	            log.error("Error al ejecutar saveOrUpdate sobre users", re);
	            //transaction.rollback();
	            throw re;
	        } 
	    }
	 
    @Transactional
	    public void save(UsuArea transientInstance) {
	        log.debug("saving UsuArea instance");        
	        try {   		
	        	getHibernateTemplate().save(transientInstance);
	        	log.debug("save usuAera successful");
	        } catch (RuntimeException re) {
	            log.error("save failed", re);
	            //transaction.rollback();
	            throw re;
	        }
	    }
	    

	    
	        
	    /**
	     * Metodo mediante el cual se obtienen las areas asociadas a un usuario
	     * @param usuId
	     * @return List<UsuAreaVO>
	     */
    @Transactional
    public List<UsuAreaVO> getAreasByUsuId(Integer usuId) {
    	log.debug("obtener las areas por usuario: " + usuId);
    	
    	List<UsuAreaVO> lstVO;
		final String sql = "from UsuArea where usu_id =" + usuId ;
		
		try {
			//transaction = session.beginTransaction();
			@SuppressWarnings("rawtypes")
			List<UsuArea> lst= (List<UsuArea>) getHibernateTemplate().execute(
	                new HibernateCallback() {
	                    public Object doInHibernate(Session s)throws HibernateException, SQLException {
	                        return s.createQuery(sql).list();
	                    }
	                });
			lstVO = new ArrayList<UsuAreaVO>();
			for (UsuArea usuArea : lst) {
				UsuAreaVO usuAreaVO = new UsuAreaVO();
				usuAreaVO = (UsuAreaVO) ConversionServices.transformPojoToVo(usuArea);
				lstVO.add(usuAreaVO);
			}
			//transaction.commit();
		} catch (RuntimeException e) {
			log.error("Error al consulta de usuarios por area", e);
	         throw e;
		}
		return lstVO;
	}
    
	    /**
	     * Metodo mediante el cual se eliminan las areas asociadas a un usuario
	     * @param usuId
	     */
    	@Transactional
	    public void deleteByUsuId(final Integer usuId) {
	    	log.debug("Eliminar las areas asociadas a el usuId: " + usuId);
	    	final String sql = "DELETE From UsuArea where usu_id = :usuId";
	    	try {    		    		
	    		//transaction = session.beginTransaction();
	    		 getHibernateTemplate().execute(
	 	                new HibernateCallback() {
	 	                    public Object doInHibernate(Session s)throws HibernateException, SQLException {
	 	                        return s.createQuery(sql).
	 	                   			setParameter("usuId", usuId).			
	 	               			executeUpdate();
	 	                    }
	 	                });
				//transaction.commit();
				log.debug("deleteByUsuId successful");
			} catch (RuntimeException e) {
				//transaction.rollback();
				log.error("Error al eliminar la relacion usuId vs areId", e);
		        throw e;
			}
	    }  
	    
	    /**
	     * Metodo mediante el cual se eliminan y se insertan las areas relacionados al usuario
	     * @param lstUsuArea
	     * @param users
	     */
    	@Transactional
	    public void eliminaGuarda(List<UsuArea> lstUsuAreaNueva, final Users users) {
	    	log.debug("eliminaGuarda UsuArea instance");
	        try {
	        	
	        	
	        	//Obtenemos la lista de areas relacionasdas al usuario, antes de hacer cualquir accion
	        	List<UsuAreaVO> lstUsuAreVO = getAreasByUsuId(users.getUsuId());
	        	//Variable que va almancenar las areas que se van a insertar
	        	List<UsuArea> lstUsuAreAux = null;
	        	
	        	//Determina si el area seleccionada ya existe en la relacion de usuario vs areas
	        	Boolean existe;
	        	//recorremos la lista de areas seleccionadas
	        	for (UsuArea usuAreaNueva : lstUsuAreaNueva) {
	        		//incializamos la variable especificando que no existe
	        		existe = false;
	        		//recorremos la lista de areas que actualmente tiene el usuario, para comparar cuales coinciden con las seleccionas
	        		for (UsuAreaVO usuAreaVO : lstUsuAreVO) {
	        			//Se valida el area seleccionada vs el area existente
						if (usuAreaNueva.getId().getArea().getAreId() == usuAreaVO.getIdVO().getAreaVO().getAreId()) {
							//se cambia el valor a existe
							existe = true;
							//se remueve esa area que ya existe de las areas ya existente del usuario
							lstUsuAreVO.remove(usuAreaVO);
							//se rompe el ciclo para comprar otra area seleccionada
							break;
						}
					}
	        		//se falida si el area seleccionada exiete
	        		if (!existe) {
	        			//si la lista de areas a inserta es null, se incializa.
	        			if (null == lstUsuAreAux) {
							lstUsuAreAux = new ArrayList<UsuArea>();
						}
	        			//al area seleccionada que no existe se le asigna el usuario
	        			usuAreaNueva.getId().setUsers(users);
	        			//el area seleccionada que no existe se almancena en la lista de areas a insertar
	        			lstUsuAreAux.add(usuAreaNueva);
					}
				}
	        	
	        	//Se valida si la lista de areas existente del usuario, tiene elementos que ya no coinciden con las seleccionadas, esto para eliminarlas
	        	if (lstUsuAreVO.size() > 0) {
	        		//Objeto que almacenará los ids de las areas que ya no tendran relacion con el usuario
	        		Object[] obj = new Object[lstUsuAreVO.size()];
	        		//se recorre la lista para almacener los ids en el objeto anterior
	                for (int i = 0; i < lstUsuAreVO.size(); i++) {
	                    obj[i] = lstUsuAreVO.get(i).getIdVO().getAreaVO().getAreId();
	                }
	                final Object[] obj1 = obj;
	                //Se ejecuta la sentencia para eliminar aquellas areas que ya no tendran relacion con el usuario
	        		final String sql = "DELETE From UsuArea where usu_id = :usuId and are_id in (:areId)";
	        		getHibernateTemplate().execute(
	    	                new HibernateCallback() {
	    	                    public Object doInHibernate(Session s)throws HibernateException, SQLException {
	    	                        return s.createQuery(sql).setInteger("usuId", users.getUsuId()).setParameterList("areId", obj1).executeUpdate();
	    	                    }
	    	                });
				}
	        	
	        	//se valida que existan areas nuevas a insertar
	        	if (null != lstUsuAreAux) {
		        	log.debug("saving UsuArea instance");
		        	//se crea la sentencia
		        	final String sql2 = "INSERT INTO usuarea(usu_id, are_id) values(:usuId, :areId)";
		        	//se recorre la lista de las areas nuevas, para insertarlas
		        	for (final UsuArea usuArea : lstUsuAreAux) {
		        		getHibernateTemplate().execute(
		    	                new HibernateCallback() {
		    	                    public Object doInHibernate(Session s)throws HibernateException, SQLException {
		    	                        return s.createSQLQuery(sql2)
		    	            	        		.setInteger("usuId", usuArea.getId().getUsers().getUsuId())
		    	            	        		.setInteger("areId", usuArea.getId().getArea().getAreId())
		    	            	        		.executeUpdate();
		    	                    }
		    	                });
					}
	        	}
	        	
	        
	            log.debug("eliminaGuarda UsuArea successful");
	        } catch (RuntimeException re) {
	            log.error("eliminaGuarda UsuArea failed", re);
	           
	            throw re;
	        }
	    }
	    
	    
	    /**
	     * Metodo mediante el cual se obtiene el catalogo de Perfiles
	     * @return List<Perfil>
	     */
    	@Transactional
	    public List<Perfil> getAll() {
	    	List<Perfil> lstPerfil = null;
	    	final String sql = "from Perfil";
	    	try {
	    		lstPerfil = getHibernateTemplate().execute(
    	                new HibernateCallback() {
    	                    public Object doInHibernate(Session s)throws HibernateException, SQLException {
    	                        return s.createQuery(sql).list();
    	                        
    	                    }
    	                });
	    				
			} catch (Exception e) {
				 log.error("Al consultar el catalogo de perfiles", e);
		        
			}
	    	return lstPerfil;
	    }
    	
    	@Transactional
	    public void save(UsuPerfil transientInstance) {
	        log.debug("saving UsuPerfil instance");
	        try {    		   		
	          
	        	getHibernateTemplate().save(transientInstance);
	        	log.debug("save usuPerfil successful");
	        } catch (RuntimeException re) {
	            log.error("save failed", re);
	            //transaction.rollback();
	            throw re;
	        }
	    }
	    
	    /**
	     * Metodo mediante el cual se obtienen los perfiles asociadas a un usuario
	     * @param usuId
	     * @return List<UsuPerfil>
	     */
    	@Transactional
	    public List<UsuPerfilVO> getPerfilByUsuId(Integer usuId) {    	
	    	log.debug("obtener los perfiles por usuario: " + usuId);
	    	
	    	List<UsuPerfilVO> lstVO;
			final String sql = "from UsuPerfil where usu_id =" + usuId ;
			
			try {
				//transaction = session.beginTransaction();
				List<UsuPerfil> lst= (List<UsuPerfil>) getHibernateTemplate().execute(
    	                new HibernateCallback() {
    	                    public Object doInHibernate(Session s)throws HibernateException, SQLException {
    	                        return s.createQuery(sql).list();
    	                        
    	                    }
    	                });
				lstVO = new ArrayList<UsuPerfilVO>();
				for (UsuPerfil usuPerfil : lst) {
					UsuPerfilVO usuPerfilVO = (UsuPerfilVO)ConversionServices.transformPojoToVo(usuPerfil);
					lstVO.add(usuPerfilVO);
				}
				//transaction.commit();
			} catch (RuntimeException e) {
				log.error("Error al consulta de usuarios por area", e);
		         throw e;
			}
			return lstVO;
		}
}