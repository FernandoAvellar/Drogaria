package br.com.fernando.drogaria.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import org.omnifaces.util.Messages;

import com.google.gson.Gson;

import br.com.fernando.drogaria.dao.FabricanteDAO;
import br.com.fernando.drogaria.domain.Fabricante;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class FabricanteBean implements Serializable {
	
	private Fabricante fabricante;
	private List<Fabricante> fabricantes;
	
	public Fabricante getFabricante() {
		return fabricante;
	}
	
	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}
	
	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}
	
	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}
	
	@PostConstruct
	public void listar(){
		try {
			//fabricantes = new FabricanteDAO().listar();	
			
			//Outra forma de se fazer desacoplando o front-end de acesso direto ao DAO, agora quem irá acessar o DAO será o serviço Rest.
			Client cliente = ClientBuilder.newClient();
			WebTarget caminho = cliente.target("http://127.0.0.1:8080/Drogaria/rest/fabricante");
			String json = caminho.request().get(String.class);
			
			Gson gson = new Gson();
			Fabricante[] vetor = gson.fromJson(json, Fabricante[].class);
			
			fabricantes = Arrays.asList(vetor);
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Falha ao carregar a lista de fabricantes");
			erro.printStackTrace();
		}
	}
	
	public void novo(){
		 fabricante = new Fabricante();
	}
	
	public void salvar(){
		try {
			//Implementação antiga onde o front end (Primefaces) acessa diretamente o backend
//			new FabricanteDAO().merge(fabricante);
//			novo();
//			listar();
			
			//Implementando o salvar com serviço rest, desacoplando o front-end
			
			Client cliente = ClientBuilder.newClient();
			WebTarget caminho = cliente.target("http://127.0.0.1:8080/Drogaria/rest/fabricante");
			Gson gson = new Gson();
			String json = gson.toJson(fabricante);
			caminho.request().post(Entity.json(json));
			
			//Limpar
			fabricante = new Fabricante();
			
			//Listar (recarregar)
			json = caminho.request().get(String.class);
			Fabricante[] vetor = gson.fromJson(json, Fabricante[].class);
			fabricantes = Arrays.asList(vetor);
			
			Messages.addGlobalInfo("Fabricante salvo com sucesso");
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao salvar o fabricante");
			erro.printStackTrace();
		}	
	}
	
	public void excluir(ActionEvent evento){
		fabricante = (Fabricante) evento.getComponent().getAttributes().get("fabricanteSelecionado");
		try {
			new FabricanteDAO().excluir(fabricante);
			listar();
			Messages.addGlobalInfo("Fabricante excluído com sucesso");
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao excluir o fabricante");
			erro.printStackTrace();
		}	
	}
	
	public void editar(ActionEvent evento){
		fabricante = (Fabricante) evento.getComponent().getAttributes().get("fabricanteSelecionado");	
	}
	
	public void salvar_editando(){
		try {
			
			Client cliente = ClientBuilder.newClient();
			WebTarget caminho = cliente.target("http://127.0.0.1:8080/Drogaria/rest/fabricante");
			Gson gson = new Gson();
			String json = gson.toJson(fabricante);
			caminho.request().put(Entity.json(json));
			
			//Limpar
			fabricante = new Fabricante();
			
			//Listar (recarregar)
			json = caminho.request().get(String.class);
			Fabricante[] vetor = gson.fromJson(json, Fabricante[].class);
			fabricantes = Arrays.asList(vetor);
			
			Messages.addGlobalInfo("Fabricante editado com sucesso");
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao editar o fabricante");
			erro.printStackTrace();
		}	
	}

}
