package br.com.fernando.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;
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
			fabricantes = new FabricanteDAO().listar();		
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
			new FabricanteDAO().merge(fabricante);
			novo();
			listar();
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

}
