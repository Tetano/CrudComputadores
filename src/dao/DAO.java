package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import model.Computador;

public interface DAO<T> {
	// � utilizada para gerar implementa��es gen�ricas focando na reutiliza��o de c�digo.
	
	public String inserir(T obj);
	public void alterar(T obj);
	public void excluir(Integer id);
	public List<T> obterTodos() throws Exception;
	
public static Connection getConnection() {
		
		// Abre a conex�o
		Connection conn = null;
		//Tentativa com try p/ executar c�digo
			
			try {
				// 1- Registrar Driver Do PostGre	
				Class.forName("org.postgresql.Driver");
				
				// estabelecendo a conex�o com banco de dados/ endere�o/nome/senha				
				conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/lojacomputador", "topicos1", "123456");
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return conn;
		
	}
	
}
