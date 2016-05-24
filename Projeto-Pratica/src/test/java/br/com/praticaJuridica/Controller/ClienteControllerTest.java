package br.com.praticaJuridica.Controller;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import br.com.praticaJuridica.model.Cliente;
import br.com.praticaJuridica.model.Endereco;
import br.com.praticaJuridica.model.Pessoa;

public class ClienteControllerTest {

	@Test
	public void test() {
		
		ClienteController clienteController = new ClienteController();
		
		Endereco endereco = clienteController.getEndereco();
		assertNotNull(endereco);
		
		Cliente cliente = clienteController.getCliente();
		
		assertNotNull(cliente);
		
	
		
	}

}
