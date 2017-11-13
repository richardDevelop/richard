package com.evaluacionlinea.interfaces;

import java.io.Serializable;

public interface IBaseServiceDAO {


    /**
     * Guarda una instancia de objeto persistente en la base de datos.
     * 
     * @param object Objeto a guardar en la base de datos. Este objeto NO puede ser de tipo <code>Value Object</code>.
     * @return la llave que se genero para el objeto salvado
     */
    public Serializable store(Object object);

    /**
     * Actualiza el contenido de la informaci&oacuten de la base de datos con la de un objeto persistente.
     * 
     * @param object El objeto del que proviene la informaci&oacuten a actualizar en la base de datos. 
     *          Este objeto NO puede ser de tipo <code>Value Object</code>
     */
    public void update(Object object);

    /**
     * Permite eliminar un objeto persistente de la base de datos.
     * 
     * @param object Objeto persistente que se desea eliminar de la base de datos. 
     *          Este objeto NO puede ser de tipo <code>Value Object</code>
     */
    public void delete(Object object);

    /**
     * Si el objeto que se recibe como argumento no existe en la base de datos, este es almacenado. 
     * Si este ya existe, es actualizado
     * @param object Objeto persistente que se desea insertar o actualizar en la base de datos. 
     *          Este objeto NO puede ser de tipo <code>Value Object</code>
     */
    public void saveOrUpdate(Object object);

    /**
     * Guarda una instancia de objeto persistente en la base de datos.
     * 
     * @param lstObjects Lista de parametros, cuyo primer elemento es el objeto a guardar en la base de datos. 
     *          Este objeto NO puede ser de tipo <code>Value Object</code>.
     * @return la llave que se genero para el objeto salvado
     */
    public Serializable store(Object... lstObjects);

    /**
     * Actualiza el contenido de la informaci&oacuten de la base de datos con la de un objeto persistente.
     * 
     * @param lstObjects Lista de parametros, cuyo primer elemento es el objeto del que proviene la informaci&oacuten 
     *          a actualizar en la base de datos. Este objeto NO puede ser de tipo <code>Value Object</code>
     */
    public void update(Object... lstObjects);

    /**
     * Permite eliminar un objeto persistente de la base de datos.
     * 
     * @param lstObjects Lista de parametros, cuyo primer elemento es el objeto persistente que se desea eliminar 
     *          de la base de datos. Este objeto NO puede ser de tipo <code>Value Object</code>
     */
    public void delete(Object... lstObjects);

    /**
     * Insertar  Actualiza el contenido de la informaci&oacuten de la base de datos con la de un objeto persistente.
     * 
     * Si el objeto que se recibe como argumento no existe en la base de datos, este
     * es almacenado. Si este ya existe, es actualizado
     * @param lstObjects Lista de parametros, cuyo primer elemento es el objeto del que proviene la informaci&oacuten 
     *          a insertar o actualizar en la base de datos. Este objeto NO puede ser de tipo <code>Value Object</code>
     */
    public void saveOrUpdate(Object... lstObjects);
}
