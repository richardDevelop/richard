package com.evaluacionlinea.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.evaluacionlinea.interfaces.IPersona;
import com.evaluacionlinea.model.Nivel;
import com.evaluacionlinea.model.Persona;
import com.evaluacionlinea.model.Users;
import com.evaluacionlinea.model.UsuArea;
import com.evaluacionlinea.model.UsuPerfil;
import com.evaluacionlinea.utils.Const;
import com.evaluacionlinea.vo.NivelVO;
import com.evaluacionlinea.vo.PersonaVO;
import com.evaluacionlinea.vo.UsersVO;
import com.evaluacionlinea.vo.UsuAreaVO;
import com.evaluacionlinea.vo.UsuPerfilVO;

@ManagedBean(name="MbPersona")
@ViewScoped
public class MbPersona implements Serializable{
	
	private static final long serialVersionUID = -605270684052357027L;
	
	private PersonaVO usuarioSession;
	//private Persona persona;
	private PersonaVO selectedPersona;
	private Integer areaId;
    private java.util.List<PersonaVO> lstPersonas;
    private Integer[] selectedAreas;
    private Integer[] selectedPerfiles;
    private List<PersonaVO> filteredlstPersonas;
        
    private FacesMessage facesMessage;
    private final HttpServletRequest httpServletRequest;
	private final FacesContext facesContext;
    
	@ManagedProperty(value="#{personaBean}")
	IPersona personaBean;
	
	
	
    
    /**
	 * @param personaBean the personaBean to set
	 */
	public void setPersonaBean(IPersona personaBean) {
		this.personaBean = personaBean;
	}

	/**
     * Constructor
     */    
    public MbPersona(){
//    	persona = new Persona();
//    	persona.setUsers(new Users());
//    	persona.setNivel(new Nivel());
    	
    	selectedPersona = new PersonaVO();
    	selectedPersona.setUsersVO(new UsersVO());
    	selectedPersona.setNivelVO(new NivelVO());
    	
    	facesContext = FacesContext.getCurrentInstance();		
		httpServletRequest = (HttpServletRequest)facesContext.getExternalContext().getRequest();
		if (httpServletRequest.getSession().getAttribute("sessionUsario") != null) {
			usuarioSession = (PersonaVO)httpServletRequest.getSession().getAttribute("sessionUsario");
		}
    }
        
	/**
	 * @return the persona
	 */
//	public Persona getPersona() {
//		return persona;
//	}

	/**
	 * @param persona the persona to set
	 */
//	public void setPersona(Persona persona) {
//		this.persona = persona;
//	}
	
	/**
	 * @return the selectedPersona
	 */
	public PersonaVO getSelectedPersona() {
		return selectedPersona;
	}

	/**
	 * @param selectedPersona the selectedPersona to set
	 */
	public void setSelectedPersona(PersonaVO selectedPersona) {
		
		//Buscamos las todas las areas relacionadas al usuarios y las seleccionamos
		List<UsuAreaVO> lstUsuAreaVO = personaBean.getListaAreasByUsuId(selectedPersona.getUsersVO().getUsuId());
		Integer[] arr = new Integer[lstUsuAreaVO.size()];
		Integer cont=0;
		for (UsuAreaVO usuAreaVO : lstUsuAreaVO) {
			arr[cont] = usuAreaVO.getIdVO().getAreaVO().getAreId();
			cont+=1;
		}
		selectedAreas = arr;
		
		//Buscamos las todos los perfiles relacionados al usuarios y las seleccionamos
		List<UsuPerfilVO> lstUsuPerfilVO = personaBean.getPerfilByUsuId(selectedPersona.getUsersVO().getUsuId());
		arr = new Integer[lstUsuPerfilVO.size()];
		cont=0;
		for (UsuPerfilVO usuPerfilVO : lstUsuPerfilVO) {
			arr[cont] = usuPerfilVO.getIdVO().getPerfilVO().getPerfId();
			cont+=1;
		}
		selectedPerfiles = arr;
		
		this.selectedPersona = selectedPersona;
	}

	/**
	 * @return the areaId
	 */
	public Integer getAreaId() {
		return areaId;
	}

	/**
	 * @param areaId the areaId to set
	 */
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	
	/**
	 * @return the lstPersonas
	 */
	public java.util.List<PersonaVO> getLstPersonas() {
		return lstPersonas;
	}

	/**
	 * @return the selectedAreas
	 */
	public Integer[] getSelectedAreas() {
		return selectedAreas;
	}

