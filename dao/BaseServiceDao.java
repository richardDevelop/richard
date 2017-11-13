package com.evaluacionlinea.dao;

import java.io.Serializable;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.evaluacionlinea.interfaces.IBaseServiceDAO;

/**
 * Clase que define los m&eac utetodos b&aacutesicos de la operaci&oacute de los DAO de Hibernate.
 * Concretamente, las operaciones de <code>save</code>, <code>update</code> y <code>delete</code>
 *
 * @author Adolfo Ruiz (aruiz)
 * @author Waldo Terry  (wterry)
 *
 */
public class BaseServiceDao  extends HibernateDaoSupport implements IBaseServiceDAO {

    /**
     * @see com.intercam.dao.interfaces.IBaseServiceDAO#store(java.lang.Object)
     */
    @Override
    public Serializable store(Object object) {
        try {
            return getHibernateTemplate().save(object);
        } catch (DataIntegrityViolationException  e) {
            throw  getConstraintException(e.getCause());
        }
    }

    /**
     * @see com.intercam.dao.interfaces.IBaseServiceDAO#update(java.lang.Object)
     */
    @Override
    public void update(Object object) {
        try {
            getHibernateTemplate().update(object);
        } catch (DataIntegrityViolationException  e) {
            throw  getConstraintException(e.getCause());
        }
    }

    /**
     * @see com.intercam.dao.interfaces.IBaseServiceDAO#delete(java.lang.Object)
     */
    @Override
    public void delete(Object object) {
        try {
            getHibernateTemplate().delete(object);
        } catch (DataIntegrityViolationException  e) {
            throw  getConstraintException(e.getCause());
        }
    }

    /**
     * @see com.intercam.dao.interfaces.IBaseServiceDAO#saveOrUpdate(java.lang.Object)
     */
    @Override
    public void saveOrUpdate(Object object) {
        try {
            getHibernateTemplate().saveOrUpdate(object);
        } catch (DataIntegrityViolationException  e) {
            throw  getConstraintException(e.getCause());
        }
    }

    /**
     *
     * @see com.intercam.dao.interfaces.IBaseServiceDAO#store(java.lang.Object[])
     */
    public Serializable store(Object... lstObjects) {
        return this.store(lstObjects[0]);
    }

    /**
     * @see com.intercam.dao.interfaces.IBaseServiceDAO#update(java.lang.Object[])
     */
    public void update(Object... lstObjects) {
        this.update(lstObjects[0]);
    }


    /**
     * @see com.intercam.dao.interfaces.IBaseServiceDAO#delete(java.lang.Object[])
     */
    public void delete(Object... lstObjects) {
        this.delete(lstObjects[0]);
    }

    /**
     * @see com.intercam.dao.interfaces.IBaseServiceDAO#saveOrUpdate(java.lang.Object[])
     */
    public void saveOrUpdate(Object... lstObjects) {
        this.saveOrUpdate(lstObjects[0]);
    }

    /**
     *
     * @param cause     Causa de la excepcion
     * @return la exception traducida a regresar
     */
    private RuntimeException getConstraintException(Throwable cause) {
        RuntimeException result = new RuntimeException(cause);
        if (cause instanceof ConstraintViolationException) {
            result = new DataIntegrityViolationException(
                    "Constraint : " + ((ConstraintViolationException) cause).getConstraintName(),
                    cause.getCause());
        }
        return result;
    }


}
