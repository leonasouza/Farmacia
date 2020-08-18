package br.com.farmacia.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.factory.ConexaoDB;

public class FornecedoresDAO {

	public void salvar(Fornecedores fornecedor) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO fornecedores ");
		sql.append("(descricao) ");
		sql.append("VALUES (?)");

		Connection conexao = ConexaoDB.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, fornecedor.getDescricao());
		comando.executeUpdate();
	}
	
	public void excluir(Fornecedores fornecedor) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM fornecedores ");
		sql.append("WHERE codigo = ? ");
		
		Connection conexao = ConexaoDB.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, fornecedor.getCodigo());
		comando.execute();
	}
	
	public void editar(Fornecedores fornecedor) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE fornecedores ");
		sql.append("SET descricao = ? ");
		sql.append("WHERE codigo = ? ");
		
		Connection conexao = ConexaoDB.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1,fornecedor.getDescricao());
		comando.setLong(2, fornecedor.getCodigo());
		comando.execute();
	}
	
	public Fornecedores buscaPorCodigo(Fornecedores fornecedor) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM fornecedores ");
		sql.append("WHERE codigo = ?");
		
		Connection conexao = ConexaoDB.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, fornecedor.getCodigo());
		ResultSet resultado = comando.executeQuery();
		Fornecedores retorno = null;
		
		if (resultado.next()) {
			retorno = new Fornecedores();
			retorno.setCodigo(resultado.getLong("codigo"));
			retorno.setDescricao(resultado.getString("descricao"));
		}
		
		return retorno;
		
	}
	
	public List<Fornecedores> buscarPorDescricao(Fornecedores fornecedor) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM fornecedores ");
		sql.append("WHERE descricao LIKE ? ");
		sql.append("ORDER BY descricao ASC");
		
		Connection conexao = ConexaoDB.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, "%"+ fornecedor.getDescricao() +"%");
		ResultSet resultado = comando.executeQuery();
		ArrayList<Fornecedores> lista = new ArrayList<Fornecedores>();
		
		while(resultado.next()) {
			Fornecedores item = new Fornecedores();
			item.setCodigo(resultado.getLong("codigo"));
			item.setDescricao(resultado.getString("descricao"));
			lista.add(item);
		}
		
		return lista;		
	}
	
	public List<Fornecedores> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM fornecedores ");
		sql.append("ORDER BY descricao ASC");
		
		Connection conexao = ConexaoDB.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Fornecedores> lista = new ArrayList<Fornecedores>();
		
		while(resultado.next()) {
			Fornecedores fornecedor = new Fornecedores();
			fornecedor.setCodigo(resultado.getLong("codigo"));
			fornecedor.setDescricao(resultado.getString("descricao"));
			lista.add(fornecedor);
		}
		
		return lista;
	}
	
	public static void main(String[] args) throws SQLException {
		
		/* salvar
		Fornecedores f1 = new Fornecedores();
		f1.setDescricao("lucas");
		Fornecedores f2 = new Fornecedores();
		f2.setDescricao("jose");
		
		FornecedoresDAO fdao = new FornecedoresDAO();
		fdao.salvar(f1);
		fdao.salvar(f2);
		*/
		
		/* excluir
		Fornecedores f1 = new Fornecedores();
		f1.setCodigo(2);
		FornecedoresDAO fdao = new FornecedoresDAO();
		fdao.excluir(f1);
		*/
		
		/* editar
		Fornecedores f1 = new Fornecedores();
		f1.setDescricao("testado1");
		f1.setCodigo(1);
		FornecedoresDAO fdao = new FornecedoresDAO();
		fdao.editar(f1);
		*/
		
		/* buscar por codigo
		Fornecedores f1 = new Fornecedores();
		f1.setCodigo(9);
		FornecedoresDAO fdao = new FornecedoresDAO();
		System.out.println(fdao.buscaPorCodigo(f1));
		*/
		
		/*
		Fornecedores fornecedor = new Fornecedores();
		fornecedor.setDescricao("teste");
		FornecedoresDAO fdao = new FornecedoresDAO();
		try {
			ArrayList<Fornecedores> lista = fdao.buscarPorDescricao(fornecedor);
			for (Fornecedores f : lista) {
				System.out.println(f);
			}
		} catch(SQLException e) {
			System.out.println("erro ao listar: "+ e);
		}
		*/
		
		
		/* listar
		FornecedoresDAO fdao = new FornecedoresDAO();
		try {
			ArrayList<Fornecedores> lista = fdao.listar();
			for (Fornecedores f : lista) {
				System.out.println(f);
			}
		} catch(SQLException e) {
			System.out.println("erro ao listar: "+ e);
		}
		*/
		
	}

}
