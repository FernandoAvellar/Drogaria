package br.com.fernando.drogaria.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Ignore;
import org.junit.Test;
import br.com.fernando.drogaria.domain.Funcionario;

public class FuncionarioDAOTest {
	
	@Test
	@Ignore
	public void salvar() throws ParseException{
		Funcionario funcionario = new Funcionario();
		funcionario.setCarteiraTrabalho("32245567");
		funcionario.setDataAdmissao(new SimpleDateFormat("dd/MM/yyyy").parse("02/07/2015"));
		funcionario.setPessoa(new PessoaDAO().buscar(2L));
		
		new FuncionarioDAO().salvar(funcionario);
	}

}
