package br.com.praticaJuridica.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="cliente")
public class Cliente {
	
	@Id
	@GeneratedValue
	@Column(name="id_cliente")
	private int id;
	@Column(name="nome_cliente")
	private String nome;
	@Column(name="cpf_cliente")
	private String cpf;
	@Column(name="rg_cliente")
	private String rg;
	@Column(name="data_nascimento")
	private Date dataNascimento;
	@Column(name="sexo")
	private String sexo;
	@Column(name="escolaridade")
	private String escolaridade;
	@Column(name="profissao")
	private String profissao;
	@Column(name="renda")
	private double renda;
	@Column(name="celular")
	private String celular;
	@Column(name="num_integrante_familia")
	private int numIntegranteFamilia;
	@ManyToOne
	@JoinColumn(name="id_endereco")
	private Endereco endereco;
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	
	public Cliente(){
		this.usuario = new Usuario();
		
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
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

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(String escolaridade) {
		this.escolaridade = escolaridade;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public double getRenda() {
		return renda;
	}

	public void setRenda(double renda) {
		this.renda = renda;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public int getNumIntegranteFamilia() {
		return numIntegranteFamilia;
	}

	public void setNumIntegranteFamilia(int numIntegranteFamilia) {
		this.numIntegranteFamilia = numIntegranteFamilia;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	

}
