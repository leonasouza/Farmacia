package br.com.farmacia.bean;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.farmacia.DAO.FornecedoresDAO;
import br.com.farmacia.DAO.ProdutosDAO;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.domain.Produtos;
import br.com.farmacia.util.JSFUtil;

@ManagedBean(name = "MBProdutos")
@ViewScoped
public class ProdutosBean {
	
	private List<Produtos> itens;
	private List<Produtos> itensFiltrados;
	private Produtos produtos;
	private List<Fornecedores> comboFornecedores;

	public Produtos getProdutos() {
		return produtos;
	}

	public void setProdutos(Produtos produtos) {
		this.produtos = produtos;
	}
	
	public List<Produtos> getItens() {
		return itens;
	}
	
	public void setItens(List<Produtos> itens) {
		this.itens = itens;
	}
	
	public List<Produtos> getItensFiltrados() {
		return itensFiltrados;
	}
	
	public void setItensFiltrados(List<Produtos> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}
	
	public List<Fornecedores> getComboFornecedores() {
		return comboFornecedores;
	}
	
	public void setComboFornecedores(List<Fornecedores> comboFornecedores) {
		this.comboFornecedores = comboFornecedores;
	}
	
	@PostConstruct
	public void prepararPesquisa() {		
		try {
			ProdutosDAO pdao = new ProdutosDAO();
			itens = pdao.listar();
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemDeErro("Erro ao pesquisar: "+ e);
		}
	}
	
	public void prepararNovo() {
		produtos = new Produtos();
		FornecedoresDAO fdao = new FornecedoresDAO();
		try {
			comboFornecedores = fdao.listar();
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemDeErro("Erro ao preparar: "+ e);
		}
	}
	
	public void novoProduto() {
		try {
			ProdutosDAO pdao = new ProdutosDAO();
			pdao.salvar(produtos);
			itens = pdao.listar();
			JSFUtil.adicionarMensagemDeSucesso("Produto cadastrado com sucesso.");
		} catch (SQLException e) {
			System.out.println("Erro: "+ e);
		}
	}
	
	public void excluir() {
		try {
			ProdutosDAO pdao = new ProdutosDAO();
			pdao.excluir(produtos);
			itens = pdao.listar();
			JSFUtil.adicionarMensagemDeSucesso("Produto excluído com sucesso.");
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemDeErro("Não foi possível excluir o produto.");
		}
	}
	
	public void editar() {
		try {
			ProdutosDAO pdao = new ProdutosDAO();
			pdao.editar(produtos);
			itens = pdao.listar();
			JSFUtil.adicionarMensagemDeSucesso("Produto editado com sucesso.");
		} catch (SQLException e) {
			System.out.println("Erro ao editar:  "+ e);
		}
	}

}
