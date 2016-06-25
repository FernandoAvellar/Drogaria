package br.com.fernando.drogaria.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity //Diz que a classe estado será uma entidade do Hibernate, ou seja, será gerada a tabela estado com os atributos: codigo, sigla e nome
public class Estado extends GenericDomain {
	
	@Column(length = 2, nullable = false) 	//garante que esse atributo não seja criado no banco como varchar de 255 caracteres e nem permite valores vazios
	private String sigla;
	@Column(length = 50, nullable = false)
	private String nome;

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
