package br.com.fernando.drogaria.dao;

import java.math.BigDecimal;

import org.junit.Ignore;
import org.junit.Test;

import br.com.fernando.drogaria.domain.Fabricante;
import br.com.fernando.drogaria.domain.Produto;

public class ProdutoDAOTest {
	
	@Test
	@Ignore
	public void salvar() {
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(5L);
		
		Produto produto = new Produto();
		produto.setDescricao("Cataflan 50mg com 20 comprimidos");
		produto.setFabricante(fabricante);
		produto.setPreco(new BigDecimal("13.00"));
		produto.setQuantidade(new Short("25"));
		
		new ProdutoDAO().salvar(produto);
	}

}
