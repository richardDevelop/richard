package com.evaluacionlinea.bean;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean (name="mbPdfRes")
public class MbPDFResultado {

	/**
	* PDF
	*/
	 private StreamedContent content;  
	 
	/**
	 * constructor
	 */
	public MbPDFResultado() {
		FacesContext contex = FacesContext.getCurrentInstance();		
		HttpServletRequest httpServletRequest = (HttpServletRequest)contex.getExternalContext().getRequest();
		try {
			byte[] b = (byte[]) httpServletRequest.getSession().getAttribute("content");
			getPdf(b);
		} catch (Exception e) {
			e.printStackTrace();  
		}
	}

	public void getPdf(byte[] b) {
		try {  
		   java.io.InputStream stream;
            stream = new ByteArrayInputStream( b );
            stream.mark(0);
            
            setContent(new DefaultStreamedContent(stream, "application/pdf"));  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
	}
	

	/**
	 * @return the content
	 * @throws IOException 
	 */
	public StreamedContent getContent() throws IOException {
		
		if (content != null)
			content.getStream().reset(); //reset stream to the start position!
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(StreamedContent content) {
		this.content = content;
	}
}
