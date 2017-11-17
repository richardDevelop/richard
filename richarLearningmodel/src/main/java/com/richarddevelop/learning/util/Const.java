///**
// * 
// */
package com.richarddevelop.learning.util;
//
//import java.io.ByteArrayOutputStream;
import java.sql.Timestamp;
import java.util.Calendar;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Collections;
//import java.util.Date;
//import java.util.List;
//import java.util.Random;
//
//import javax.faces.model.SelectItem;
//
//import com.itextpdf.text.BaseColor;
//import com.itextpdf.text.Chunk;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.Font;
//import com.itextpdf.text.Font.FontFamily;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.pdf.PdfWriter;
//import com.itextpdf.text.pdf.draw.DottedLineSeparator;
//import com.richardevelop.learning.vo.ContestoVO;
//import com.richardevelop.learning.vo.CuestionarioVO;
//import com.richardevelop.learning.vo.PersonaVO;
//import com.richardevelop.learning.vo.PreguntaVO;
//import com.richardevelop.learning.vo.ResultadoVO;
//
///**
// * @author WebServer
// *
// */
public class Const {
//
//	
//	public static final Integer CUESTIONARIO_NORMAL = 1;
//	public static final Integer CUESTIONARIO_EXPOSITOR = 2;
//	public static final Integer CUESTIONARIO_INSTALACION = 3;
//	
//	public static final Integer CURSO_EXPOSITOR  = 2;
//	public static final Integer CURSO_INSTALACION = 3;
//		/**
//		 * Preguntas cuestionario random
//		 * @param cuestionario
//		 * @param numPreguntas
//		 * @param usuId
//		 * @param evaId
//		 * @return
//		 */
//		public static List<PreguntaVO> getPreguntaRandom (CuestionarioVO cuestionario, int numPreguntas, Integer usuId, Integer evaId) {
//			List<PreguntaVO> listPreguntas = new ArrayList<PreguntaVO>();
//			siguiente:
//			for(int i=0; i<numPreguntas;i++){
//				if(cuestionario.getPreguntasVO().size() > 0) {
//					int index = 0;
//					int item = new Random().nextInt(cuestionario.getPreguntasVO().size());
//					for (PreguntaVO pregunta : cuestionario.getPreguntasVO()) {
//						if(index == item && pregunta.getContestoVO() == null) {
//							PreguntaVO preguntaVO = new PreguntaVO(pregunta, evaId, cuestionario.getCursoVO().getCurId(), usuId,
//									TIPO_CUE_EXAMEN);
//							listPreguntas.add(preguntaVO);
//							cuestionario.getPreguntasVO().remove(pregunta);
//							continue siguiente;
//						} 
//						index ++;
//					}
//				} else {
//					break;
//				}
//			}
//			
//			return listPreguntas;
//		}
//		
//		/**
//		 * Preguntas para Encuenta sin randmom
//		 * @param cuestionario
//		 * @param numPreguntas
//		 * @param usuId
//		 * @param evaId
//		 * @return
//		 */
//		public static List<PreguntaVO> getPregunta (CuestionarioVO cuestionario, Integer usuId, Integer evaId) {
//			List<PreguntaVO> listPreguntas = new ArrayList<PreguntaVO>();
//				if(cuestionario.getPreguntasVO().size() > 0) {
//					for (PreguntaVO pregunta : cuestionario.getPreguntasVO()) {
//						if (pregunta.getContestoVO() == null) {
//							PreguntaVO preguntaVO = new PreguntaVO(pregunta, evaId, cuestionario.getCursoVO().getCurId(), usuId,
//									TIPO_CUE_ENCUESTA);
//							listPreguntas.add(preguntaVO);
//						} 
//					}
//				} 
//			return listPreguntas;
//		}
//		
//		/**
//		 * 
//		 * @param numero a redondear
//		 * @param digitos 
//		 * @return
//		 */
//		public static Double Redondear(double numero,int digitos)
//		{
//		      int cifras=(int) Math.pow(10,digitos);
//		      return Math.rint(numero*cifras)/cifras;
//		}
//		
//		/**
//		 * Obtiene el slected item de las areas por usuario
//		 * @param lstArea
//		 * @return
//		 */
//		public static List<SelectItem> getLstAreaItem(List<UsuArea> lstArea){
//			List<SelectItem> lstAreaByUser = new ArrayList<SelectItem>();
//			for(UsuArea us : lstArea){
//				SelectItem item = new SelectItem(us.getId().getArea().getAreId(), us.getId().getArea().getNombre());
//				lstAreaByUser.add(item);
//			}
//			
//			return lstAreaByUser;
//			
//		}
//		
//		/**
//		 * Metodo mediante el cual se genera el password del usuario, tomando el nombre, apellidos y numero de empleado
//		 * @param Persona
//		 * @return String
//		 */
//		public static String generaPassword(PersonaVO persona) {
//			String password = "";
//			
//			//Se valida si el nombre viene vacio
//			if (!persona.getNombre().isEmpty()) {
//				
//				//se almacena en un arreglo los nombres
//				String[] nombres = persona.getNombre().split(" ");
//				
//				//Se valida si solo existe un nombre
//				if (nombres.length == 1) {
//					//Validamos si el tamaño del nombre es mayor 1 
//					if (nombres[0].length() > 1) {
//						//Se toman las primeras dos letras del nombre
//						password = nombres[0].toString().substring(0, 2);
//					} else {
//						//Si el nombre no es mayor a 1, se generan las 2 letras
//						password = ramdonString(2);
//					}				
//				} else if (nombres.length == 2) {
//					//se toma la primer letra, del primer nombre
//					password = nombres[0].toString().substring(0,1);
//					//se toma la segunda letra, del segundo nombre
//					password = password + nombres[1].toString().substring(0,1);
//				}
//			} else {
//				//Si el nombre viene vacio, se generan las 2 letras para el passwprd
//				password = ramdonString(2);
//			}
//			
//			//Se valida si solo tiene el apellido paterno
//			if (!persona.getApePaterno().isEmpty() && persona.getApeMaterno().isEmpty()) {
//				//Se valida el tamaño del apellido paterno
//				if (persona.getApePaterno().length() >1) {
//					//se toman las dos primeras letras del apellido paterno
//					password = password + persona.getApePaterno().substring(0,2);
//				} else {
//					//Si el apellido paterno no es mayor a 1, se generan las 2 letras
//					password = ramdonString(2);
//				}				
//			}
//			
//			//Se valida si se cuenta con los 2 apellidos
//			if (!persona.getApePaterno().isEmpty() && !persona.getApeMaterno().isEmpty()) {	
//				//Se toma la primer letra del apellido paterno
//				password = password + persona.getApePaterno().substring(0,1);
//				//Se toma la primer letra del apellido materno
//				password = password + persona.getApeMaterno().substring(0,1);			
//			}
//			
//			//Validamos si el numero de empleado es diferente de vacio
//			if (!persona.getNumEmpleado().isEmpty() && persona.getNumEmpleado().length() > 8) {
//				
//				//Se valida si apartir de la posicion 2 a la 7, la cadena es de valor numerico
//				if (isNumeric(persona.getNumEmpleado().substring(2, 7))) {
//					//Si la cadena es numerica, tomanos de la posicion 2 a la 7
//					password = password + persona.getNumEmpleado().substring(2, 7);
//				} else {
//					//Si la cadena no era numerica, generamos un numero mediante ramdom, de 5 posiciones
//					password = password + ramdon(5).toString();
//				}
//			} else {
//				//En caso de que sea vacio, generamos un numero de 5 digitos
//				password = password + ramdon(5).toString();
//			}
//			
//			//por ultimo se genera un numero de 3 digitos
//			password = password + ramdon(3).toString();
//			
//			return password;
//		}
//		
//		/**
//		 * Metodo mediante el cual se genera un numero usando Ramdom
//		 * @param sizeReturn numero de digitos a regresar
//		 * @return Integer
//		 */
//		private static Integer ramdon(Integer sizeReturn) {
//			Integer rd = 0;
//			Integer sizeRamdom = 9;
//			switch (sizeReturn) {
//			case 3:
//				sizeRamdom = 999;
//				break;
//			case 5:
//				sizeRamdom = 99999;
//				break;
//			}
//			while (rd.toString().length() != sizeReturn) {
//				rd = (int) (Math.random()*sizeRamdom+1);
//			}
//			return rd;
//		}
//		
//		/**
//		 * Metodo mediante el cual se genera ramdom de letras, especidficando el tamaño de la cadena a regresar
//		 * @param sizeReturn tamaño de la cadena a regresar
//		 * @return String
//		 */
//		private static String ramdonString(Integer sizeReturn) {
//			String rd = "";
//			int position;
//			String[] abecedario = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", 
//					"K", "L", "M","N","O","P","Q","R","S","T","U","V","W", "X","Y","Z"};
//						
//			while (rd.toString().length() != sizeReturn) {
//				position = (int) Math.round(Math.random()*26);
//				rd = rd + abecedario[position];
//			}
//			
//			return rd;
//		}
//		
//		/**
//		 * Funcion que valida si una cadena es numerica
//		 * @param cadena
//		 * @return true = es numerica, false=no es numerica
//		 */
//		public static boolean isNumeric(String cadena){
//			try {
//				Integer.parseInt(cadena);
//				return true;
//			} catch (NumberFormatException nfe){
//				return false;
//			}
//		}
//
//		
//		/**
//		 * Regresa Hora Actuakl en timestamp
//		 * @return
//		 */
		public static Timestamp getHoraActual(){
			Calendar cal = Calendar.getInstance();
			return new Timestamp(cal.getTimeInMillis()); 
		}
//		
//		
//		public static long difSegundosTimestamp (Timestamp fIni, Timestamp fFin) {
//			return ((Math.abs(fFin.getTime() - fIni.getTime())/1000));
//		}
//		
//		public static Integer Perfil_ROOT = 1;
//		public static Integer Perfil_ADMINTRADOR = 2;
//		public static Integer Perfil_EVALUADO = 3;
//		public static Integer Perfil_MONITOR = 4;
//		
//		public static Font FONT_RED = new Font(FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.RED);
//        public static Font FONT_NORMAL = new Font(FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);
//        public static Font FONT_BLOD = new Font(FontFamily.HELVETICA, 12, Font.BOLDITALIC, BaseColor.BLACK);
//        
//        public static Integer TIPO_CUE_EXAMEN = 1;
//        public static Integer TIPO_CUE_ENCUESTA = 2;
//        
//		/**
//		 * Construlle PDF resultado evaluacion
//		 * @param cuestionario
//		 * @param listContesto
//		 * @return BYTE[] PDF
//		 * @throws DocumentException
//		 */
//		public static byte[] builtPDFResultado (CuestionarioVO cuestionario, List<ContestoVO> listContesto,
//				PersonaVO persona, ResultadoVO result) throws DocumentException {
//			ByteArrayOutputStream out = new ByteArrayOutputStream();  
////	        Document document = new Document();  
////	        PdfWriter.getInstance(document, out);  
////	        document.open();  
////
////	        int i = 0;
////	        document.add(new Paragraph(""));
////	        document.add(new Paragraph(cuestionario.getNombre()));
////	        document.add(new Paragraph("Nombre: " + persona.getApePaterno() + " " + persona.getApeMaterno() +
////	        		" " + persona.getNombre()));
////	        document.add(new Paragraph("Calificación: " + result.getCalificacion().toString()));
////	        document.add(new Paragraph("Respuestas correctas: " + result.getResTotal().toString()));
////	        document.add(new Paragraph(" \n"));
////	        for (PreguntaVO pregunta : cuestionario.getPreguntasVO()) { 
////	        	 Chunk pregText = new Chunk(++i + "-  " + pregunta.getPregunta(),FONT_NORMAL);
////	        	document.add(new Paragraph(pregText));
////	        	Integer resContesto = 0;
////	        	for(ContestoVO contesto : listContesto){
////	        		if(pregunta.getPreId().equals(contesto.getPreId())) {
////	        			resContesto = contesto.getResId();
////	        		}
////	        	}
////	        	List<RespuestaVO> listRespuestas = pregunta.getRespuestasVO();
////	        	Collections.sort(listRespuestas, new java.util.Comparator<RespuestaVO>() {
////	    			@Override
////	    			public int compare(RespuestaVO o1, RespuestaVO o2) {
////	    				return o1.getOrden().compareTo(o2.getOrden());
////	    			}
////	    		});
////	        	for(RespuestaVO respuesta : listRespuestas) {
////	        		Font fontRes;
////	        		if (respuesta.getEsCorrecta()){
////	        			fontRes = FONT_BLOD;
////	        		} else if(respuesta.getRespId().equals(resContesto)){
////	        			fontRes = FONT_RED;
////	        		} else {
////	        			fontRes = FONT_NORMAL;
////	        		}
////	        		Chunk resText = new Chunk("      " + respuesta.getOrden() + ")   " + respuesta.getRespuesta(),fontRes);
////	        		document.add(new Paragraph(resText));	
////	        	}
////	        	DottedLineSeparator dottedline = new DottedLineSeparator();
////	        	dottedline.setOffset(-1);
////	            dottedline.setGap(0f);
////	            Chunk linebreak = new Chunk(dottedline);
//	          //  document.add(linebreak);
//	       // }  
//
//	    //    document.close();  
//	        return out.toByteArray();
//		}
//		
//		
//		@SuppressWarnings("deprecation")
//		public static Date builDateDuracion(Integer hrs, Integer min){
//			Date fecha  = new Date();
//			Integer hora = hrs == null ? 0 : hrs;
//			Integer minuto = min == null ? 0 : min;
//			fecha.setHours(hora);
//			fecha.setMinutes(minuto);
//			return fecha;
//		}
//		
}
