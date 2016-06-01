package br.com.pratica.Juridica.PDFs;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@SessionScoped
@ManagedBean
public class FileBean {
	
	private StreamedContent file;
	
	public FileBean(){
	}
	
	public StreamedContent getFile() throws FileNotFoundException{
		String caminhoDoArquivo = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/pdf/Acordo_PraticaJuridica.pdf");
		InputStream stream = new FileInputStream(caminhoDoArquivo);
		file = new DefaultStreamedContent(stream, "application/pdf", "Acordo.pdf");
		return this.file;
	}
	public void setFile(StreamedContent file) {
		this.file = file;
	}

}
