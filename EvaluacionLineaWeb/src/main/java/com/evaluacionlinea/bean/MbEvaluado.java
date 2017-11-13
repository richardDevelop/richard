package com.evaluacionlinea.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.evaluacionlinea.interfaces.IPersona;
import com.evaluacionlinea.vo.EvaluacionVO;
import com.evaluacionlinea.vo.EvaluadosVO;
import com.evaluacionlinea.vo.PersonaVO;
import com.evaluacionlinea.vo.ResponsivaJasperVO;

import jxl.*;
import jxl.read.biff.BiffException;


@ManagedBean(name = "mbUsuarios")
@ViewScoped
public class MbEvaluado implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6944253918410527572L;
	
	private List<PersonaVO> lstPersona;
	
	
	
	private PersonaVO persona;
	
	private UploadedFile file;
	
	private PersonaVO usuarioSession;
	
	@ManagedProperty(value="#{personaBean}")
	private IPersona personaBean;
	
	private HttpServletRequest httpServletRequest;
	
	private EvaluacionVO ev;
	
	private EvaluacionVO eva;
	
	
	
	
	
	/**
	 * @param personaBean the personaBean to set
	 */
	public void setPersonaBean(IPersona personaBean) {
		this.personaBean = personaBean;
	}

	/**
	 * @return the file
	 */
	public UploadedFile getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(UploadedFile file) {
		this.file = file;
	}

	
	/**
	 * @return the lstPersona
	 */
	public List<PersonaVO> getLstPersona() {
		return lstPersona;
	}

	/**
	 * @param lstPersona the lstPersona to set
	 */
	public void setLstPersona(List<PersonaVO> lstPersona) {
		this.lstPersona = lstPersona;
	}

	/**
	 * @return the persona
	 */
	public PersonaVO getPersona() {
		return persona;
	}

	/**
	 * @param persona the persona to set
	 */
	public void setPersona(PersonaVO persona) {
		this.persona = persona;
	}
	
	

	public EvaluacionVO getEva() {
		return eva;
	}

	public void setEva(EvaluacionVO eva) {
		this.eva = eva;
	}

	@PostConstruct
	public void getEvaluadoLst(){
		try{
			cleanValues();
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			httpServletRequest = (HttpServletRequest) context.getRequest();
			ev = (EvaluacionVO) httpServletRequest.getSession().getAttribute("evaluacion");
			lstPersona = personaBean.getUsuByEvaId(ev.getEvaId());
			usuarioSession = (PersonaVO)httpServletRequest.getSession().getAttribute("sessionUsario");
		}catch(Exception e){
			e.getMessage();
		}
		
		
	}


	
	
	
	private void cleanValues(){
		ev = new EvaluacionVO();
		eva = new EvaluacionVO();
		lstPersona = new ArrayList<PersonaVO>();
	}
	
	@SuppressWarnings("deprecation")
	public void generatePdf() {
		List<ResponsivaJasperVO> lstReport = new ArrayList<ResponsivaJasperVO>();
		ResponsivaJasperVO report = new ResponsivaJasperVO();
		report.setNombreEva(ev.getNombre());
		//report.setNombreCues(ev.getCuestionarioVO().getNombre());
		report.setGrupo(ev.getGrupo());
		report.setSede(ev.getSede());
		report.setMinAprob(ev.getCalApro().toString());
		report.setUser(usuarioSession.getEmail());
		report.setFechaFinEva(getStringDate(ev.getFfinAplicacion()));
		report.setFechaInEva(getStringDate(ev.getFiniAplicacion()));
		report.setNumEvaluados(lstPersona.size());
		lstReport.add(report);
		try {
		
	    FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		HttpServletResponse response = (HttpServletResponse) context.getResponse();
		ServletOutputStream servletOutputStream = response.getOutputStream();
	    response.setContentType("application/pdf");
	    facesContext.responseComplete();
		response.setHeader("Content-Disposition", "attachment; filename=\"reporte.pdf\";");
	    response.setHeader("Cache-Control", "no-cache");
	    response.setHeader("Pragma", "no-cache");
	    response.setDateHeader("Expires", 0);

	    response.setContentType("application/pdf");
	    
	    
	    JasperReport reporteJas = (JasperReport) JRLoader.loadObjectFromFile(context.getRealPath("responsiva.jasper"));
	    JasperPrint jasperPrint = JasperFillManager.fillReport(reporteJas, null, 
	    		new JRBeanCollectionDataSource(lstReport));
	   

        JRExporter exporter = new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        exporter.exportReport();
        
        servletOutputStream.flush();
        servletOutputStream.close();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private String getStringDate(Timestamp date) {
		Date fec = new Date(date.getTime()); 
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String fecha = df.format(fec);
        return fecha;
	}
}
