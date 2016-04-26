package br.com.praticaJuridica.model;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


public class Acao {

	public Acao() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue
	@Column(name="id_acao")
	private int idAcao;
	@Column(name="natureza_acao")
	private String naturezaAcao;
	@Column(name="parte_contraria")
	private String parteContraria;
	@Column(name="interesse_azuijar")
	private String interesseAzuijar;
	@Column(name="data_atendimento")
	private Date dataAtendimento;
	
	public int getIdAcao() {
		return idAcao;
	}
	public void setIdAcao(int idAcao) {
		this.idAcao = idAcao;
	}

	public String getNaturezaAcao() {
		return naturezaAcao;
	}

	public void setNaturezaAcao(String naturezaAcao) {
		this.naturezaAcao = naturezaAcao;
	}

	public String getParteContraria() {
		return parteContraria;
	}

	public void setParteContraria(String parteContraria) {
		this.parteContraria = parteContraria;
	}

	public String getInteresseAzuijar() {
		return interesseAzuijar;
	}

	public void setInteresseAzuijar(String interesseAzuijar) {
		this.interesseAzuijar = interesseAzuijar;
	}

	public Date getDataAtendimento() {
		return dataAtendimento;
	}

	public void setDataAtendimento(Date dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}

	
}
