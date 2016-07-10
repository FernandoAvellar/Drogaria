package br.com.fernando.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.fernando.drogaria.dao.ClienteDAO;
import br.com.fernando.drogaria.dao.PessoaDAO;
import br.com.fernando.drogaria.domain.Cliente;
import br.com.fernando.drogaria.domain.Pessoa;


@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable {
	
	private List<Cliente> clientes;
	private List<Pessoa> pessoas;
	private Cliente cliente;
	
	public List<Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public List<Pessoa> getPessoas() {
		return pessoas;
	}
	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
	
	
	
	@PostConstruct 
	public void listar() {
		try {
			
			clientes = new ClienteDAO().listar("dataCadastro");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao listar os clientes");
			erro.printStackTrace();
		}
	}

	public void novo(){
		try{
		cliente = new Cliente();
		pessoas = new PessoaDAO().listar("nome");
		
		}catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao listar os clientes");
			erro.printStackTrace();
		}
	}
	
	public void salvar(){
		try {
			new ClienteDAO().merge(cliente);
			listar();
			novo();
			Messages.addFlashGlobalInfo("Cliente salvo com sucesso");
			
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar o cliente");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento){
		cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");
		try {
			new ClienteDAO().excluir(cliente);
			listar();
			Messages.addGlobalInfo("Cliente excluído com sucesso");
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao excluir o cliente");
			erro.printStackTrace();
		}	
	}	
	
	public void editar(ActionEvent evento){
		 try {		 
			 cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");
			 clientes = new ClienteDAO().listar("dataCadastro");
			 pessoas = new PessoaDAO().listar("nome");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar um cliente");
			erro.printStackTrace();
		}	
	}


}
