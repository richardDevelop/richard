package com.evaluacionlinea.admin.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.evaluacionlinea.vo.EvaluadosVO;


@ManagedBean(name = "dtEvaluadosService")
@ApplicationScoped
public class EvaluadosListService{
	
	
	public List<EvaluadosVO> getEvaluadosList() {
		List<EvaluadosVO> lstEvaluados = new ArrayList<EvaluadosVO>();
		for(int i= 0; i < 25; i++){
			EvaluadosVO evaluado = new EvaluadosVO();
			evaluado.setNombre("Nombre " + i);
			evaluado.setApellidoPaterno("Paterno " + i);
			evaluado.setApellidoMaterno("Materno " + i);
			evaluado.setNumEmpleado("Numero Empleado " + i);
			evaluado.setMail("Mail " + i);
			evaluado.setEvaluacion("Evaluacion A o R " + i);
			evaluado.setCalificacion(new Double(1.0 + i));
			evaluado.setHora("Hora " + i);
			evaluado.setFecha(new Date());
			evaluado.setIpHost("Ip host " + i);
			lstEvaluados.add(evaluado);
		}
		return lstEvaluados;
		
	}
	

}
