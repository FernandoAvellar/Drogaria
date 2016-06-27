package br.com.fernando.drogaria.dao;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import br.com.fernando.drogaria.domain.Cliente;

public class ClienteDAOTest {
	
	@Test
	@Ignore
	public void salvar(){
		Cliente cliente = new Cliente();
		cliente.setDataCadastro(new Date());
		cliente.setLiberado(true);
		cliente.setPessoa(new PessoaDAO().buscar(1L));
		
		new ClienteDAO().salvar(cliente);
	}

}
