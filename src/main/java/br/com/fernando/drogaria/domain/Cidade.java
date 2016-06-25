package br.com.fernando.drogaria.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Cidade extends GenericDomain {
	
	@Column(length = 50, nullable = false)
	private String nome;
	
	@ManyToOne //Diz que muitas cidades podem pertencer a apenas 1 estado
	@JoinColumn(nullable = false) //Personalizar propiedades de chave estrangeira .... não pode usar o @Column
	private Estado estado;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}	

}
