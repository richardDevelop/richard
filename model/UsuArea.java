package com.evaluacionlinea.model;

import com.evaluacionlinea.ConversionServices;
import com.evaluacionlinea.vo.AreaVO;
import com.evaluacionlinea.vo.UsersVO;
import com.evaluacionlinea.vo.UsuAreaIdVO;

/**
 * UsuArea entity. @author MyEclipse Persistence Tools
 */

public class UsuArea  implements java.io.Serializable {

	//SerialVersion
	private static final long serialVersionUID = 1L;
	
    // Fields    

     private UsuAreaId id;


    // Constructors

    /** default constructor */
    public UsuArea() {
    }

    public UsuArea(Integer usuId, Integer areaId) {
    	Users users = new Users();
    	Area area = new Area();
    	users.setUsuId(usuId);
    	area.setAreId(areaId);
    	this.id = new UsuAreaId();
    	this.id.setArea(area);
    	this.id.setUsers(users);
    }
    
    public UsuArea(Integer areaId) {
    	Area area = new Area();
    	area.setAreId(areaId);
    	this.id = new UsuAreaId();
    	this.id.setArea(area);
    }
    
    /** full constructor */
    public UsuArea(UsuAreaId id) {
        this.id = id;
    }

   
    // Property accessors

    public UsuAreaId getId() {
        return this.id;
    }
    
    public void setId(UsuAreaId id) {
        this.id = id;
    }
    
    public UsuAreaIdVO getIdVO() {
    	UsuAreaIdVO usuAreaIdVO = new UsuAreaIdVO();
		usuAreaIdVO.setAreaVO((AreaVO)ConversionServices.transformPojoToVo(this.id.getArea()));
		usuAreaIdVO.setUsersVO((UsersVO)ConversionServices.transformPojoToVo(this.id.getUsers()));
        return usuAreaIdVO;
    }
}