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
		Fabricante fabricante = fabricanteDAO.buscar(3L);
		
		Produto produto = new Produto();
		produto.setDescricao("Novalgina gotas 10ml");
		produto.setFabricante(fabricante);
		produto.setPreco(new BigDecimal("12.20"));
		produto.setQuantidade(new Short("53"));
		
		new ProdutoDAO().salvar(produto);
	}

}
