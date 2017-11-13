package com.evaluacionlinea.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.primefaces.event.FileUploadEvent;

import com.evaluacionlinea.interfaces.ICuestionario;
import com.evaluacionlinea.model.Cuestionario;
import com.evaluacionlinea.model.Curso;
import com.evaluacionlinea.model.Pregunta;
import com.evaluacionlinea.model.Respuesta;
import com.evaluacionlinea.model.TipoCuestionario;
import com.evaluacionlinea.utils.Const;
import com.evaluacionlinea.vo.CuestionarioVO;
import com.evaluacionlinea.vo.PersonaVO;
import com.evaluacionlinea.vo.PreguntaVO;

@ManagedBean(name = "mbEvaEsp")
@ViewScoped
public class MbEvaluacionEspecial implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value="#{cuestionarioBean}")
	private ICuestionario cuestionario;
	
	private HttpServletRequest httpServletRequest;
	
	private CuestionarioVO cuestionarioVO;
	
	private PersonaVO usuarioSession;
	
	private List<PreguntaVO> lstPregunta;
	
	private List<SelectItem> lstCuestionario;
	private Integer cueId;
	
	private List<CuestionarioVO> lstCuestionarioVO;
	
	private List<SelectItem> lstTipoCuest;
	private Integer tipoCursoId;
	
	private Integer tipoCuest;
	
	private String nombre;
	
	private Boolean activo;
	
	
	
	
	/**
	 * @param cuestionario the cuestionario to set
	 */
	public void setCuestionario(ICuestionario cuestionario) {
		this.cuestionario = cuestionario;
	}

	/**
	 * @return the lstPregunta
	 */
	public List<PreguntaVO> getLstPregunta() {
		return lstPregunta;
	}

	/**
	 * @param lstPregunta the lstPregunta to set
	 */
	public void setLstPregunta(List<PreguntaVO> lstPregunta) {
		this.lstPregunta = lstPregunta;
	}

	/**
	 * @return the cuestionarioVO
	 */
	public CuestionarioVO getCuestionarioVO() {
		return cuestionarioVO;
	}

	/**
	 * @param cuestionarioVO the cuestionarioVO to set
	 */
	public void setCuestionarioVO(CuestionarioVO cuestionarioVO) {
		this.cuestionarioVO = cuestionarioVO;
	}
	
	
	/**
	 * @return the lstCuestionario
	 */
	public List<SelectItem> getLstCuestionario() {
		return lstCuestionario;
	}

	/**
	 * @param lstCuestionario the lstCuestionario to set
	 */
	public void setLstCuestionario(List<SelectItem> lstCuestionario) {
		this.lstCuestionario = lstCuestionario;
	}

	/**
	 * @return the cueId
	 */
	public Integer getCueId() {
		return cueId;
	}

	/**
	 * @param cueId the cueId to set
	 */
	public void setCueId(Integer cueId) {
		this.cueId = cueId;
	}

	/**
	 * @return the lstCuestionarioVO
	 */
	public List<CuestionarioVO> getLstCuestionarioVO() {
		return lstCuestionarioVO;
	}

	/**
	 * @param lstCuestionarioVO the lstCuestionarioVO to set
	 */
	public void setLstCuestionarioVO(List<CuestionarioVO> lstCuestionarioVO) {
		this.lstCuestionarioVO = lstCuestionarioVO;
	}
	
	

	/**
	 * @return the lstTipoCuest
	 */
	public List<SelectItem> getLstTipoCuest() {
		return lstTipoCuest;
	}
	
	

	/**
	 * @return the tipoCursoId
	 */
	public Integer getTipoCursoId() {
		return tipoCursoId;
	}

	/**
	 * @param tipoCursoId the tipoCursoId to set
	 */
	public void setTipoCursoId(Integer tipoCursoId) {
		this.tipoCursoId = tipoCursoId;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the activo
	 */
	public Boolean getActivo() {
		return activo;
	}

	/**
	 * @param activo the activo to set
	 */
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	
	/**
	 * @param lstTipoCuest the lstTipoCuest to set
	 */
	public void setLstTipoCuest(List<SelectItem> lstTipoCuest) {
		this.lstTipoCuest = lstTipoCuest;
	}
	
	

	/**
	 * @return the tipoCuest
	 */
	public Integer getTipoCuest() {
		return tipoCuest;
	}

	/**
	 * @param tipoCuest the tipoCuest to set
	 */
	public void setTipoCuest(Integer tipoCuest) {
		this.tipoCuest = tipoCuest;
	}

	@PostConstruct
	public void init() {
		ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();
		httpServletRequest = (HttpServletRequest) context.getRequest();
		usuarioSession = (PersonaVO) httpServletRequest.getSession()
				.getAttribute("sessionUsario");
		
		buildTipoCuestionario();
		
		
	}
	
	public void guardarCuest(){
		Cuestionario cuestionarioSave = new Cuestionario();
		cuestionarioSave.setNombre(nombre);
		cuestionarioSave.setActivo(activo);
		Curso cur = new Curso();
		cur.setCurId(tipoCuest.equals(Const.CUESTIONARIO_EXPOSITOR) ? Const.CURSO_EXPOSITOR : 
			Const.CURSO_INSTALACION);
		cuestionarioSave.setCurso(cur);
		TipoCuestionario tipo = new TipoCuestionario();
		tipo.setTcueId(tipoCursoId);
		cuestionarioSave.setTipoCuestionario(tipo);
		cuestionarioSave.setUsuario(usuarioSession.getUsuario());
		cuestionarioSave.setFalta(new Timestamp(new Date().getTime()));
		cuestionarioSave.setFmodifica(new Timestamp(new Date().getTime()));
		cuestionario.saveCuestionario(cuestionarioSave);
	}
	
	
	
	private void buildTipoCuestionario(){
		lstTipoCuest = new ArrayList<SelectItem>();
		lstTipoCuest.add(new SelectItem(Const.CUESTIONARIO_EXPOSITOR,
				"Expositor"));
		lstTipoCuest.add(new SelectItem(Const.CUESTIONARIO_INSTALACION,
				"Instalaciones"));
	}
	
	
	public void handleChangeTipoCues() {
		lstCuestionarioVO = cuestionario.getCuestionarioByTipo(tipoCursoId);
	}
	
	public void handleFileUploadCues(FileUploadEvent event) {
		if(cuestionarioVO == null){
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Error", "Seleccione un Cuestionario");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		try {
			FileUtils.writeByteArrayToFile(new File("tempCues.xlsx"), event
					.getFile().getContents());
			FileInputStream file = new FileInputStream(
					new File("tempCues.xlsx"));
			Workbook workbook = WorkbookFactory.create(file);
			Sheet sheet = workbook.getSheetAt(0);
			Sheet sheetRes = workbook.getSheetAt(1);
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();

				if (row.getRowNum() > 0) {
					Pregunta per = new Pregunta();
					Object[] celda = new Object[150];
					int i = 0;
					boolean isEmpyCell = true;
					while (cellIterator.hasNext() && isEmpyCell) {

						celda[i] = cellIterator.next();
						i++;
						Double value = ((Cell) celda[0]).getNumericCellValue();
						isEmpyCell = value != null && value.equals(0) ? false
								: value == null ? false : true;
					}

					if (isEmpyCell) {
						per.setPregunta(((Cell) celda[1]).getStringCellValue());
						per.setCueId(cuestionarioVO.getCueId());
					    if(per.getPregunta() != null && 
					    		!per.getPregunta().trim().equals("")){
					    	Integer preId = cuestionario.savePregunta(per);
							Integer preOrd = new Integer(
									(int) ((Cell) celda[0]).getNumericCellValue());
							saveRespuestaByPreId(sheetRes, preOrd, preId);
					    }
						
					}

				}
			}

		} catch (Exception e) {
			e.getMessage();
		}
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Registro Guardado", "Exitoso");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	private void saveRespuestaByPreId(Sheet sheet, Integer preOrd, Integer preId) {
		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();

			if (row.getRowNum() > 0) {
				Respuesta res = new Respuesta();
				Object[] celda = new Object[150];
				int i = 0;
				boolean isEmpyCell = true;
				boolean isRespuesta = true;
				while (cellIterator.hasNext() && isEmpyCell) {

					celda[i] = cellIterator.next();
					i++;
					Double value = ((Cell) celda[0]).getNumericCellValue();
					Integer preOrdRe = new Integer(value.intValue());
					isEmpyCell = value != null && value.equals(0.0) ? false
							: value == null ? false : true;
					isRespuesta = preOrdRe.equals(preOrd);

				}

				if (isEmpyCell && isRespuesta) {
					res.setPreId(preId);

					if (((Cell) celda[1]).getCellType() == Cell.CELL_TYPE_NUMERIC) {
						Double respuesta = ((Cell) celda[1])
								.getNumericCellValue();
						res.setRespuesta(respuesta.toString());
					} else {
						res.setRespuesta(((Cell) celda[1]).getStringCellValue());
					}
					String orden = ((Cell) celda[2]).getStringCellValue();
					res.setOrden(orden);
					res.setEsCorrecta(false);
					cuestionario.saveRespuesta(res);
				}

			}
		}
	}

	public void handleChangeTipoCuesTipo(){
		tipoCuest = tipoCursoId;
	}
	
	
	

}
