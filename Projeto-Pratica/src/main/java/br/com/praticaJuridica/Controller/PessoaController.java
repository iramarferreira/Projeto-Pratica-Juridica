package br.com.praticaJuridica.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.praticaJuridica.DAO.PessoaJpaDAO;
import br.com.praticaJuridica.model.Endereco;
import br.com.praticaJuridica.model.Pessoa;


@ManagedBean
@SessionScoped
public class PessoaController {

	private Pessoa pessoa;
	private PessoaJpaDAO pessoaJpaDao;
	private Endereco endereco;
	
	public PessoaController(){
		this.pessoaJpaDao = PessoaJpaDAO.getInstance();
		this.pessoa = new Pessoa();
		this.endereco = new Endereco();
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public PessoaJpaDAO getPessoaJpaDao() {
		return pessoaJpaDao;
	}
	public void setPessoaJpaDao(PessoaJpaDAO pessoaJpaDao) {
		this.pessoaJpaDao = pessoaJpaDao;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public String adiciona(){
		this.pessoaJpaDao.adicionaEndereco(this.endereco);
		this.endereco = new Endereco();
		this.pessoaJpaDao.persist(this.pessoa);
		this.pessoa = new Pessoa();
		return "/view/menuAdmin?faces-redirect=true";
	}

	public List<Pessoa> getListar(){
		List<Pessoa> pessoas = pessoaJpaDao.findAll();
		/*List<Pessoa> resultado = new ArrayList<Pessoa>();
		for(Pessoa pessoa : pessoas){
			pessoa.setEndereco(pessoaJpaDao.getByIdEndereco(pessoa.getEndereco().getId()));
			resultado.add(pessoa);
		}*/
		
		return pessoas;
	}
	
	public String remover(){
		this.pessoaJpaDao.removeById(this.pessoa.getId());
		this.pessoa = new Pessoa();
		return "/view/menuAdmin?faces-redirect=true";
	}
	
	public String buscaAltera(){
		
		this.pessoa = this.pessoaJpaDao.getById(this.pessoa.getId());
		if(this.pessoa != null){
			this.endereco = this.pessoaJpaDao.getByIdEndereco(this.pessoa.getEndereco().getId());
			return "/view/alterarPessoa?faces-redirect=true";
		}
		else{
			this.pessoa = new Pessoa();
			return "/view/menuAdmin?faces-redirect=true";
		}
	}
	
	public String getAlterar(){
		try{
			this.pessoaJpaDao.getMerge(this.pessoa);
			pessoa = new Pessoa();
			this.pessoaJpaDao.getMergeEndereco(this.endereco);
			endereco = new Endereco();
		}catch(Exception e){
			this.pessoa = new Pessoa();
		}
		return "/view/menuAdmin?faces-redirect=true";
	}
	
	
	
}
