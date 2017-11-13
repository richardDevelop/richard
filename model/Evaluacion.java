package com.evaluacionlinea.model;
// default package

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;



/**
 * Evaluacion entity. @author MyEclipse Persistence Tools
 */

public class Evaluacion  implements java.io.Serializable {

	//SerialVersion
	private static final long serialVersionUID = 1L;
	
    // Fields    

     private Integer evaId;
     private Cuestionario cuestionario;
     private String nombre;
     private String sede;
     private String grupo;
     private Integer duracionHora;
     private Integer duracionMin;
     private Integer calApro;
     private Integer showPDF;
     private Timestamp finiAplicacion;
     private Timestamp ffinAplicacion;
     private Timestamp hrIniAplicacion;
     private Timestamp hrFinAplicacion;
     private String usuario;
     private Timestamp falta;
     private Timestamp fmodifica;
     private Boolean activo;
     private Set evaCursos = new HashSet(0);
     private Set usuEvaluacions = new HashSet(0);
     private String areaNombre;
     private String estatus;
     private Integer evaExpId;
     private Integer evainstaId;
     private String hrApIni;
     private String hrApFin;
     private String fecApIni;
     private String fecApFin;


     /**
      * Variable que determina si esta evaluacion ya fue contestada por el usuario, no este en la tabla
      */
     private Boolean contestada;
     
    // Constructors

    /** default constructor */
    public Evaluacion() {
    }

    public Evaluacion(Integer horas, Integer min){
    	this.duracionHora = horas;
    	this.duracionMin = min;
    }
	/** minimal constructor */
    public Evaluacion(Cuestionario cuestionario, String nombre, Integer duracionHora, Integer duracionMin, Integer numPreguntas, Float valorRespuesta, Timestamp finiAplicacion, Timestamp ffinAplicacion, Timestamp hrIniAplicacion, Timestamp hrFinAplicacion, String usuario, Timestamp falta, Timestamp fmodifica, Boolean activo) {
        this.cuestionario = cuestionario;
        this.nombre = nombre;
        this.duracionHora = duracionHora;
        this.duracionMin = duracionMin;
         this.finiAplicacion = finiAplicacion;
        this.ffinAplicacion = ffinAplicacion;
        this.hrIniAplicacion = hrIniAplicacion;
        this.hrFinAplicacion = hrFinAplicacion;
        this.usuario = usuario;
        this.falta = falta;
        this.fmodifica = fmodifica;
        this.activo = activo;
    }
    
    /** full constructor */
    public Evaluacion(Cuestionario cuestionario, String nombre, String sede, String grupo, Integer duracionHora, Integer duracionMin, Integer numPreguntas, Float valorRespuesta, Timestamp finiAplicacion, Timestamp ffinAplicacion, Timestamp hrIniAplicacion, Timestamp hrFinAplicacion, String usuario, Timestamp falta, Timestamp fmodifica, Boolean activo, Set evaCursos, Set usuEvaluacions) {
        this.cuestionario = cuestionario;
        this.nombre = nombre;
        this.sede = sede;
        this.grupo = grupo;
        this.duracionHora = duracionHora;
        this.duracionMin = duracionMin;
        this.finiAplicacion = finiAplicacion;
        this.ffinAplicacion = ffinAplicacion;
        this.hrIniAplicacion = hrIniAplicacion;
        this.hrFinAplicacion = hrFinAplicacion;
        this.usuario = usuario;
        this.falta = falta;
        this.fmodifica = fmodifica;
        this.activo = activo;
        this.evaCursos = evaCursos;
        this.usuEvaluacions = usuEvaluacions;
    }

   
    // Property accessors

    public Integer getEvaId() {
        return this.evaId;
    }
    
    public void setEvaId(Integer evaId) {
        this.evaId = evaId;
    }

    public Cuestionario getCuestionario() {
        return this.cuestionario;
    }
    
    public void setCuestionario(Cuestionario cuestionario) {
        this.cuestionario = cuestionario;
    }

    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSede() {
        return this.sede;
    }
    
    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getGrupo() {
        return this.grupo;
    }
    
    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public Integer getDuracionHora() {
        return this.duracionHora;
    }
    
    public void setDuracionHora(Integer duracionHora) {
        this.duracionHora = duracionHora;
    }

    public Integer getDuracionMin() {
        return this.duracionMin;
    }
    
    public void setDuracionMin(Integer duracionMin) {
        this.duracionMin = duracionMin;
    }


