package br.com.praticaJuridica.Controller;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import br.com.praticaJuridica.model.Causa;
import br.com.praticaJuridica.model.Pessoa;

public class CausaControllerTest {

	@Test
	public void test() {
		
		CausaController causaController = new CausaController();
		
		List<Causa> causas = causaController.getListar();
		assertNotSame(causaController.getListar(), causas);
		
		Causa causa = causaController.getCausa();
		
		assertNotNull(causa);
	}

}
