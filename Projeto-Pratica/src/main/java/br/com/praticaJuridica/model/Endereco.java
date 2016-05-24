package br.com.praticaJuridica.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="endereco")
public class Endereco {

	@Id
	@GeneratedValue
	@Column(name="id_endereco")
	private int id;
	@Column(name="cidade")
	private String cidade;
	@Column(name="uf_estado")
	private String ufEstado;
	@Column(name="cep")
	private String cep;
	@Column(name="rua")
	private String rua;
	@Column(name="bairro")
	private String bairro;
	@Column(name="num_casa")
	private int numCasa;
	
	public Endereco(){
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getUfEstado() {
		return ufEstado;
	}
	public void setUfEstado(String ufEstado) {
		this.ufEstado = ufEstado;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public int getNumCasa() {
		return numCasa;
	}
	public void setNumCasa(int numCasa) {
		this.numCasa = numCasa;
	}
	
}