    /**
	 * @return the calApro
	 */
	public Integer getCalApro() {
		return calApro;
	}

	/**
	 * @param calApro the calApro to set
	 */
	public void setCalApro(Integer calApro) {
		this.calApro = calApro;
	}

	/**
	 * @return the showPDF
	 */
	public Integer getShowPDF() {
		return showPDF;
	}

	/**
	 * @param showPDF the showPDF to set
	 */
	public void setShowPDF(Integer showPDF) {
		this.showPDF = showPDF;
	}


	public Timestamp getFiniAplicacion() {
    	return this.finiAplicacion;
    }
    
    public void setFiniAplicacion(Timestamp finiAplicacion) {
        this.finiAplicacion = finiAplicacion;
    }

    public Timestamp getFfinAplicacion() {
        return this.ffinAplicacion;
    }
    
    public void setFfinAplicacion(Timestamp ffinAplicacion) {
        this.ffinAplicacion = ffinAplicacion;
    }

    public Timestamp getHrIniAplicacion() {
        return this.hrIniAplicacion;
    }
    
    public void setHrIniAplicacion(Timestamp hrIniAplicacion) {
        this.hrIniAplicacion = hrIniAplicacion;
        
    }

    public Timestamp getHrFinAplicacion() {
        return this.hrFinAplicacion;
    }
    
    public void setHrFinAplicacion(Timestamp hrFinAplicacion) {
        this.hrFinAplicacion = hrFinAplicacion;
    }

    public String getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Timestamp getFalta() {
        return this.falta;
    }
    
    public void setFalta(Timestamp falta) {
        this.falta = falta;
    }

    public Timestamp getFmodifica() {
        return this.fmodifica;
    }
    
    public void setFmodifica(Timestamp fmodifica) {
        this.fmodifica = fmodifica;
    }

    public Boolean getActivo() {
        return this.activo;
    }
    
    public void setActivo(Boolean activo) {
        this.activo = activo;
        estatus = activo ? "Activo" : "Inactivo";
    }

    public Set getEvaCursos() {
        return this.evaCursos;
    }
    
    public void setEvaCursos(Set evaCursos) {
        this.evaCursos = evaCursos;
    }

    public Set getUsuEvaluacions() {
        return this.usuEvaluacions;
    }
    
    public void setUsuEvaluacions(Set usuEvaluacions) {
        this.usuEvaluacions = usuEvaluacions;
    }

	public String getAreaNombre() {
		return areaNombre;
	}

	public void setAreaNombre(String areaNombre) {
		this.areaNombre = areaNombre;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	/**
	 * @return the contestada
	 */
	public Boolean getContestada() {
		return contestada;
	}

	/**
	 * @param contestada the contestada to set
	 */
	public void setContestada(Boolean contestada) {
		this.contestada = contestada;
	}



	/**
	 * @return the hrApIni
	 */
	public String getHrApIni() {
		return hrApIni;
	}

	/**
	 * @param hrApIni the hrApIni to set
	 */
	public void setHrApIni(String hrApIni) {
		this.hrApIni = hrApIni;
	}

	/**
	 * @return the hrApFin
	 */
	public String getHrApFin() {
		return hrApFin;
	}

	/**
	 * @param hrApFin the hrApFin to set
	 */
	public void setHrApFin(String hrApFin) {
		this.hrApFin = hrApFin;
	}

	/**
	 * @return the fecApIni
	 */
	public String getFecApIni() {
		return fecApIni;
	}

	/**
	 * @param fecApIni the fecApIni to set
	 */
	public void setFecApIni(String fecApIni) {
		this.fecApIni = fecApIni;
	}

	/**
	 * @return the fecApFin
	 */
	public String getFecApFin() {
		return fecApFin;
	}

	/**
	 * @param fecApFin the fecApFin to set
	 */
	public void setFecApFin(String fecApFin) {
		this.fecApFin = fecApFin;
	}

	/**
	 * @return the evaExpId
	 */
	public Integer getEvaExpId() {
		return evaExpId;
	}

	/**
	 * @param evaExpId the evaExpId to set
	 */
	public void setEvaExpId(Integer evaExpId) {
		this.evaExpId = evaExpId;
	}

	/**
	 * @return the evainstaId
	 */
	public Integer getEvainstaId() {
		return evainstaId;
	}

	/**
	 * @param evainstaId the evainstaId to set
	 */
	public void setEvainstaId(Integer evainstaId) {
		this.evainstaId = evainstaId;
	}
	
	

	
}