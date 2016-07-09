package br.com.fernando.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;
import br.com.fernando.drogaria.dao.FabricanteDAO;
import br.com.fernando.drogaria.dao.ProdutoDAO;
import br.com.fernando.drogaria.domain.Fabricante;
import br.com.fernando.drogaria.domain.Produto;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ProdutoBean implements Serializable {
	
	private Produto produto;
	private List<Produto> produtos;
	private List<Fabricante> fabricantes;
	
	 public Produto getProduto() {
		return produto;
	}
	 
	 public void setProduto(Produto produto) {
		this.produto = produto;
	}
	 
	 public List<Produto> getProdutos() {
		return produtos;
	}
	 
	 public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	 
	 public List<Fabricante> getFabricantes() {
		return fabricantes;
	}
	 
	 public void setFabicantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}
	 
	@PostConstruct 
	public void listar() {
		try {
			produtos = new ProdutoDAO().listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao listar os produtos");
			erro.printStackTrace();
		}
	}

	public void novo(){
		try{
			produto = new Produto();
			fabricantes = new FabricanteDAO().listar("descricao");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar gerar um novo fabricante");
			erro.printStackTrace();
		}
	}
	
	public void salvar(){
		try {
			new ProdutoDAO().merge(produto);
			novo();
			listar();
			Messages.addFlashGlobalInfo("Produto salvo com sucesso");
			
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar o produto");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento){
		produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
		try {
			new ProdutoDAO().excluir(produto);
			listar();
			Messages.addGlobalInfo("Produto excluído com sucesso");
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao excluir o produto");
			erro.printStackTrace();
		}	
	}	
	
	public void editar(ActionEvent evento){
		 try {		 
			 produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
			 produtos = new ProdutoDAO().listar();
			 fabricantes = new FabricanteDAO().listar("descricao");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar um produto");
			erro.printStackTrace();
		}	
	}
	
}