	/**
	 * @param selectedAreas the selectedAreas to set
	 */
	public void setSelectedAreas(Integer[] selectedAreas) {
		this.selectedAreas = selectedAreas;
	}
	
	/**
	 * @return the selectedPerfiles
	 */
	public Integer[] getSelectedPerfiles() {
		return selectedPerfiles;
	}

	/**
	 * @param selectedPerfiles the selectedPerfiles to set
	 */
	public void setSelectedPerfiles(Integer[] selectedPerfiles) {
		this.selectedPerfiles = selectedPerfiles;
	}

	/**
	 * @return the filteredlstPersonas
	 */
	public List<PersonaVO> getFilteredlstPersonas() {
		return filteredlstPersonas;
	}

	/**
	 * @param filteredlstPersonas the filteredlstPersonas to set
	 */
	public void setFilteredlstPersonas(List<PersonaVO> filteredlstPersonas) {
		this.filteredlstPersonas = filteredlstPersonas;
	}

	public void BuscarPersonasByAreaId() {
		lstPersonas = personaBean.getPersonaByAreaId(this.areaId);		
	}
	
	public String nuevaPersona() {
		selectedPersona = new PersonaVO();
    	selectedPersona.setUsersVO(new UsersVO());
    	selectedPersona.setNivelVO(new NivelVO());
    	selectedAreas = null;
    	selectedPerfiles = null;
		return null;
	}
	
	public String editarPersona() {
		return null;
	}
	
	public String BuscarPersonasByEmail() {
		
//		if (selectedPersona != null) {
//			this.persona = personaBean.getPersonaByEmail(this.selectedPersona.getEmail());
//			if (null != persona) {
//				this.selectedPersona = persona;
//				facesMessage=new FacesMessage(FacesMessage.SEVERITY_INFO, "Ya existe un usuario con este email", null);
//				facesContext.addMessage(null, facesMessage);
//			}
//		}
		return null;
	}
	
	public void refresh() {
	    FacesContext context = FacesContext.getCurrentInstance();
	    Application application = context.getApplication();
	    javax.faces.application.ViewHandler viewHandler = application.getViewHandler();
	    UIViewRoot viewRoot = viewHandler.createView(context, context
	     .getViewRoot().getViewId());
	    context.setViewRoot(viewRoot);
	    context.renderResponse(); 
	 }
	
	public String doGuardar(){     
        
        if (selectedPersona != null  && (selectedAreas !=null && selectedAreas.length > 0)) {
        	UsuAreaVO usuAreaVO;
        	Integer perId = null;
        	UsuPerfilVO usuPerfilVO;
        	
        	//Recorremoas las areas seleccionadas
        	List<UsuAreaVO> lstUsuAreaVO = new ArrayList<UsuAreaVO>();
        	for (Integer areaId : selectedAreas) {
				usuAreaVO = new UsuAreaVO(areaId);
				lstUsuAreaVO.add(usuAreaVO);
			}        	
        	
        	//recorremos los perfiles seleccionados
        	List<UsuPerfilVO> lstPerfilVO = new ArrayList<UsuPerfilVO>();
        	for (Integer perfilId : selectedPerfiles) {
				usuPerfilVO = new UsuPerfilVO(perfilId);
				lstPerfilVO.add(usuPerfilVO);
			}
        	
        	this.selectedPersona.setListaUsuAreaVO(lstUsuAreaVO);
        	this.selectedPersona.setListaUsuPerfilVO(lstPerfilVO);
        	this.selectedPersona.getUsersVO().setActivo(selectedPersona.getActivo());
        	this.selectedPersona.setUsuario(usuarioSession.getUsersVO().getUsuario());
        	
        	//Si no trae el password, lo genera
        	if (selectedPersona.getUsersVO().getPassword().isEmpty()) {
    			this.selectedPersona.getUsersVO().setPassword(Const.generaPassword(selectedPersona));
			}
        	
        	if (null == this.selectedPersona.getPerId()) {
        		perId = personaBean.guardaPersona(this.selectedPersona);
        		selectedPersona.setPerId(perId);        		
			} else {
				personaBean.saveOrUpdate(this.selectedPersona);
			}
		}
        
        return null;
    }
	
	public String doElimina() {
		
		if (selectedPersona != null  && (selectedAreas !=null && selectedAreas.length > 0)) {
			
		}
		
		return null;
	}
}
