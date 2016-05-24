package br.com.praticaJuridica.Controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.praticaJuridica.DAO.ClienteJpaDAO;
import br.com.praticaJuridica.model.Cliente;
import br.com.praticaJuridica.model.Endereco;

@ManagedBean
@SessionScoped
public class ClienteController {
	
	private Cliente cliente;
	private ClienteJpaDAO clienteJpaDao;
	private Endereco endereco;
	
	public ClienteController(){
		this.cliente = new Cliente();
		this.clienteJpaDao = ClienteJpaDAO.getInstance();
		this.endereco = new Endereco();
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public ClienteJpaDAO getClienteJpaDao() {
		return clienteJpaDao;
	}
	public void setClienteJpaDao(ClienteJpaDAO clienteJpaDao) {
		this.clienteJpaDao = clienteJpaDao;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public String adiciona(){
		this.clienteJpaDao.adicionaEndereco(this.endereco);
		this.endereco = new Endereco();
		this.clienteJpaDao.persist(this.cliente);
		this.cliente = new Cliente();
		
		return LoginController.login();
	}

	public List<Cliente> getListar(){
		List<Cliente> clientes = clienteJpaDao.findAll();
		/*List<Pessoa> resultado = new ArrayList<Pessoa>();
		for(Pessoa pessoa : pessoas){
			pessoa.setEndereco(pessoaJpaDao.getByIdEndereco(pessoa.getEndereco().getId()));
			resultado.add(pessoa);
		}*/
		
		return clientes;
	}
	
	public String remover(){
		this.clienteJpaDao.removeById(this.cliente.getId());
		this.cliente = new Cliente();
		return LoginController.login();
	}
	
	public String buscaAltera(){
		
		this.cliente = this.clienteJpaDao.getById(this.cliente.getId());
		if(this.cliente != null){
			this.endereco = this.clienteJpaDao.getByIdEndereco(this.cliente.getEndereco().getId());
			return "/view/alterarCliente?faces-redirect=true";
		}
		else{
			this.cliente = new Cliente();
			return LoginController.login();
		}
	}
	
	public String getAlterar(){
		try{
			this.clienteJpaDao.getMerge(this.cliente);
			cliente = new Cliente();
			this.clienteJpaDao.getMergeEndereco(this.endereco);
			endereco = new Endereco();
		}catch(Exception e){
			this.cliente = new Cliente();
		}
		return LoginController.login();
	}
	

}
