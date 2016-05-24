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
@Table(name="causa")
public class Causa {

	@Id
	@GeneratedValue
	@Column(name="id_causa")
	private int id;
	@Column(name="natureza_causa")
	private String natureza;
	@Column(name="parte_contraria")
	private String parteContraria;
	@Column(name="interesse_ajuizar")
	private String interesseAjuizar;
	@Column(name="data_atendimento")
	private Date dataAtendimento;
	@Column(name="status")
	private String status;
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	@ManyToOne
	@JoinColumn(name="id_pessoa")
	private Pessoa pessoa;
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private Cliente cliente;
	
	public Causa(){
		this.usuario = new Usuario();
		this.cliente = new Cliente();
		this.pessoa = new Pessoa();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNatureza() {
		return natureza;
	}

	public void setNatureza(String natureza) {
		this.natureza = natureza;
	}

	public String getParteContraria() {
		return parteContraria;
	}

	public void setParteContraria(String parteContraria) {
		this.parteContraria = parteContraria;
	}

	public String getInteresseAjuizar() {
		return interesseAjuizar;
	}

	public void setInteresseAjuizar(String interesseAjuizar) {
		this.interesseAjuizar = interesseAjuizar;
	}

	public Date getDataAtendimento() {
		return dataAtendimento;
	}

	public void setDataAtendimento(Date dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	
}
