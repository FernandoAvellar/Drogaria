package br.com.fernando.drogaria.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@SuppressWarnings("serial") //só pra ignorar warnings do tipo serial nessa classe - essa anotação não é do Hibernate
@MappedSuperclass //Notação pra dizer que essa classe não será uma tabela mais será usada por outros para gerar tabelas.
public class GenericDomain implements Serializable {
	@Id //Diz pro código que essa será uma chave primária no banco (pra todos que herdarem dessa tabela)
	@GeneratedValue(strategy = GenerationType.AUTO) //A chave primaria será gerenciada pelo banco e autoincrementada
	private Long codigo;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

}
