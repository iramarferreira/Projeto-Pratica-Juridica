package br.com.praticaJuridica.Controller;



import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.praticaJuridica.model.Usuario;


@ManagedBean
@SessionScoped
public class LoginController {
	
	
	static boolean logado;
	private static String nomeLogin;
	private static String senha;
	
	public LoginController(){
		logado = false;
	}
	
	
	public static String login() {
		Usuario usuario = new Usuario();
		UsuarioController usuarioController = new UsuarioController();
		FacesContext context = FacesContext.getCurrentInstance();
		
		
		usuario = usuarioController.buscaLogin(nomeLogin,senha);
		
		if(usuario != null){
			if (usuario.getTipoUsuario().equals("administrador")) {
				logado = true;
				
				return "/view/menuAdmin?faces-redirect=true";
			} 
			if (usuario.getTipoUsuario().equals("normal")) {
				logado = true;
				
 				return "/view/menuUsuario?faces-redirect=true";
			} 
			
		}else {
			FacesMessage mensagem = new FacesMessage(
					"Usuário e/ou senha inválidos!");
					mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
					context.addMessage(null, mensagem);
		}
		return null;
	}
	
	public String getNomeLogin() {
		return nomeLogin;
	}
	public void setNomeLogin(String nomeLogin) {
		this.nomeLogin = nomeLogin;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext()
		.invalidateSession();
		logado = false;
		return "/login?faces-redirect=true";
	}
	
	public void validarLogin() throws IOException{
		System.out.println("Validar Login");
        if (!logado){
        	FacesContext.getCurrentInstance().getExternalContext().redirect("../login.xhtml");
        }
        
        
    }
		
		

}
