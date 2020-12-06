package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
	// � utilizada para gerar implementa��es gen�ricas focando na reutiliza��o de c�digo.
	
	public void inserir(T obj) throws Exception;
	public void alterar(T obj) throws Exception;
	public void excluir(T obj) throws Exception;
	public List<T> obterTodos() throws Exception;
	public T obterUm(T obj) throws Exception;
	
public static Connection getConnection() {
		
		// Abre a conex�o
		Connection conn = null;
		//Tentativa com try p/ executar c�digo
			
			try {
				// 1- Registrar Driver Do PostGre	
				Class.forName("org.postgresql.Driver");
				
				// estabelecendo a conex�o com banco de dados/ endere�o/nome/senha				
				conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/lojacomputador", "topicos1", "123456");
				conn.setAutoCommit(false);
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
