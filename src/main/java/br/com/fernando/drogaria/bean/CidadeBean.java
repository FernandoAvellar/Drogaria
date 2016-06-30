package br.com.fernando.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.fernando.drogaria.dao.CidadeDAO;
import br.com.fernando.drogaria.dao.EstadoDAO;
import br.com.fernando.drogaria.domain.Cidade;
import br.com.fernando.drogaria.domain.Estado;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CidadeBean implements Serializable {
	
	private Cidade cidade;
	private List<Cidade> cidades;
	private List<Estado> estados;
	
	public Cidade getCidade() {
		return cidade;
	}
	
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
	public List<Cidade> getCidades() {
		return cidades;
	}
	
	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}
	
	public List<Estado> getEstados() {
		return estados;
	}
	
	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}
	

	@PostConstruct
	public void listar(){
		try {
			cidades = new CidadeDAO().listar();
			
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar as cidades");
			erro.printStackTrace();
		}
	}
	
	public void novo(){
		 try {		 
			 cidade = new Cidade();
			 estados = new EstadoDAO().listar("nome");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar gerar uma nova cidade");
			erro.printStackTrace();
		}
	}
	
	public void salvar(){
		try {
			new CidadeDAO().merge(cidade);
			novo();
			listar();
			Messages.addFlashGlobalInfo("Cidade salva com sucesso");
			
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar a cidade");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento){
		cidade = (Cidade) evento.getComponent().getAttributes().get("cidadeSelecionada");
		try {
			new CidadeDAO().excluir(cidade);
			listar();
			Messages.addGlobalInfo("Cidade excluída com sucesso");
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao excluir a cidade");
			erro.printStackTrace();
		}	
	}	
	
	public void editar(ActionEvent evento){
		 try {		 
			 cidade = (Cidade) evento.getComponent().getAttributes().get("cidadeSelecionada");
			 estados = new EstadoDAO().listar("nome");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar uma cidade");
			erro.printStackTrace();
		}	
	}
}
