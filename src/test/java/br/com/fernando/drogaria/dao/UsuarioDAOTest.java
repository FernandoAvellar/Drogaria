package br.com.fernando.drogaria.dao;

import org.junit.Test;

import br.com.fernando.drogaria.domain.Usuario;

public class UsuarioDAOTest {
	
	@Test
	public void salvar(){
		Usuario usuario = new Usuario();
		usuario.setAtivo(true);
		usuario.setPessoa(new PessoaDAO().buscar(2L));
		usuario.setSenha("123456");
		usuario.setTipo(new Character('F'));
		
		new UsuarioDAO().salvar(usuario);
	}

}
