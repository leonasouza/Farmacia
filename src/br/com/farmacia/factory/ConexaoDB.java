package br.com.farmacia.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {

	private static final String USUARIO = "root";
	private static final String SENHA = "1234";
	private static final String URL = "jdbc:mysql://localhost:3306/sistema?useTimezone=true&serverTimezone=UTC";
	
	public static Connection conectar() throws SQLException {
		Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
		return conexao;
	}
	
	public static void main(String[] args) {
		try {
			ConexaoDB.conectar();
			System.out.println("Conectado");
		} catch (SQLException e) {
			System.out.println("Erro ao conectar: "+ e);
		}
	}
}
