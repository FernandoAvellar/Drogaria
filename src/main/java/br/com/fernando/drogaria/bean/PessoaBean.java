package br.com.fernando.drogaria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;

import br.com.fernando.drogaria.dao.CidadeDAO;
import br.com.fernando.drogaria.dao.EstadoDAO;
import br.com.fernando.drogaria.dao.PessoaDAO;
import br.com.fernando.drogaria.domain.Cidade;
import br.com.fernando.drogaria.domain.Estado;
import br.com.fernando.drogaria.domain.Pessoa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PessoaBean implements Serializable {
	
	private Pessoa pessoa;
	private List<Pessoa> pessoas;
	
	private List<Cidade> cidades;
	
	private Estado estado;
	private List<Estado> estados;
	
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public List<Pessoa> getPessoas() {
		return pessoas;
	}
	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
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
	
	public Estado getEstado() {
		return estado;
	}
	
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	
	@PostConstruct 
	public void listar() {
		try {
			pessoas = new PessoaDAO().listar("nome");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao listar as pessoas");
			erro.printStackTrace();
		}
	}
	
	public void novo(){
		try{
			pessoa = new Pessoa();
			estados = new EstadoDAO().listar("nome");
			cidades = new ArrayList<Cidade>();
			estado = new Estado();
			
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar gerar uma nova pessoa");
			erro.printStackTrace();
		}
	}
	
	public void salvar(){
		try {
			new PessoaDAO().merge(pessoa);
			listar();
			novo();
			Messages.addFlashGlobalInfo("Pessoa salva com sucesso");
			
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar a pessoa");
			erro.printStackTrace();
		}
	}
	
	
	public void excluir(ActionEvent evento){
		pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada");
		try {
			new PessoaDAO().excluir(pessoa);
			listar();
			Messages.addGlobalInfo("Pessoa excluída com sucesso");
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao excluir a pessoa");
			erro.printStackTrace();
		}	
	}	
	
	public void editar(ActionEvent evento){
		 try {		 
			 pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada");
			 
			 estados = new EstadoDAO().listar("nome");
			 estado = pessoa.getCidade().getEstado();
			 cidades = new CidadeDAO().buscarPorEstado(estado.getCodigo());
			 
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar uma pessoa");
			erro.printStackTrace();
		}	
	}
	
	public void popular() {
		try {
			if (estado != null) {
				cidades = new CidadeDAO().buscarPorEstado(estado.getCodigo());
			} else {
				cidades = new ArrayList<>();
			}
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar filtrar as cidades");
			erro.printStackTrace();
		}
	}
}
	

