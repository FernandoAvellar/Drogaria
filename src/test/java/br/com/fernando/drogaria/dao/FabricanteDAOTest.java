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
	@Ignore
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

	@Test
	public void excluir(){
		Long codigo = 3L;
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(codigo);

		if (fabricante != null){
			fabricanteDAO.excluir(fabricante);
			System.out.println("Registro de fabricante removido:");
			System.out.println(fabricante.getCodigo() + " - " + fabricante.getDescricao());
		} else {
			System.out.println("Nenhum registro de fabricante encontrado!");
		}
	}
	
	@Test
	@Ignore
	public void editar(){
		Long codigo = 3L;
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = new FabricanteDAO().buscar(codigo);
		
		if(fabricante != null){
			System.out.println("Registro de fabricante antes da edição:");
			System.out.println(fabricante.getCodigo() + " - " + fabricante.getDescricao());
			
			fabricante.setDescricao("Orion");
			fabricanteDAO.editar(fabricante);
			
			System.out.println("Registro de fabricante editado:");
			System.out.println(fabricante.getCodigo() + " - " + fabricante.getDescricao());
		}else {
			System.out.println("Nenhum registro de fabricante encontrado!");
		}
	}

}

