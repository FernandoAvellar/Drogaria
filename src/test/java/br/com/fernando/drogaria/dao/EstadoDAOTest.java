package br.com.fernando.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.fernando.drogaria.domain.Estado;

public class EstadoDAOTest {
	@Test
	@Ignore
	public void salvar(){
		Estado estado = new Estado();
		estado.setNome("Pará");
		estado.setSigla("PA");
		
		EstadoDAO estadoDAO = new EstadoDAO();
		estadoDAO.salvar(estado);
	}
	
	@Test
	@Ignore
	public void listar(){
		EstadoDAO estadoDAO = new EstadoDAO();
		List<Estado> resultado = estadoDAO.listar();
		
		for(Estado estado : resultado){
			System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());
		}
	}
	
	@Test
	@Ignore
	public void buscar(){
		Long codigo = 2L;
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigo);
		
		if (estado == null){
			System.out.println("Estado não encontrado!");
		}else {
			System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());		
		}
	}
	
	@Test
	@Ignore
	public void excluir(){
		Long codigo = 10L;
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigo);
		if(estado != null) {
			estadoDAO.excluir(estado);
			System.out.println("Registro de Estado removido:");
			System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());
		} else {
			System.out.println("Nenhum registro de estado encontrado!");
		}	
	}
	
	@Test
	@Ignore
	public void editar(){
		Long codigo = 10L;
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigo);
		
		if (estado != null){
			System.out.println("Registro de Estado antes da edição:");
			System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());
			
			estado.setNome("São Paulo");
			estado.setSigla("SP");
			estadoDAO.editar(estado);
			
			System.out.println("Registro de Estado editado:");
			System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());
		} else {
			System.out.println("Nenhum registro de estado encontrado!");
			
		}
		
		
	}
}
