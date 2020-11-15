package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import model.Computador;

public interface DAO<T> {
	// É utilizada para gerar implementações genêricas focando na reutilização de código.
	
	public String inserir(T obj);
	public void alterar(T obj);
	public void excluir(Integer id);
	public List<T> obterTodos() throws Exception;
	
public static Connection getConnection() {
		
		// Abre a conexão
		Connection conn = null;
		//Tentativa com try p/ executar código
			
			try {
				// 1- Registrar Driver Do PostGre	
				Class.forName("org.postgresql.Driver");
				
				// estabelecendo a conexão com banco de dados/ endereço/nome/senha				
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
