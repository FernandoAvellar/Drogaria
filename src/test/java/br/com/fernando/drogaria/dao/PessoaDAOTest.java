package br.com.fernando.drogaria.dao;

import org.junit.Ignore;
import org.junit.Test;

import br.com.fernando.drogaria.domain.Pessoa;

public class PessoaDAOTest {
	
	@Test
	@Ignore
	public void salvar(){
		
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Paulo Amaral");
		pessoa.setCpf("033.456.666-63");
		pessoa.setRg("123.334.556");
		pessoa.setRua("Rua dos Funcionários");
		pessoa.setNumero(new Short("123"));
		pessoa.setBairro("Recreação");
		pessoa.setCep("12243-000");
		pessoa.setComplemento("APTO: 23");
		pessoa.setCelular("(12)98897-3003");
		pessoa.setTelefone("(12)3921-9724");
		pessoa.setEmail("paulo@hotmail.com");
		pessoa.setCidade(new CidadeDAO().buscar(2L));
		
		new PessoaDAO().salvar(pessoa);
		
	}

}
