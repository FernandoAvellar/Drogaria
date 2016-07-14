package br.com.fernando.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.fernando.drogaria.dao.PessoaDAO;
import br.com.fernando.drogaria.dao.UsuarioDAO;
import br.com.fernando.drogaria.domain.Pessoa;
import br.com.fernando.drogaria.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {
	
	private Usuario usuario;
	private List<Usuario> usuarios;
	private List<Pessoa> pessoas;
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	@PostConstruct
	public void listar(){
		try{
			usuarios = new UsuarioDAO().listar("tipo");
		}catch(RuntimeException erro){
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os usuários");
			erro.printStackTrace();
		}
	}
	
	public void novo() {
		try {
			usuario = new Usuario();
			pessoas = new PessoaDAO().listar("nome");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar criar um novo usuário");
			erro.printStackTrace();
		}
	}
	
	public void salvar() {
		try {
			new UsuarioDAO().merge(usuario);;
			listar();
			novo();
			
			Messages.addGlobalInfo("Usuário salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o usuário");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento){
		usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");
		try {
			new UsuarioDAO().excluir(usuario);
			listar();
			Messages.addGlobalInfo("Usuário excluído com sucesso");
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao excluir o usuário");
			erro.printStackTrace();
		}	
	}	
	
	public void editar(ActionEvent evento){
		 try {		 
			 usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");
			 usuarios = new UsuarioDAO().listar("tipo");
			 pessoas = new PessoaDAO().listar("nome");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar um usuário");
			erro.printStackTrace();
		}	
	}
	
}
