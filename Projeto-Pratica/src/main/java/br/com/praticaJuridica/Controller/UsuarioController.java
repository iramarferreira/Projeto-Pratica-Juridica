package br.com.praticaJuridica.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.praticaJuridica.DAO.UsuarioJpaDAO;
import br.com.praticaJuridica.model.Usuario;



@ManagedBean
@SessionScoped
public class UsuarioController {

	private Usuario usuario;
	private UsuarioJpaDAO usuarioJpaDao;
	
	public UsuarioController(){
		this.usuario = new Usuario();
		usuarioJpaDao = UsuarioJpaDAO.getInstance();
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public UsuarioJpaDAO getUsuarioJpaDao() {
		return usuarioJpaDao;
	}
	public void setUsuarioJpaDao(UsuarioJpaDAO usuarioJpaDao) {
		this.usuarioJpaDao = usuarioJpaDao;
	}
	
	public String adiciona(){
		this.usuarioJpaDao.persist(this.usuario);
		this.usuario = new Usuario();
		return "/view/menuAdmin?faces-redirect=true";
	}

	public List<Usuario> getListar(){
		return usuarioJpaDao.findAll();
	}
	
	public List<Usuario> getListarAdmins(){
//		List<Usuario> usuarios = usuarioJpaDao.findAll();
//		
//		for(Usuario usuario : usuarios){
//			if(usuario.getTipoUsuario().equals("normal")){
//				usuarios.remove(usuario);
//			}
//		}
//		return usuarios;
		return usuarioJpaDao.findAdmin();
	}
	
	public String remover(){
		this.usuarioJpaDao.removeById(this.usuario.getId());
		this.usuario = new Usuario();
		return "/view/menuAdmin?faces-redirect=true";
	}
	
	public Usuario buscaLogin(String nomeLogin, String senha){
		List<Usuario> usuarios = getListar();
		for(Usuario usuarioBd : usuarios){
    		
    		if(usuarioBd.getLogin().equals(nomeLogin) && (usuarioBd.getSenha().equals(senha))){
    			return usuarioBd;
    		}
    			
    	}
    	return null;
		
	}
	
	public String buscaAltera(){
		
		this.usuario = this.usuarioJpaDao.getById(this.usuario.getId());
		if(this.usuario != null){
			return "/view/alterarUsuario?faces-redirect=true";
		}
		else{
			this.usuario = new Usuario();
			return "/view/menuAdmin?faces-redirect=true";
		}
	}
	
	public String alterar(){
		try{
			this.usuarioJpaDao.getMerge(this.usuario);
			usuario = new Usuario();
		}catch(Exception e){
			this.usuario = new Usuario();
		}
		return "/view/menuAdmin?faces-redirect=true";
	}
	
	
}
