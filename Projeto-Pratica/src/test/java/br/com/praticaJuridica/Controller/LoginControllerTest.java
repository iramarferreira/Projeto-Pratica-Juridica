package br.com.praticaJuridica.Controller;

import static org.junit.Assert.*;

import org.junit.Test;

public class LoginControllerTest {

	@Test
	public void test() {
		LoginController loginController = new LoginController();
		
		assertNotNull(loginController.logado);
		
		
	}

}
