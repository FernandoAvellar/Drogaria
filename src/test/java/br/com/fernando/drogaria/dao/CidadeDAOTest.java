package br.com.fernando.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.fernando.drogaria.domain.Cidade;
import br.com.fernando.drogaria.domain.Estado;

public class CidadeDAOTest {

	@Test
	@Ignore
	public void salvar(){
		//Buscando a chave estrangeira correspondente ao estado de SP
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(1L);

		Cidade cidade = new Cidade();
		cidade.setNome("Lavras");
		cidade.setEstado(estado);

		CidadeDAO cidadeDAO = new CidadeDAO();
		cidadeDAO.salvar(cidade);
	}
	
	@Test
	@Ignore
	public void listar(){
		CidadeDAO cidadeDAO = new CidadeDAO();
		List<Cidade> resultado = cidadeDAO.listar();
		
		for (Cidade cidade : resultado) {
			System.out.println("Codigo: " + cidade.getCodigo() + ", Nome: " + cidade.getNome()
			+ ", Estado: " + cidade.getEstado().getSigla());
		}
	}
	
	@Test
	@Ignore
	public void buscar(){
		Long codigo = 3L;
		
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigo);
		
		System.out.println("Codigo: " + cidade.getCodigo() + ", Nome: " + cidade.getNome()
		+ ", Estado: " + cidade.getEstado().getSigla());
	}
	
	@Test
	@Ignore
	public void excluir(){
		Long codigo = 4L;
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigo);
		cidadeDAO.excluir(cidade);	
	}
	
	@Test
	@Ignore
	public void editar(){
		Long codigoCidade = 5L;
		Long codigoEstado = 3L;
		
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigoCidade);
		
		Estado estado = new Estado();
		estado.setCodigo(codigoEstado);
		
		cidade.setEstado(estado);
		
		cidadeDAO.editar(cidade);
	}
	
	@Test
	@Ignore
	public void buscarPorEstado(){
		Long estadoCodigo = 3L;
		CidadeDAO cidadeDAO = new CidadeDAO();
		List<Cidade> resultado = cidadeDAO.buscarPorEstado(estadoCodigo);
		
		for (Cidade cidade : resultado) {
			System.out.println("Codigo: " + cidade.getCodigo() + ", Nome: " + cidade.getNome()
			+ ", Estado: " + cidade.getEstado().getSigla());
		}
	}
}
