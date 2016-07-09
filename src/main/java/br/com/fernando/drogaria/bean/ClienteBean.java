package br.com.fernando.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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
	
	@PostConstruct 
	public void listar() {
		try {
			
			clientes = new ClienteDAO().listar("dataCadastro");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao listar os clientes");
			erro.printStackTrace();
		}
	}
	public List<Pessoa> getPessoas() {
		return pessoas;
	}
	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
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


}
