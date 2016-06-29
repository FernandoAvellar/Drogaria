package br.com.fernando.drogaria.bean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;
import br.com.fernando.drogaria.dao.EstadoDAO;
import br.com.fernando.drogaria.domain.Estado;

@SuppressWarnings("serial")
@ManagedBean //Anotação pra dizer que é uma classe que cuidará do controle e do modelo dentro do nosso projeto
@ViewScoped //Os objetos ficarão validos enquanto estiver na tela do estado correspondente
public class EstadoBean implements Serializable {
	
	private Estado estado;
	private List<Estado> estados;
	
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public List<Estado> getEstados() {
		return estados;
	}
	
	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}
	
	@PostConstruct //método que será chamado assim que o EstadoBean for construído
	public void listar(){
		try {
			estados = new EstadoDAO().listar();		
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os etsados");
			erro.printStackTrace();
		}
	}
	
	public void novo(){
		estado = new Estado();
	}
	
	public void salvar(){	
		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			estadoDAO.merge(estado);
			novo(); //Limpa o objeto
			listar();
			Messages.addGlobalInfo("Estado salvo com sucesso");
		}catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao salvar o estado");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento){
		estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");
		try {
			new EstadoDAO().excluir(estado);
			listar();
			Messages.addGlobalInfo("Estado excluído com sucesso");
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao excluir o estado");
			erro.printStackTrace();
		}	
	}	
	
	public void editar(ActionEvent evento){
		estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");	
	}
}
