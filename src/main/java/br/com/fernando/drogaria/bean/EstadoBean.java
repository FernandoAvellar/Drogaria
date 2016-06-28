package br.com.fernando.drogaria.bean;

import javax.faces.bean.ManagedBean;
import org.omnifaces.util.Messages;

@ManagedBean //Anotação pra dizer que é uma classe que cuidará do controle e do modelo dentro do nosso projeto
public class EstadoBean {
	
	public void salvar(){

		Messages.addGlobalInfo("Programação Web com Java");
	}

}
