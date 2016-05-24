package br.com.praticaJuridica.Controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Query;

import br.com.praticaJuridica.DAO.CausaJpaDAO;
import br.com.praticaJuridica.model.Causa;
import br.com.praticaJuridica.model.Cliente;
import br.com.praticaJuridica.model.Endereco;
import br.com.praticaJuridica.model.Usuario;

@ManagedBean
@SessionScoped
public class CausaController {
	
	private Causa causa;
	private CausaJpaDAO causaJpaDAO;

	public CausaController(){
		this.causa = new Causa();
		this.causaJpaDAO = CausaJpaDAO.getInstance();
	}

	public Causa getCausa() {
		return causa;
	}

	public void setCausa(Causa causa) {
		this.causa = causa;
	}

	public CausaJpaDAO getCausaJpaDAO() {
		return causaJpaDAO;
	}

	public void setCausaJpaDAO(CausaJpaDAO causaJpaDAO) {
		this.causaJpaDAO = causaJpaDAO;
	}
	
	
	public String adiciona(){
		this.causaJpaDAO.insert(this.causa);
		this.causa = new Causa();
		return LoginController.login();
	}

	public List<Causa> getListar(){
		List<Causa> causas = causaJpaDAO.findAll();
		/*List<Pessoa> resultado = new ArrayList<Pessoa>();
		for(Pessoa pessoa : pessoas){
			pessoa.setEndereco(pessoaJpaDao.getByIdEndereco(pessoa.getEndereco().getId()));
			resultado.add(pessoa);
		}*/
		
		return causas;
	}
	
	public String remover(){
		this.causaJpaDAO.remove(this.causa.getId());
		this.causa = new Causa();
		return LoginController.login();
	}
	
	public String buscaAltera(){
		
		this.causa = this.causaJpaDAO.getById(this.causa.getId());
		if(this.causa != null){
			return "/view/alterarCausa?faces-redirect=true";
		}
		else{
			this.causa = new Causa();
			return LoginController.login();
		}
		
	}
	
	public String alterar(){
		
		try{
			causaJpaDAO.update(this.causa);
			this.causa = new Causa();
			
		}catch(Exception e){
			this.causa = new Causa();
		}
		return LoginController.login();
	}
	
	

}
