package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.Util;
import model.Computador;
import model.Gabinete;


public class ComputadorDAO implements DAO<Computador> {
	// Para implementar o DAO genêrico utilize implements DAO<ClasseDesejada>
	
	
// A ideia do dao é organziar e separar a camada de modelo do controlador
	// é preferencialmente feita para reutilização.
	@Override
	public void inserir(Computador obj) throws Exception { // Ainda estou inserindo computador
		Connection conn = DAO.getConnection();
		
		Exception exception = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO ");
		sql.append("computador ");
		sql.append("  (cpf, placa_mae, processador, placa_video, memoria, fonte, gabinete, data_compra) ");
		sql.append("VALUES ");
		sql.append("  ( ?, ?, ?, ?, ?, ?, ?, ?) ");
		PreparedStatement stat = null;
		
		
		try {
			conn.setAutoCommit(false);
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, obj.getCpf()); // Usa o get para definir o nome
			stat.setString(2, obj.getPlacaMae());
			stat.setString(3, obj.getProcessador()); 
			stat.setString(4, obj.getPlacaDeVideo());		// Usa o get para definir o nome
			stat.setString(5, obj.getMemoria());
			stat.setString(6, obj.getFonte());
			if(obj.getGabinete() == null) {
				return;
			}
			stat.setInt(7, obj.getGabinete().getId());
			
			//Convertendo um Obj LocalDate para SQLDate
			stat.setDate(8, obj.getDataCompra() == null ? null: Date.valueOf(obj.getDataCompra()));
			

			

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
	public void alterar(Computador obj) throws Exception {
Connection conn = DAO.getConnection();
		
		Exception exception = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE computador SET ");
		sql.append(" cpf = ?, ");
		sql.append(" placa_mae = ?, ");
		sql.append(" processador = ?, ");
		sql.append(" placa_video = ?, ");
		sql.append(" memoria = ?, ");
		sql.append(" fonte = ?, ");
		sql.append(" gabinete = ?, ");
		sql.append(" data_compra = ? ");
		sql.append("WHERE ");
		sql.append(" id = ? ");
		
		
		PreparedStatement stat = null;
		
		try {
			conn.setAutoCommit(false);
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, obj.getCpf()); // Usa o get para definir o nome
			stat.setString(2, obj.getPlacaMae());
			stat.setString(3, obj.getProcessador()); 
			stat.setString(4, obj.getPlacaDeVideo());		// Usa o get para definir o nome
			stat.setString(5, obj.getMemoria());
			stat.setString(6, obj.getFonte());
			if(obj.getGabinete() == null) {
				return;
			}
			stat.setInt(7, obj.getGabinete().getId());
			
			stat.setDate(8, obj.getDataCompra() == null ? null: Date.valueOf(obj.getDataCompra()));
			
			stat.setInt(9, obj.getId());

			

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
	public void excluir(Computador obj) throws Exception {
		// TODO Auto-generated method stub
Connection conn = DAO.getConnection();
		
		Exception exception = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM computador WHERE id = ?");
		
		
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
	public List<Computador> obterTodos() throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();
		List<Computador> listaComputador = new ArrayList<Computador>();
	

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		//sql.append("  c.*, "); -> Uso do * pode ser viável quando não há necessidade de retornar um elemento em específico.
		sql.append("  c.id, ");
		sql.append(" c.gabinete, ");
		sql.append(" c.cpf, ");
		sql.append(" c.placa_mae, ");
		sql.append(" c.processador, ");
		sql.append(" c.placa_video, ");
		sql.append(" c.memoria, ");
		sql.append(" c.fonte, ");
		sql.append(" c.data_compra ");
		sql.append("FROM ");
		sql.append("  computador c ");
		sql.append("ORDER BY c.id ");
		PreparedStatement stat = null;
		try {
			
			stat = conn.prepareStatement(sql.toString());
			ResultSet rs = stat.executeQuery();
			
			while(rs.next()) {
				Computador computador = new Computador();
				computador.setId(rs.getInt("id"));
				computador.setGabinete(Gabinete.valueOf(rs.getInt("gabinete"))); 				
				computador.setCpf(rs.getString("cpf"));
				computador.setProcessador(rs.getString("processador"));
				computador.setPlacaMae(rs.getString("placa_mae"));
				computador.setPlacaDeVideo(rs.getString("placa_video"));
				computador.setMemoria(rs.getString("memoria"));
				computador.setFonte(rs.getString("fonte"));
				Date data = rs.getDate("data_compra");
				computador.setDataCompra(data == null ? null : data.toLocalDate());
				

				listaComputador.add(computador);
		
			}
			
		} catch (SQLException e) {
			Util.addMessage("Não foi possível buscar os dados");
			e.printStackTrace();
			exception = new Exception("Erro ao executar um sql em computador DAO");
			// cancelando a transacao
		} finally {
			try {
				if (!stat.isClosed())
					stat.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar o Statement");
				Util.addMessage("Problema ao fechar conn");
				e.printStackTrace();
			}

			try {
				if (!conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				Util.addMessage("Problema ao fechar statement");
				System.out.println("Erro a o fechar a conexao com o banco.");
				e.printStackTrace();
			}
		}
		if(exception != null)
			throw exception;
		return listaComputador;
	}

	@Override
	public Computador obterUm(Computador obj) throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();
		
		Computador computador = null;

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		//sql.append("  c.*, "); -> Uso do * pode ser viável quando não há necessidade de retornar um elemento em específico.
		sql.append("  c.id, ");
		sql.append(" c.gabinete, ");
		sql.append(" c.cpf, ");
		sql.append(" c.placa_mae, ");
		sql.append(" c.processador, ");
		sql.append(" c.placa_video, ");
		sql.append(" c.memoria, ");
		sql.append(" c.fonte, ");
		sql.append(" c.data_compra ");
		sql.append("FROM ");
		sql.append("  computador c ");
		sql.append("ORDER BY c.id ");
		
		PreparedStatement stat = null;
		try {

			stat = conn.prepareStatement(sql.toString());


			ResultSet rs = stat.executeQuery();

			if (rs.next()) {
				computador = new Computador();
				computador.setId(rs.getInt("id"));
				computador.setGabinete(Gabinete.valueOf(rs.getInt("gabinete"))); 				
				computador.setCpf(rs.getString("cpf"));
				computador.setProcessador(rs.getString("processador"));
				computador.setPlacaMae(rs.getString("placa_mae"));
				computador.setPlacaDeVideo(rs.getString("placa_video"));
				computador.setMemoria(rs.getString("memoria"));
				computador.setFonte(rs.getString("fonte"));
				Date data = rs.getDate("data_compra");
				computador.setDataCompra(data == null ? null : data.toLocalDate());
			}

		} catch (SQLException e) {
			Util.addMessage("Não foi possivel buscar os dados do Computador.");
			e.printStackTrace();
			exception = new Exception("Erro ao executar um sql em ComputadorDAO.");
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

		return computador;
	}




}
