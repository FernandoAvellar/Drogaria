package br.com.fernando.drogaria.bean;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.fernando.drogaria.util.OsDetectionUtil;

//Criado pra possibilitar a exibição da foto, como criamos e mostramos tudo no mesmo local existe um BUG se não separarmos os BEAN

@ManagedBean
@RequestScoped //Recarrega a tela a cada clique pra corrigir o BUG do @ViewScoped necessário no ProdutoBean
public class ImagemBean {
	
	@ManagedProperty("#{param.caminho}") //Amarra o Bean com o .xhtml
	private String caminho;
	
	private StreamedContent foto;
	
	private static final String DIRETORIO_IMAGENS_UPLOAD = OsDetectionUtil.configuraCaminhoDiretorio();
	
	
	public String getCaminho() {
		return caminho;
	}
	
	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}
	
	public StreamedContent getFoto() throws IOException {
		if(caminho == null || caminho.isEmpty()){
			Path path = Paths.get(DIRETORIO_IMAGENS_UPLOAD);
			InputStream stream = Files.newInputStream(path);
			foto = new DefaultStreamedContent(stream);
		}else {
			Path path = Paths.get(caminho);
			InputStream stream = Files.newInputStream(path);
			foto = new DefaultStreamedContent(stream);
		}
		return foto;
	}
	
	public void setFoto(StreamedContent foto) {
		this.foto = foto;
	}
	
}
