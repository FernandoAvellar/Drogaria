package br.com.fernando.drogaria.dao;

import java.util.List;
import org.junit.Ignore;
import org.junit.Test;
import br.com.fernando.drogaria.domain.Fabricante;

public class FabricanteDAOTest {
	
	@Test
	@Ignore
	public void salvar() {
		Fabricante fabricante = new Fabricante();
		fabricante.setDescricao("FarmaLab");
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		fabricanteDAO.salvar(fabricante);
	}
	
	@Test
	@Ignore
	public void listar() {
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		List<Fabricante> resultado = fabricanteDAO.listar();
		System.out.println("Numero de registros encontrados: " + resultado.size());
		
		for (Fabricante fabricante : resultado) {
			System.out.println("Codigo: " +fabricante.getCodigo()+ " Descrição: " +fabricante.getDescricao());		
		}
	}
	
	@Test
	public void buscar(){
		Long codigo = 1L;
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(codigo);
		
		if (fabricante == null){
			System.out.println("Fabricante não encontrado!");
		}else {
			System.out.println(fabricante.getCodigo() + " - " + fabricante.getDescricao());		
		}
	}
}
