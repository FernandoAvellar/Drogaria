package br.com.fernando.drogaria.dao;

import java.math.BigDecimal;
import java.util.Date;
import org.junit.Ignore;
import org.junit.Test;

import br.com.fernando.drogaria.domain.Venda;

public class VendaDAOTest {
	
	@Test
	@Ignore
	public void salvar(){
		Venda venda = new Venda();
		venda.setFuncionario(new FuncionarioDAO().buscar(1L));
		venda.setCliente(new ClienteDAO().buscar(1L));
		venda.setHorario(new Date());
		venda.setValorParcial(new BigDecimal("130.00"));
		
		new VendaDAO().salvar(venda);
	}

}
