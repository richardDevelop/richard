package com.evaluacionlinea.dao;
// default package

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.evaluacionlinea.ConversionServices;
import com.evaluacionlinea.interfaces.IUsuPerfilDAO;
import com.evaluacionlinea.model.Users;
import com.evaluacionlinea.model.UsuPerfil;
import com.evaluacionlinea.vo.UsuPerfilVO;

/**
 	* A data access object (DAO) providing persistence and search support for UsuPerfil entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see .UsuPerfil
  * @author MyEclipse Persistence Tools 
 */

public class UsuPerfilDAO extends BaseServiceDao implements IUsuPerfilDAO  {
	     private static final Logger log = LoggerFactory.getLogger(UsuPerfilDAO.class);
	

    public void save(UsuPerfil transientInstance) {
        	store(transientInstance);
    }
    
    
    /**
     * Metodo mediante el cual se eliminan los perfiles asociadas a un usuario
     * @param usuId
     */
    @SuppressWarnings("unchecked")
	public void deleteByUsuId(final Integer usuId) {
    	log.debug("Eliminar los perfiles asociadas a el usuId: " + usuId);    	
    	final String sql = "DELETE From UsuPerfil where usu_id = :usuId";
		getHibernateTemplate().execute(new HibernateCallback() {
			 public Object doInHibernate(Session s)throws HibernateException, SQLException {
					 s.createQuery(sql).
					 setParameter("usuId", usuId).
					 executeUpdate();
					 return null;
					}
				});
    }
    
    /**
     * Metodo mediante el cual se obtienen los perfiles asociadas a un usuario
     * @param usuId
     * @return List<UsuPerfil>
     */
    @SuppressWarnings("unchecked")
	public List<UsuPerfilVO> getPerfilByUsuId(final Integer usuId) {    	
    	log.debug("obtener los perfiles por usuario: " + usuId);
    	return getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException{
            	List<UsuPerfilVO> lstVO;
            	String sql = "from UsuPerfil where usu_id =" + usuId ;
            	List<UsuPerfil> lst= (List<UsuPerfil>) getSession().createQuery(sql).list();
            	lstVO = new ArrayList<UsuPerfilVO>();
            	for (UsuPerfil usuPerfil : lst) {
            		UsuPerfilVO usuPerfilVO = (UsuPerfilVO)ConversionServices.transformPojoToVo(usuPerfil);
            		lstVO.add(usuPerfilVO);
            	}
            	return lstVO;
			}
		});
	}
    
    
    /**
     * Metodo mediante el cual se eliminan y se insertan los perfiles relacionados al usuario
     * @param lstUsuPerfil
     * @param users
     */
    @SuppressWarnings("unchecked")
	public void eliminaGuarda(final List<UsuPerfil> lstUsuPerfilNuevos, final Users users) {
    	log.debug("eliminaGuarda UsuPerfil instance");
    	getHibernateTemplate().execute(new HibernateCallback() {
   		 public Object doInHibernate(Session session)throws HibernateException, SQLException {
   			 //Obtenemos la lista de perfiles relacionasdos al usuario, antes de hacer cualquir accion
   			 List<UsuPerfilVO> lstUsuPerfilVO = getPerfilByUsuId(users.getUsuId());
   			 //Variable que va almancenar los perfiles que se van a insertar
   			 List<UsuPerfil> lstUsuPerfilAux = null;
   			 
   			 //Determina si el area seleccionada ya existe en la relacion de usuario vs perfil
   			 Boolean existe;
   			 
   			 //recorremos la lista de perfiles seleccionadas
   			 for (UsuPerfil usuPerfilNuevo : lstUsuPerfilNuevos) {
   				 //incializamos la variable especificando que no existe
   				 existe = false;
   				 //recorremos la lista de perfiles que actualmente tiene el usuario, para comparar cuales coinciden con los seleccionados
   				 for (UsuPerfilVO usuPerfilVO : lstUsuPerfilVO) {
   					 //Se valida el perfil seleccionado vs el perfil existente
   					 if (usuPerfilNuevo.getId().getPerfil().getPerfId() == usuPerfilVO.getIdVO().getPerfilVO().getPerfId()) {
   						 //se cambia el valor a existe
   						 existe = true;
   						 //se remueve ese perfil que ya existe de los perfiles ya existente en usuario
   						 lstUsuPerfilVO.remove(usuPerfilVO);
   						 //se rompe el ciclo para comprar otra perfil seleccionada
   						 break;
   					 }
   				 }
   				 //se falida si el perfil seleccionado existe
   				 if (!existe) {
   					 //si la lista de perfiles a inserta es null, se incializa.
   					 if (null == lstUsuPerfilAux) {
   						 lstUsuPerfilAux = new ArrayList<UsuPerfil>();
   					 }
   					 //al perfil seleccionado que no existe se le asigna el usuario
   					 usuPerfilNuevo.getId().setUsers(users);
   					 //el perfil seleccionado que no existe se almancena en la lista de perfiles a insertar
   					 lstUsuPerfilAux.add(usuPerfilNuevo);
   				 }
   			 }
   			 
   			 //Se valida si la lista de areas existente del usuario, tiene elementos que ya no coinciden con las seleccionadas, esto para eliminarlas
   			 if (lstUsuPerfilVO.size() > 0) {
   				 //Objeto que almacenará los ids de los perfiles que ya no tendran relacion con el usuario
   				 Object[] obj = new Object[lstUsuPerfilVO.size()];
   				 //se recorre la lista para almacener los ids en el objeto anterior
   				 for (int i = 0; i < lstUsuPerfilVO.size(); i++) {
   					 obj[i] = lstUsuPerfilVO.get(i).getId().getPerfil().getPerfId();
   				 }
   				 //Se ejecuta la sentencia para eliminar aquellos perfiles que ya no tendran relacion con usuario
   				 String sql = "DELETE From UsuPerfil where usu_id = :usuId and perf_id in (:perfId)";
   				 session.createQuery(sql).setInteger("usuId", users.getUsuId()).setParameterList("perfId", obj).executeUpdate();
   			 }
   			 
   			 //se valida que existan perfiles nuevos a insertar
   			 if (null != lstUsuPerfilAux) {
   				 log.debug("saving UsuPerfil instance");
   				 //se crea la sentencia
   				 String sql2 = "INSERT INTO usuperfil(perf_id, usu_id) values(:perfId, :usuId)";
   				 //se recorre la lista de los perfiles nuevos, para insertarlos
   				 for (UsuPerfil usuPerfil : lstUsuPerfilAux) {
   					 session.createSQLQuery(sql2)
   					 .setInteger("perfId", usuPerfil.getId().getPerfil().getPerfId())
   					 .setInteger("usuId", usuPerfil.getId().getUsers().getUsuId())	        		
   					 .executeUpdate();
   				 }
   			 }
   			 return null;
			}
		});
        	
    }
}