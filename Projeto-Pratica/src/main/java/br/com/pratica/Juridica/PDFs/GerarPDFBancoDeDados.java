package br.com.pratica.Juridica.PDFs;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.jboss.logging.Logger;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64.OutputStream;

import br.com.praticaJuridica.Controller.CausaController;
import br.com.praticaJuridica.model.Causa;

@SessionScoped
@ManagedBean
public class GerarPDFBancoDeDados {

	
	public void gerarPdf() throws IOException{
		
		Document document = null;
		FileOutputStream outPutStream = null;
		
		try{
			document = new Document(PageSize.A4,30,20,20, 30);
			outPutStream = new FileOutputStream("/home/iramar/TabelaBancoCausa.pdf");
			try{
				PdfWriter.getInstance(document, outPutStream);
				document.open();
				
				Font f = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD);
				Font f1 = new Font(FontFamily.TIMES_ROMAN, 12);
				
				String caminhoDoArquivo = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/imagens/ufrn1.png");
				Image img = Image.getInstance(caminhoDoArquivo);
				img.setAlignment(Element.ALIGN_CENTER);
				document.add(img);
				
				Paragraph ufrn = new Paragraph("\nUNIVERSIDADE FEDERAL DO RIO GRANDE DO NORTE\n"
						+ "CENTRO DE ENSINO SUPERIOR DO SERIDÓ\n"
						+ "DEPARTAMENTO DE DIREITO\n"
						+ "PRÁTICA JURÍDICA\n\n\n", f);
				ufrn.setAlignment(Element.ALIGN_CENTER);
				document.add(ufrn);
				
				PdfPTable tabela = new PdfPTable(new float[] {0.3f, 0.2f, 0.2f, 0.2f, 0.3f, 0.2f, 0.3f, 0.3f });
				PdfPCell cabecalho = new PdfPCell(new Paragraph("Relatório das causas cadastradas", f));
				cabecalho.setHorizontalAlignment(Element.ALIGN_CENTER);
				cabecalho.setBorder(PdfPCell.NO_BORDER);
				cabecalho.setBackgroundColor(new BaseColor(100, 150, 200));
				cabecalho.setColspan(8);
				tabela.addCell(cabecalho);
				tabela.setWidthPercentage(100.0f);	

				
				PdfPCell dataAtendimento = new PdfPCell(new Paragraph("Data de Atendimento",f));
				PdfPCell interesseAjuizar = new PdfPCell(new Paragraph("Interesse ajuizar?",f));
				PdfPCell naturezaCausa = new PdfPCell(new Paragraph("naturezaCausa",f));
				PdfPCell parteContraria = new PdfPCell(new Paragraph("Parte contrária",f));
				PdfPCell status = new PdfPCell(new Paragraph("Status",f));
				PdfPCell Cliente = new PdfPCell(new Paragraph("Cliente",f));
				PdfPCell responsavelEnvolvido = new PdfPCell(new Paragraph("Responsável envolvido",f));
				PdfPCell cadastradaPor = new PdfPCell(new Paragraph("Cadastrada por:",f));
				
				tabela.addCell(dataAtendimento);
				tabela.addCell(interesseAjuizar);
				tabela.addCell(naturezaCausa);
				tabela.addCell(parteContraria);
				tabela.addCell(status);
				tabela.addCell(Cliente);
				tabela.addCell(responsavelEnvolvido);
				tabela.addCell(cadastradaPor);
				
				CausaController causaController = new CausaController();
				Causa causa = new Causa();
				List<Causa> causas = causaController.getListar();
				
				for(int i = 0; i < causas.size(); i++){
					causa = causas.get(i);
					
					PdfPCell causaDataAtendimento = new PdfPCell(new Paragraph(new SimpleDateFormat("dd/MM/yyyy").format(causa.getDataAtendimento()),f1));
					PdfPCell causaInteresseAjuizar = new PdfPCell(new Paragraph(causa.getInteresseAjuizar(),f1));
					PdfPCell causaNaturezaCausa = new PdfPCell(new Paragraph(causa.getNatureza(),f1));
					PdfPCell causaParteContraria = new PdfPCell(new Paragraph(causa.getParteContraria(),f1));
					PdfPCell causaStatus = new PdfPCell(new Paragraph(causa.getStatus(),f1));
					PdfPCell causaCliente = new PdfPCell(new Paragraph(causa.getCliente().getNome(),f1));
					PdfPCell causaResponsavelEnvolvido = new PdfPCell(new Paragraph(causa.getPessoa().getNome(),f1));
					PdfPCell causaCadastradaPor = new PdfPCell(new Paragraph(causa.getUsuario().getLogin(),f1));
					
					tabela.addCell(causaDataAtendimento);
					tabela.addCell(causaInteresseAjuizar);
					tabela.addCell(causaNaturezaCausa);
					tabela.addCell(causaParteContraria);
					tabela.addCell(causaStatus);
					tabela.addCell(causaCliente);
					tabela.addCell(causaResponsavelEnvolvido);
					tabela.addCell(causaCadastradaPor);
				}
				
				document.add(tabela);
				
				Date dataAtual = new Date(System.currentTimeMillis());
				Paragraph dataRelatorio = new Paragraph("\n\nGerado em " + new SimpleDateFormat("dd/MM/yyyy").format(dataAtual) + 
						"\nSistema Integrado de Gerenciamento da Prática Jurídica", f1);
				dataRelatorio.setAlignment(Element.ALIGN_RIGHT);
				document.add(dataRelatorio);
				
				
				FacesMessage msg;
			    msg = new FacesMessage("PDF gerado");
			    FacesContext.getCurrentInstance().addMessage(null, msg);  
				
			}catch(DocumentException ex){
				Logger.getLogger(GerarPDFBancoDeDados.class.getName()).log(null, Level.SEVERE, null, ex);
			}
		}
		finally{
			if(document != null){
				document.close();
			}
			if(outPutStream != null){
				outPutStream.close();
			}
			
		}
		
	}
	

}
