package com.evaluacionlinea.bean;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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

import com.evaluacionlinea.interfaces.ICuestionario;
import com.evaluacionlinea.interfaces.IPersona;
import com.evaluacionlinea.model.Evaluacion;
import com.evaluacionlinea.vo.EvaluacionVO;
import com.evaluacionlinea.vo.PersonaVO;
import com.evaluacionlinea.vo.ResponsivaJasperVO;

@ManagedBean(name = "mbEvaEdit")
@ViewScoped
public class MbEvaluacionEdit {
	
	private EvaluacionVO eval;
	
	@ManagedProperty(value="#{cuestionarioBean}")
	private ICuestionario cuestionarioBean;
	
	private HttpServletRequest httpServletRequest;
	
	private List<PersonaVO> lstPersona;
	
	private PersonaVO per;
	
	@ManagedProperty(value="#{personaBean}")
	private IPersona personaBean;
	
	

	/**
	 * @param personaBean the personaBean to set
	 */
	public void setPersonaBean(IPersona personaBean) {
		this.personaBean = personaBean;
	}

	/**
	 * @return the eval
	 */
	public EvaluacionVO getEval() {
		return eval;
	}

	/**
	 * @param eval the eval to set
	 */
	public void setEval(EvaluacionVO eval) {
		this.eval = eval;
	}

	/**
	 * @return the cuestionarioBean
	 */
	public ICuestionario getCuestionarioBean() {
		return cuestionarioBean;
	}

	/**
	 * @param cuestionarioBean the cuestionarioBean to set
	 */
	public void setCuestionarioBean(ICuestionario cuestionarioBean) {
		this.cuestionarioBean = cuestionarioBean;
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

	@PostConstruct
	public void init() {
		try{
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			httpServletRequest = (HttpServletRequest) context.getRequest();
			eval = (EvaluacionVO) httpServletRequest.getSession().getAttribute("evaEdit");
			 per = (PersonaVO) httpServletRequest.getSession().getAttribute("sessionUsario");
			lstPersona = personaBean.getUsuByEvaId(eval.getEvaId());
		}catch(Exception e){
			e.getMessage();
		}
	}
	
	public void generatePdf() {
		List<ResponsivaJasperVO> lstReport = new ArrayList<ResponsivaJasperVO>();
		ResponsivaJasperVO report = new ResponsivaJasperVO();
		report.setNombreEva(eval.getNombre());
	//	report.setNombreCues(eval.getCuestionarioVO().getNombre());
		report.setGrupo(eval.getGrupo());
		report.setSede(eval.getSede());
		report.setMinAprob(eval.getCalApro().toString());
		report.setUser(per.getEmail());
		report.setFechaFinEva(getStringDate(eval.getFfinAplicacion()));
		report.setFechaInEva(getStringDate(eval.getFiniAplicacion()));
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
