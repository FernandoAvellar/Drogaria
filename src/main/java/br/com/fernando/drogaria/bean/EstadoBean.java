package br.com.fernando.drogaria.bean;

import javax.faces.bean.ManagedBean;
import org.omnifaces.util.Messages;

@ManagedBean //Anotação pra dizer que é uma classe que cuidará do controle e do modelo dentro do nosso projeto
public class EstadoBean {
	
	public void salvar(){
		
//		String texto = "Programação Java WEB";
//		FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, texto, texto);
//		
//		FacesContext contexto = FacesContext.getCurrentInstance(); //captura o contexto do JSF
//		contexto.addMessage(null, mensagem);					   //insere a mensagem nesse contexto
		
		//O Omnifaces substitui as 4 linhas acima com apenass essa abaixo
		Messages.addGlobalInfo("Programação Web com Java");
	}

}
