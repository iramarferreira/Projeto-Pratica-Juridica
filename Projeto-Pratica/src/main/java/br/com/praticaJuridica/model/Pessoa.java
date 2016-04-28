package br.com.praticaJuridica.model;

import java.util.Calendar;
import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="pessoa")
public class Pessoa {

	@Id
	@GeneratedValue
	@Column(name="id_pessoa")
	private int id;
	@Column(name="nome_pessoa")
	private String nome;
	@Column(name="cpf_pessoa")
	private String cpf;
	@Column(name="rg_pessoa")
	private String rg;
	@Column(name="data_nascimento")
	private Date dataNascimento;
	@Column(name="tipo_pessoa")
	private String tipoPessoa;
	@Column(name="celular")
	private String celular;
	@ManyToOne
	@JoinColumn(name="id_endereco")
	private Endereco endereco;
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	
	public Pessoa(){
		this.usuario = new Usuario();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome(){
		return nome;
	}
	public void setNome(String nome){
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
			
	}
	public String getTipoPessoa() {
		return tipoPessoa;
	}
	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public Usuario getUsuario(){
		return usuario;
	}
	public void setUsuario(Usuario usuario){
		this.usuario = usuario;
	}
	
}
