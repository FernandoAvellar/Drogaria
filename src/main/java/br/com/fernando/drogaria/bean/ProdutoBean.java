package br.com.fernando.drogaria.bean;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.fernando.drogaria.dao.FabricanteDAO;
import br.com.fernando.drogaria.dao.ProdutoDAO;
import br.com.fernando.drogaria.domain.Fabricante;
import br.com.fernando.drogaria.domain.Produto;


@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ProdutoBean implements Serializable {
	
	private static final String diretorioParaSalvarAsImagensDosProdutos = "C:/Users/fernando.avellar/workspace/Drogaria/Drogaria/uploads/";
	
	private Produto produto;
	private List<Produto> produtos;
	private List<Fabricante> fabricantes;
	
	 public Produto getProduto() {
		return produto;
	}
	 
	 public void setProduto(Produto produto) {
		this.produto = produto;
	}
	 
	 public List<Produto> getProdutos() {
		return produtos;
	}
	 
	 public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	 
	 public List<Fabricante> getFabricantes() {
		return fabricantes;
	}
	 
	 public void setFabicantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}
	 
	@PostConstruct 
	public void listar() {
		try {
			produtos = new ProdutoDAO().listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao listar os produtos");
			erro.printStackTrace();
		}
	}

	public void novo(){
		try{
			produto = new Produto();
			fabricantes = new FabricanteDAO().listar("descricao");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar gerar um novo fabricante");
			erro.printStackTrace();
		}
	}
	
	public void salvar(){
		try {
			
			//Checar se foi feito o upload da foto antes de salvar, não funciona diretamente no .xhtml pra esse componente
			if(produto.getCaminho() == null){
				Messages.addFlashGlobalError("O campo foto é obrigatório");
				return;
			}
			
			Produto produtoRetorno = new ProdutoDAO().merge(produto);
			
			//Cópia da foto do produto pra area correta
			Path origem = Paths.get(produto.getCaminho());
			Path destino = Paths.get(diretorioParaSalvarAsImagensDosProdutos + produtoRetorno.getCodigo() + ".png");
			Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);
			
			novo();
			listar();
			Messages.addFlashGlobalInfo("Produto salvo com sucesso");
			
		} catch (RuntimeException | IOException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar o produto");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento){
		produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
		try {
			//Exclui o produto
			new ProdutoDAO().excluir(produto);
			listar();
			Messages.addGlobalInfo("Produto excluído com sucesso");
			
			//Excluir a foto do produto caso ela exista
			Path arquivo = Paths.get(diretorioParaSalvarAsImagensDosProdutos + produto.getCodigo() + ".png");
			Files.deleteIfExists(arquivo);
			
		} catch (RuntimeException | IOException erro) {
			Messages.addGlobalError("Ocorreu um erro ao excluir o produto");
			erro.printStackTrace();
		}	
	}	
	
	public void editar(ActionEvent evento){
		 try {		 
			 produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
			 produto.setCaminho(diretorioParaSalvarAsImagensDosProdutos + produto.getCodigo() + ".png");
			 produtos = new ProdutoDAO().listar();
			 fabricantes = new FabricanteDAO().listar("descricao");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar um produto");
			erro.printStackTrace();
		}	
	}
	
	public void upload(FileUploadEvent evento) {
		UploadedFile arquivoUpload = evento.getFile(); //Recebe o arquivo que foi feito upload e coloca em uma área temporária do Tomcat
		
		try {
			Path arquivoTemp = Files.createTempFile(null, null); //Cria um arquivo temporário no Sistema Operacional e devolve o caminho onde ele foi salvo	
			Files.copy(arquivoUpload.getInputstream(), arquivoTemp, StandardCopyOption.REPLACE_EXISTING); //Copia o arquivo que veio do upload pro sistema operacional
			produto.setCaminho(arquivoTemp.toString()); //Guarda dentro da variavel caminho do produto o local do S.O. onde foi salvo o arquivo
			Messages.addFlashGlobalInfo("Upload realizado com sucesso");
		} catch (IOException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar realizar o upload do arquivo");
			erro.printStackTrace();
			}  	
		}	
}
