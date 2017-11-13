package com.evaluacionlinea.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.ArrayList;
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
import com.evaluacionlinea.model.Curso;
import com.evaluacionlinea.model.Expositor;
import com.evaluacionlinea.model.Pregunta;
import com.evaluacionlinea.model.Respuesta;
import com.evaluacionlinea.utils.Const;
import com.evaluacionlinea.vo.CursoVO;
import com.evaluacionlinea.vo.ExpositorVO;
import com.evaluacionlinea.vo.PersonaVO;

@ManagedBean(name = "mbExpo")
@ViewScoped
public class MbExpositor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3785801212032352744L;

	private HttpServletRequest httpServletRequest;

	private PersonaVO usuarioSession;

	private List<SelectItem> lstArea;
	private Integer area;

	private List<CursoVO> lstCurso;
	private Integer curId;

	private List<SelectItem> lstCursoItems;

	private List<ExpositorVO> lstExpositor;

	@ManagedProperty(value="#{cuestionarioBean}")
	private ICuestionario cuestionario;

	
	
	/**
	 * @param cuestionario the cuestionario to set
	 */
	public void setCuestionario(ICuestionario cuestionario) {
		this.cuestionario = cuestionario;
	}

	/**
	 * @return the lstArea
	 */
	public List<SelectItem> getLstArea() {
		return lstArea;
	}

	/**
	 * @param lstArea
	 *            the lstArea to set
	 */
	public void setLstArea(List<SelectItem> lstArea) {
		this.lstArea = lstArea;
	}

	/**
	 * @return the area
	 */
	public Integer getArea() {
		return area;
	}

	/**
	 * @param area
	 *            the area to set
	 */
	public void setArea(Integer area) {
		this.area = area;
	}

	/**
	 * @return the lstCursoItems
	 */
	public List<SelectItem> getLstCursoItems() {
		return lstCursoItems;
	}

	/**
	 * @param lstCursoItems
	 *            the lstCursoItems to set
	 */
	public void setLstCursoItems(List<SelectItem> lstCursoItems) {
		this.lstCursoItems = lstCursoItems;
	}

	/**
	 * @return the curId
	 */
	public Integer getCurId() {
		return curId;
	}

	/**
	 * @param curId
	 *            the curId to set
	 */
	public void setCurId(Integer curId) {
		this.curId = curId;
	}

	/**
	 * @return the lstExpositor
	 */
	public List<ExpositorVO> getLstExpositor() {
		return lstExpositor;
	}

	/**
	 * @param lstExpositor
	 *            the lstExpositor to set
	 */
	public void setLstExpositor(List<ExpositorVO> lstExpositor) {
		this.lstExpositor = lstExpositor;
	}

	public void handleFileUpload(FileUploadEvent event) {
		if (curId != null && curId > 0) {
			try {
				Curso cur = new Curso();
				cur.setCurId(curId);
				List<Expositor> lstExpoSave = new ArrayList<Expositor>();
				FileUtils.writeByteArrayToFile(new File("tempExp.xlsx"), event
						.getFile().getContents());
				FileInputStream file = new FileInputStream(new File(
						"tempExp.xlsx"));
				Workbook workbook = WorkbookFactory.create(file);
				Sheet sheet = workbook.getSheetAt(0);
				Iterator<Row> rowIterator = sheet.iterator();
				while (rowIterator.hasNext()) {
					Row row = rowIterator.next();
					Iterator<Cell> cellIterator = row.cellIterator();

					if (row.getRowNum() > 0) {
						Expositor expo = new Expositor();
						Object[] celda = new Object[200];
						int i = 0;
						boolean isEmpyCell = true;
						while (cellIterator.hasNext() && isEmpyCell) {

							celda[i] = cellIterator.next();
							i++;
							String value = ((Cell) celda[0])
									.getStringCellValue();
							isEmpyCell = value != null && value.equals("") ? false
									: value == null ? false : true;
						}

						if (isEmpyCell) {
							String nombre = ((Cell) celda[0])
									.getStringCellValue()
									+ " "
									+ ((Cell) celda[1]).getStringCellValue();
							expo.setNombre(nombre);
							expo.setActivo(true);
						//	expo.setCurso(cur);
							lstExpoSave.add(expo);
						}

					}
				}
				cuestionario.saveExpositor(lstExpoSave);
				lstExpositor = cuestionario.getExpositorByCurId(curId);
			} catch (Exception e) {
				e.getCause();
			}
		} else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Falta Curso", "Seleccione Curso");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	@PostConstruct
	public void init() {
		ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();
		httpServletRequest = (HttpServletRequest) context.getRequest();
		usuarioSession = (PersonaVO) httpServletRequest.getSession()
				.getAttribute("sessionUsario");

		lstArea = Const.getLstAreaItem(usuarioSession.getListaUsuArea());
	}

	public void handleChangeAreaGral() {
		lstCurso = cuestionario.findCursoByArea(area);
		lstCursoItems = new ArrayList<SelectItem>();
		for (CursoVO curso : lstCurso) {
			SelectItem item = new SelectItem(curso.getCurId(),
					curso.getNombre());
			lstCursoItems.add(item);
		}

	}

	public void handleChangeCurso() {
		lstExpositor = cuestionario.getExpositorByCurId(curId);
	}

	
}
