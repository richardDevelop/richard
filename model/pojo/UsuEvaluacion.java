package com.evaluacionlinea.model.pojo;
// default package

import java.sql.Timestamp;


/**
 * UsuEvaluacion entity. @author MyEclipse Persistence Tools
 */

public class UsuEvaluacion  implements java.io.Serializable {


    // Fields    

     private UsuEvaluacionId id;
     private String usuario;
     private Timestamp falta;
     private Timestamp fmodifica;
     private Boolean activo;


    // Constructors

    /** default constructor */
    public UsuEvaluacion() {
    }

    
    /** full constructor */
    public UsuEvaluacion(UsuEvaluacionId id, String usuario, Timestamp falta, Timestamp fmodifica, Boolean activo) {
        this.id = id;
        this.usuario = usuario;
        this.falta = falta;
        this.fmodifica = fmodifica;
        this.activo = activo;
    }

   
    // Property accessors

    public UsuEvaluacionId getId() {
        return this.id;
    }
    
    public void setId(UsuEvaluacionId id) {
        this.id = id;
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
    }
   








}