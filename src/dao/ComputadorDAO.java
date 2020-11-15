package dao;

import java.sql.Connection;
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
	
	

	@Override
	public String inserir(Computador obj) {
		Connection conn = DAO.getConnection();

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO ");
		sql.append("computador ");
		sql.append("  (cpf, placa_mae, processador, placa_video, memoria, fonte, gabinete) ");
		sql.append("VALUES ");
		sql.append("  ( ?, ?, ?, ?, ?, ?, ?) ");
		PreparedStatement stat = null;
		try {

			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, obj.getCpf()); // Usa o get para definir o nome
			//stat.setObject(2, computador.getDataCompra());
			stat.setString(2, obj.getPlacaMae());
			stat.setString(3, obj.getProcessador()); 
			stat.setString(4, obj.getPlacaDeVideo());		// Usa o get para definir o nome
			stat.setString(5, obj.getMemoria());
			stat.setString(6, obj.getFonte());
			if(obj.getGabinete() == null) {
				return "Erro ao inserir Objeto";
			}
			stat.setInt(7, obj.getGabinete().getId());
			conn.setAutoCommit(false);
			stat.execute();
			// efetivando a transacao
			conn.commit();
			return 	"Inclusão realizada com sucesso.";

		} catch (SQLException e) {
			e.printStackTrace();
			// cancelando a transacao
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println("Erro ao realizar o rollback.");
				e1.printStackTrace();
				return "Erro ao realizar o rollback";
			}
		} finally {
			try {
				if (!stat.isClosed())
					stat.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar o Statement");
				e.printStackTrace();
				return "Problema ao fechar statement";
			}

			try {
				if (!conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				System.out.println("Erro a o fechar a conexao com o banco.");
				e.printStackTrace();
				return "Problema ao fechar conn";
			}
		}
		return null;

	}

	@Override
	public void alterar(Computador obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluir(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Computador> obterTodos() throws Exception {
		Exception exception = null;
		List<Computador> listaComputador = new ArrayList<Computador>();
		Connection conn = DAO.getConnection();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  c.id, ");
		sql.append(" c.gabinete, ");
		sql.append(" c.cpf, ");
		sql.append(" c.placa_mae, ");
		sql.append(" c.processador, ");
		sql.append(" c.placa_video, ");
		sql.append(" c.memoria, ");
		sql.append(" c.fonte ");
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





}
