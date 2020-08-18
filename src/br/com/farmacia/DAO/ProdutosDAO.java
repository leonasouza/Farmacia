package br.com.farmacia.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.domain.Produtos;
import br.com.farmacia.factory.ConexaoDB;

public class ProdutosDAO {

	public void salvar(Produtos produto) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO produtos ");
		sql.append("(descricao, quantidade, preco, fornecedores_codigo) ");
		sql.append("VALUES (?,?,?,?)");

		Connection conexao = ConexaoDB.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, produto.getDescricao());
		comando.setLong(2, produto.getQuantidade());
		comando.setDouble(3, produto.getPreco());
		comando.setLong(4, produto.getFornecedor().getCodigo());
		comando.executeUpdate();
	}
	
	public List<Produtos> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.codigo, p.descricao, p.preco, p.quantidade, f.codigo, f.descricao ");
		sql.append("FROM produtos p ");
		sql.append("INNER JOIN fornecedores f ON f.codigo = p.fornecedores_codigo");

		Connection conexao = ConexaoDB.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		ResultSet resultado = comando.executeQuery();

		List<Produtos> lista = new ArrayList<Produtos>();

		while (resultado.next()) {
			
			Fornecedores fornecedor = new Fornecedores();
			fornecedor.setCodigo(resultado.getLong("f.codigo"));
			fornecedor.setDescricao(resultado.getString("f.descricao"));
			Produtos produto = new Produtos();
			produto.setCodigo(resultado.getLong("p.codigo"));
			produto.setDescricao(resultado.getString("p.descricao"));
			produto.setPreco(resultado.getDouble("p.preco"));
			produto.setQuantidade(resultado.getLong("p.quantidade"));
			produto.setFornecedor(fornecedor);
			
			lista.add(produto);
		}

		return lista;
	}
	
	public void excluir(Produtos produto) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM produtos ");
		sql.append("WHERE codigo = ? ");
		
		Connection conexao = ConexaoDB.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, produto.getCodigo());
		comando.execute();
	}
	
	public void editar(Produtos produto) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE produtos ");
		sql.append("SET descricao = ?, preco = ?, quantidade = ?, fornecedores_codigo = ? ");
		sql.append("WHERE codigo = ? ");
		
		Connection conexao = ConexaoDB.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1,produto.getDescricao());
		comando.setDouble(2, produto.getPreco());
		comando.setLong(3, produto.getQuantidade());
		comando.setLong(4, produto.getFornecedor().getCodigo());
		comando.setLong(5, produto.getCodigo());
		comando.execute();
	}
	
}
