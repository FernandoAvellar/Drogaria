package br.com.fernando.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.fernando.drogaria.dao.ProdutoDAO;
import br.com.fernando.drogaria.domain.Produto;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ProdutoBean implements Serializable {
	
	private Produto produto;
	private List<Produto> produtos;
	
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
	 
	@PostConstruct 
	public void listar() {
		try {
			produtos = new ProdutoDAO().listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao listar os produtos");
			erro.printStackTrace();
		}
	}
	
}
