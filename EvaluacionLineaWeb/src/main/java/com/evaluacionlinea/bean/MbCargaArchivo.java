package com.evaluacionlinea.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.primefaces.event.FileUploadEvent;

import com.evaluacionlinea.interfaces.ICuestionario;
import com.evaluacionlinea.interfaces.IPersona;
import com.evaluacionlinea.model.Pregunta;
import com.evaluacionlinea.model.Respuesta;
import com.evaluacionlinea.vo.EvaluacionVO;
import com.evaluacionlinea.vo.EvaluadosVO;
import com.evaluacionlinea.vo.PersonaVO;
@ManagedBean(name = "mbCarga")
@ViewScoped
public class MbCargaArchivo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{personaBean}")
	private IPersona personaBean;
	
	@ManagedProperty(value="#{cuestionarioBean}")
	private ICuestionario cuestionario;

	private List<EvaluadosVO> lstPersonaSave;

	private PersonaVO usuarioSession;

	private HttpServletRequest httpServletRequest;
	
	private EvaluacionVO ev;
	
	private Integer areId;
	
	

	/**
	 * @param personaBean the personaBean to set
	 */
	public void setPersonaBean(IPersona personaBean) {
		this.personaBean = personaBean;
	}

	/**
	 * @param cuestionario the cuestionario to set
	 */
	public void setCuestionario(ICuestionario cuestionario) {
		this.cuestionario = cuestionario;
	}

	@PostConstruct
	public void init() {
		ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();
		httpServletRequest = (HttpServletRequest) context.getRequest();
		usuarioSession = (PersonaVO) httpServletRequest.getSession()
				.getAttribute("sessionUsario");
	    ev = (EvaluacionVO) httpServletRequest.getSession()
				.getAttribute("evaluacion");
	    areId = (Integer)httpServletRequest.getSession().
	    		getAttribute("areId");
		
	}

	public void handleFileUpload(FileUploadEvent event) {

		try {
			FileUtils.writeByteArrayToFile(new File("temp.xlsx"), event
					.getFile().getContents());
			FileInputStream file = new FileInputStream(new File("temp.xlsx"));
			jxl.Workbook workbook = jxl.Workbook.getWorkbook(file);
			jxl.Sheet sheet = workbook.getSheet(0);
			lstPersonaSave = new ArrayList<EvaluadosVO>();
			String nombre;
			Object[] celda = new Object[200];
			for (int fila = 1; fila < sheet.getRows(); fila++) { // recorremos
																	// las filas
				EvaluadosVO per = new EvaluadosVO();
				for (int columna = 0; columna < sheet.getColumns(); columna++) { // recorremos
																					// las
																					// columnas
					celda[columna] = sheet.getCell(columna, fila).getContents(); // setear
																					// la
																					// celda
																					// leida
																					// a
																					// nombre
					String valor = (String) celda[columna];
					if (columna > 9 && valor.trim().equals(""))
						break;
				}
				per.setNumEmpleado((String) celda[0]);
				per.setNombre((String) celda[1] + " " + (String) celda[2]);
				per.setApellidoPaterno((String) celda[3]);
				per.setApellidoMaterno((String) celda[4]);
				per.setMail((String) celda[5]);
				per.setNivel(1);
				per.setUser(usuarioSession.getUsers().getUsuario());
				per.setOficina((String) celda[5]);
				per.setRegion((String) celda[6]);
				// per.setNivel((String) ((Cell)
				// celda[7]).getStringCellValue());
				per.setSede((String) celda[8]);
				per.setGrupo(((String) celda[9]) != null ? (String) celda[9]
						: "");
				lstPersonaSave.add(per);
			}
			personaBean.cargaUsuario(lstPersonaSave, ev, areId);
		} catch (Exception e) {
			e.getMessage();
		}
		FacesMessage message = new FacesMessage("Carga Existosa",
				"Usuarios Agregados");
		FacesContext.getCurrentInstance().addMessage(null, message);

	}

	public void handleFileUploadCuest(FileUploadEvent event) {
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
					Object[] celda = new Object[50];
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
						per.setCueId(ev.getCuestionarioVO().getCueId());
						if (per.getPregunta() != null
								&& !per.getPregunta().trim().equals("")) {
							Integer preId = cuestionario.savePregunta(per);
							Integer preOrd = new Integer(
									(int) ((Cell) celda[0])
											.getNumericCellValue());
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
					double resOk = ((Cell) celda[3]).getNumericCellValue();
					res.setEsCorrecta(resOk > 0);
					cuestionario.saveRespuesta(res);
				}

			}
		}
	}

}
