package br.com.praticaJuridica.Controller;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.praticaJuridica.model.Pessoa;
import br.com.praticaJuridica.model.Usuario;

public class PessoaControllerTest {

	@Test
	public void test() {
		PessoaController pessoaController = new PessoaController();
	
		
		String resultado = pessoaController.getAlterar();
		assertSame("/view/menuAdmin?faces-redirect=true", resultado);
		
		Pessoa pessoa = pessoaController.getPessoa();
		
		assertNotNull(pessoa);
	}

}
