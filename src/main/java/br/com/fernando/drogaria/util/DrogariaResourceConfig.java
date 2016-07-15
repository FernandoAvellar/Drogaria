package br.com.fernando.drogaria.util;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

//http://localhost:8080/Drogaria/[NomeRest]

@ApplicationPath("rest")
public class DrogariaResourceConfig extends ResourceConfig {
	
	//Contrutor padrão apontando para o pacote contendo os serviços RESTFull
	public DrogariaResourceConfig() {
		packages("br.com.fernando.drogaria.service");
	}
}
