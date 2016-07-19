package br.com.fernando.drogaria.dao;

import java.math.BigDecimal;

import org.junit.Ignore;
import org.junit.Test;

import br.com.fernando.drogaria.domain.ItemVenda;

public class ItemVendaDAOTest {
	
	@Test
	@Ignore
	public void salvar(){
		ItemVenda itemVenda = new ItemVenda();
		itemVenda.setProduto(new ProdutoDAO().buscar(1L));
		itemVenda.setQuantidade(new Short("10"));
		itemVenda.setValorParcial(new BigDecimal("130.00"));
		itemVenda.setVenda(new VendaDAO().buscar(1L));
		
		new ItemVendaDAO().salvar(itemVenda);
	}

}
