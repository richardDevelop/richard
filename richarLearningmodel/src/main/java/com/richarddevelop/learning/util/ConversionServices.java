package com.richarddevelop.learning.util;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

public class ConversionServices {
    
    /**
     * Mediante el uso de reflection, este m&eacutetodo permite obtener una instancia de un objeto de tipo <code>Value Object</code> a partir
     * de una instancia de su objeto persistente correspondiente
     * 
     * @param obj El objeto que se usa como base para obtener el <code>Value Object</code>, por ende, 
     * este objeto debe ser una instancia de un objeto persistente.
     * @return objDest Una instancia <b>en blanco</b> del <code>Value Object</code> que corresponde al objeto persistente que se envi&oacute como par&Aacutemetro.
     */
    public static Object instanceValueObject(Object obj){
        Object objDest = null;
        try {
            objDest = Class.forName(obj.getClass().getCanonicalName().replace(".model", ".vo").concat("VO")).newInstance();
        }
        catch (Exception e) {
			e.printStackTrace();
        	throw new RuntimeException("posible clase : " + obj + " no existente " + " o clase sin costructor default " );
        }
        return objDest;
    }
    
    /**
     * Copia de las propiedades entre objectos VO a Hibernate POJOs para operaciones de Hibernate. La copia solo se realiza entre las propiedades que comparten getters y setters con la misma firma.
     * 
     * @param objDestiny El objeto al que se copiar&aacuten las propiedades
     * @param objSource El objeto del que se obtienen los datos
     * @return objDestiny El mismo objeto que se envi&oacute como par&aacutemetro, con los valores de sus propiedades iguales a los del objeto origen
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static Object copyProperties(Object objDestiny, Object objSource) throws IllegalAccessException, InvocationTargetException {
        BeanUtils.copyProperties(objDestiny,objSource);
        return objDestiny;
    }
    
    /**
     * Mediante el uso de reflection, este m&eacutetodo permite obtener una instancia de un objeto de Hibernate a partir
     * de una instancia de su objeto <code>Value Object</code> correspondiente
     * 
     * @param obj El objeto que se usa como base para obtener el objeto de Hibernate, por ende, 
     * este objeto debe ser una instancia de un <code>Value Object</code> que corresponda al objeto persistente que se desea obtener.
     * @return objDest Una instancia <b>en blanco</b> del objeto de Hibernate que corresponde al <code>Value Object</code> que se envi&oacute como par&aacutemetro.
     */
    public static Object instanceObjectPojo(Object obj){
        Object objDest = null;
        try {
        	
            objDest = Class.forName(obj.getClass().getCanonicalName().replace(".vo", ".model").replace("VO", "")).newInstance();
        }
        catch (Exception e) {
			e.printStackTrace();
        	throw new RuntimeException("posible clase : " + obj + " no existente " + " o clase sin costructor default " );
        }
        return objDest;
    }
    
    /**
     * Este m&eacutetodo se encarga de obtener una instancia de objeto persistente de hibernate
     * a partir de la instancia de su value object correspondiente.
     * <p>
     * @param objVO la instancia de value object que se usar&aacutea para generar el pojo
     * @return Una instancia del pojo correspondiente al value object, con todos sus propiedades en <code>null</code>
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static Object transformVoToPojo(Object objVO){
	Object obj = null;
	if (objVO != null) {
	    Object hibernate = instanceObjectPojo(objVO);
	    try {
		obj = copyProperties(hibernate, objVO);
	    } catch (Exception e) {
		e.printStackTrace();
		throw new RuntimeException("Error convirtiendo  VO to pojo posible diferencia en algun get/set de clase  :   " + objVO);
	    } 
	}
	return obj;
    }
    
    /**
     * Este m&eacutetodo se encarga de obtener una instancia de value object a partir de su pojo de hibernate
     * correspondiente 
     * <p>
     * @param objPojo el pojo de hibernate que se usara como base para generar el value object
     * @return Una instancia del value object asociado con el pojo base, con los valores de sus propiedades en null
     * (o en su valor default correspondiente)
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static Object transformPojoToVo(Object objPojo){
    	Object copyProperties = null;
    	if(objPojo != null){
    		try {
    			copyProperties = copyProperties(instanceValueObject(objPojo), objPojo);
    		} catch (Exception e) {
    			e.printStackTrace();
    			throw new RuntimeException("Error convirtiendo pojo to VO  posible diferencia en algun get/set de clase : " + objPojo + "  :  transformPojoToVo ");
    		}
    	}
		return copyProperties;
    }

}
