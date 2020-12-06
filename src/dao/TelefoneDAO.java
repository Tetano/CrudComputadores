package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.Util;
import model.Telefone;
import submodelHardware.Gabinete;


public class TelefoneDAO implements DAO<Telefone> {
	// Para implementar o DAO genêrico utilize implements DAO<ClasseDesejada>
	
	
// A ideia do dao é organziar e separar a camada de modelo do controlador
	// é preferencialmente feita para reutilização.
	@Override
	public void inserir(Telefone obj) throws Exception { // Ainda estou inserindo telefone
		Connection conn = DAO.getConnection();
		
		Exception exception = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO ");
		sql.append("telefone ");
		sql.append("  (codigo_area, numero) ");
		sql.append("VALUES ");
		sql.append("  ( ?, ?) ");
		PreparedStatement stat = null;
		
		
		try {
			conn.setAutoCommit(false);
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, obj.getCodigoArea()); // Usa o get para definir o nome
			stat.setString(2, obj.getNumero());
			
			stat.execute();
			// efetivando a transacao
			conn.commit();


		} catch (SQLException e) {
			e.printStackTrace();
			// cancelando a transacao
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println("Erro ao realizar o rollback.");
				e1.printStackTrace();
			}
		} finally {
			try {
				if (!stat.isClosed())
					stat.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar o Statement");
				e.printStackTrace();
			}

			try {
				if (!conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				System.out.println("Erro a o fechar a conexao com o banco.");
				e.printStackTrace();
			}
		}
		
		if(exception != null)
			throw exception;

	}

	@Override
	public void alterar(Telefone obj) throws Exception {
Connection conn = DAO.getConnection();
		
		Exception exception = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE telefone SET ");
		sql.append(" codigo_area = ?, ");
		sql.append(" numero = ?, ");
		sql.append("WHERE ");
		sql.append(" id = ? ");
		
		
		PreparedStatement stat = null;
		
		try {
			conn.setAutoCommit(false);
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, obj.getCodigoArea()); // Usa o get para definir o nome
			stat.setString(2, obj.getNumero());
			stat.setInt(3, obj.getId());

			stat.execute();
			conn.commit();


		} catch (SQLException e) {
			e.printStackTrace();
			// cancelando a transacao
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println("Erro ao realizar o rollback.");
				e1.printStackTrace();

			}
		} finally {
			try {
				if (!stat.isClosed())
					stat.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar o Statement");
				e.printStackTrace();

			}

			try {
				if (!conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				System.out.println("Erro a o fechar a conexao com o banco.");
				e.printStackTrace();

			}
		}
		if(exception != null)
			throw exception;

	

	}

	@Override
	public void excluir(Telefone obj) throws Exception {
		// TODO Auto-generated method stub
Connection conn = DAO.getConnection();
		
		Exception exception = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM telefone WHERE id = ?");
		
		
		PreparedStatement stat = null;
		
		try {
			conn.setAutoCommit(false);
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, obj.getId());

			stat.execute();
			conn.commit();


		} catch (SQLException e) {
			e.printStackTrace();
			// cancelando a transacao
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println("Erro ao realizar o rollback.");
				e1.printStackTrace();

			}
		} finally {
			try {
				if (!stat.isClosed())
					stat.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar o Statement");
				e.printStackTrace();

			}

			try {
				if (!conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				System.out.println("Erro a o fechar a conexao com o banco.");
				e.printStackTrace();

			}
		}
		if(exception != null)
			throw exception;

	

		
	}

	@Override
	public List<Telefone> obterTodos() throws Exception {
		return new ArrayList<Telefone>();
	}

	@Override
	public Telefone obterUm(Telefone obj) throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();
		
		Telefone telefone = null;

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		//sql.append("  c.*, "); -> Uso do * pode ser viável quando não há necessidade de retornar um elemento em específico.
		sql.append("  t.id, ");
		sql.append(" t.codigo_area, ");
		sql.append(" t.numero, ");
		sql.append("FROM ");
		sql.append("  telefone t ");
		sql.append("ORDER BY t.id ");
		
		PreparedStatement stat = null;
		try {

			stat = conn.prepareStatement(sql.toString());


			ResultSet rs = stat.executeQuery();

			if (rs.next()) {
				telefone = new Telefone();
				telefone.setId(rs.getInt("id"));
				telefone.setCodigoArea(rs.getString("codigo_area")); 				
				telefone.setNumero(rs.getString("numero"));
	
			}

		} catch (SQLException e) {
			Util.addMessage("Não foi possivel buscar os dados do Telefone.");
			e.printStackTrace();
			exception = new Exception("Erro ao executar um sql em TelefoneDAO.");
		} finally {
			try {
				if (!stat.isClosed())
					stat.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar o Statement");
				e.printStackTrace();
			}

			try {
				if (!conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				System.out.println("Erro a o fechar a conexao com o banco.");
				e.printStackTrace();
			}
		}

		return telefone;
	}




}
