package br.com.farmacia.teste;

import java.sql.SQLException;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.farmacia.DAO.ProdutosDAO;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.domain.Produtos;

public class TesteProdutosDAO {

	@Test
	@Ignore
	public void salvarTeste() throws SQLException {
		Produtos produto = new Produtos();
		Fornecedores fornecedor = new Fornecedores();

		fornecedor.setCodigo(12);

		produto.setDescricao("Dipirona");
		produto.setPreco(4.99);
		produto.setQuantidade(50);
		produto.setFornecedor(fornecedor);

		ProdutosDAO pdao = new ProdutosDAO();
		pdao.salvar(produto);
	}

	@Test
	@Ignore
	public void listarTeste() throws SQLException {
		ProdutosDAO pdao = new ProdutosDAO();
		List<Produtos> lista = pdao.listar();
		
		for (Produtos p : lista) {
			System.out.println("Código do produto: "+ p.getCodigo());
			System.out.println("Descrição do produto: "+ p.getDescricao());
			System.out.println("Preço do produto: "+ p.getPreco());
			System.out.println("Quantidade do produto: "+ p.getQuantidade());
			System.out.println("Código do fornecedor: "+ p.getFornecedor().getCodigo());
			System.out.println("Descrição do fornecedor: "+ p.getFornecedor().getDescricao());
			System.out.println("");
		}
		
	}
	
	@Test
	@Ignore
	public void excluirTeste() throws SQLException {
		Produtos produto = new Produtos();
		produto.setCodigo(4);
		
		ProdutosDAO pdao = new ProdutosDAO();
		pdao.excluir(produto);
	}
	
	@Test
	@Ignore
	public void editarTeste() throws SQLException {
		Produtos produto = new Produtos();
		Fornecedores fornecedor = new Fornecedores();
		produto.setCodigo(1);
		produto.setDescricao("Teste");
		produto.setQuantidade(55);
		produto.setPreco(99.99);
		fornecedor.setCodigo(13);
		produto.setFornecedor(fornecedor);
		
		ProdutosDAO pdao = new ProdutosDAO();
		pdao.editar(produto);
	}

}
