package br.com.fernando.drogaria.bean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.omnifaces.util.Messages;
import br.com.fernando.drogaria.dao.EstadoDAO;
import br.com.fernando.drogaria.domain.Estado;

@SuppressWarnings("serial")
@ManagedBean //Anotação pra dizer que é uma classe que cuidará do controle e do modelo dentro do nosso projeto
@ViewScoped //Os objetos ficarão validos enquanto estiver na tela do estado correspondente
public class EstadoBean implements Serializable {
	
	private Estado estado;
	
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public void novo(){
		estado = new Estado();
	}
	
	public void salvar(){	
		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			estadoDAO.salvar(estado);
			novo(); //Limpa o objeto
			Messages.addGlobalInfo("Estado salvo com sucesso");
		}catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao salvar o estado");
			erro.printStackTrace();
		}
	}
	
}
