package br.com.farmacia.bean;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.farmacia.DAO.FornecedoresDAO;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.util.JSFUtil;

@ManagedBean(name = "MBFornecedores")
@ViewScoped
public class FornecedoresBean { 
	
	private List<Fornecedores> itens;
	private List<Fornecedores> itensFiltrados;
	private Fornecedores fornecedores;

	public Fornecedores getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(Fornecedores fornecedores) {
		this.fornecedores = fornecedores;
	}
	
	public List<Fornecedores> getItens() {
		return itens;
	}
	
	public void setItens(List<Fornecedores> itens) {
		this.itens = itens;
	}
	
	public List<Fornecedores> getItensFiltrados() {
		return itensFiltrados;
	}
	
	public void setItensFiltrados(List<Fornecedores> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}
	
	@PostConstruct
	public void prepararPesquisa() {		
		try {
			FornecedoresDAO fdao = new FornecedoresDAO();
			itens = fdao.listar();
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemDeErro("Erro ao pesquisar: "+ e);
		}
	}
	
	public void prepararNovoFornecedor() {
		fornecedores = new Fornecedores();
	}
	
	public void novoFornecedor() {
		try {
			FornecedoresDAO fdao = new FornecedoresDAO();
			fdao.salvar(fornecedores);
			itens = fdao.listar();
			JSFUtil.adicionarMensagemDeSucesso("Fornecedor cadastrado com sucesso.");
		} catch (SQLException e) {
			System.out.println("Erro: "+ e);
		}
	}
	
	public void excluir() {
		try {
			FornecedoresDAO fdao = new FornecedoresDAO();
			fdao.excluir(fornecedores);
			itens = fdao.listar();
			JSFUtil.adicionarMensagemDeSucesso("Fornecedor excluído com sucesso.");
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemDeErro("Não é possível excluir um fornecedor com produtos associados. "
					+ "Exclua os produtos antes de excluir o fornecedor.");
		}
	}
	
	public void editar() {
		try {
			FornecedoresDAO fdao = new FornecedoresDAO();
			fdao.editar(fornecedores);
			itens = fdao.listar();
			JSFUtil.adicionarMensagemDeSucesso("Fornecedor editado com sucesso.");
		} catch (SQLException e) {
			System.out.println("Erro ao editar:  "+ e);
		}
	}
	
}
