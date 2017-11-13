package com.evaluacionlinea.model;

import com.evaluacionlinea.ConversionServices;
import com.evaluacionlinea.vo.PerfilVO;
import com.evaluacionlinea.vo.UsersVO;
import com.evaluacionlinea.vo.UsuPerfilIdVO;




/**
 * UsuPerfil entity. @author MyEclipse Persistence Tools
 */

public class UsuPerfil  implements java.io.Serializable {

	//SerialVersion
	private static final long serialVersionUID = 1L;
		

    // Fields    

     private UsuPerfilId id;


    // Constructors

    /** default constructor */
    public UsuPerfil() {
    }

    public UsuPerfil(Integer perfId) {
    	Perfil perfil = new Perfil();
    	perfil.setPerfId(perfId);
    	this.id = new UsuPerfilId();
    	this.id.setPerfil(perfil);
    }
    /*
     public UsuArea(Integer areaId) {
    	Area area = new Area();
    	area.setAreId(areaId);
    	this.id = new UsuAreaId();
    	this.id.setArea(area);
    }
     */
    
    /** full constructor */
    public UsuPerfil(UsuPerfilId id) {
        this.id = id;
    }

   
    // Property accessors

    public UsuPerfilId getId() {
        return this.id;
    }
    
    public void setId(UsuPerfilId id) {
        this.id = id;
    }
    
    public UsuPerfilIdVO getIdVO() {
    	UsuPerfilIdVO usuPerfilIdVO = new UsuPerfilIdVO();
    	usuPerfilIdVO.setPerfilVO((PerfilVO)ConversionServices.transformPojoToVo(this.id.getPerfil()));
    	usuPerfilIdVO.setUsersVO((UsersVO)ConversionServices.transformPojoToVo(this.id.getUsers()));
        return usuPerfilIdVO;
    }
}