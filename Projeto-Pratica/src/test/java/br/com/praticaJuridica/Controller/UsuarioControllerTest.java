package br.com.praticaJuridica.Controller;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.praticaJuridica.model.Usuario;

public class UsuarioControllerTest {

	@Test
	public void test() {
		UsuarioController usuarioController = new UsuarioController();
		String resultado = usuarioController.adiciona();
		assertSame("/view/menuAdmin?faces-redirect=true", resultado);
		
		resultado = usuarioController.alterar();
		assertSame("/view/menuAdmin?faces-redirect=true", resultado);
		
		Usuario usuario = usuarioController.getUsuario();
		
		assertNotNull(usuario);
	}

}
