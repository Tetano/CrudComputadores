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
import submodelHardware.Gabinete;

public class ComputadorDAO implements DAO<Computador> {
	// Para implementar o DAO gen�rico utilize implements DAO<ClasseDesejada>

// A ideia do dao � organziar e separar a camada de modelo do controlador
	// � preferencialmente feita para reutiliza��o.
	@Override
	public void inserir(Computador obj) throws Exception { // Ainda estou inserindo computador
		Connection conn = DAO.getConnection();

		Exception exception = null;

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO ");
		sql.append("computador ");
		sql.append("  (placa_mae, processador, placa_video, memoria, fonte, gabinete, data_montagem, preco, estoque) ");
		sql.append("VALUES ");
		sql.append("  ( ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
		PreparedStatement stat = null;

		try {
			conn.setAutoCommit(false);
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, obj.getPlacaMae());
			stat.setString(2, obj.getProcessador());
			stat.setString(3, obj.getPlacaDeVideo()); // Usa o get para definir o nome
			stat.setString(4, obj.getMemoria());
			stat.setString(5, obj.getFonte());
			if (obj.getGabinete() == null) {
				return;
			}
			stat.setInt(6, obj.getGabinete().getId());

			// Convertendo um Obj LocalDate para SQLDate
			stat.setDate(7, obj.getDataMontagem() == null ? null : Date.valueOf(obj.getDataMontagem()));
			stat.setDouble(8, obj.getPreco());
			stat.setInt(9, obj.getEstoque());
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

		if (exception != null)
			throw exception;

	}

	@Override
	public void alterar(Computador obj) throws Exception {
		Connection conn = DAO.getConnection();

		Exception exception = null;

		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE computador SET ");
		sql.append(" placa_mae = ?, ");
		sql.append(" processador = ?, ");
		sql.append(" placa_video = ?, ");
		sql.append(" memoria = ?, ");
		sql.append(" fonte = ?, ");
		sql.append(" gabinete = ?, ");
		sql.append(" data_montagem = ?, ");
		sql.append(" preco = ?, ");
		sql.append(" estoque = ? ");
		sql.append("WHERE ");
		sql.append(" id = ? ");

		PreparedStatement stat = null;

		try {
			conn.setAutoCommit(false);
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, obj.getPlacaMae());
			stat.setString(2, obj.getProcessador());
			stat.setString(3, obj.getPlacaDeVideo()); // Usa o get para definir o nome
			stat.setString(4, obj.getMemoria());
			stat.setString(5, obj.getFonte());
			if (obj.getGabinete() == null) {
				return;
			}
			stat.setInt(6, obj.getGabinete().getId());
			stat.setDate(7, obj.getDataMontagem() == null ? null : Date.valueOf(obj.getDataMontagem()));
			stat.setDouble(8, obj.getPreco());
			stat.setInt(9, obj.getEstoque());
			stat.setInt(10, obj.getId());

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
		if (exception != null)
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
		if (exception != null)
			throw exception;

	}

	@Override
	public List<Computador> obterTodos() throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();
		List<Computador> listaComputador = new ArrayList<Computador>();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		// sql.append(" c.*, "); -> Uso do * pode ser vi�vel quando n�o h� necessidade
		// de retornar um elemento em espec�fico.
		sql.append("  c.id, ");
		sql.append(" c.gabinete, ");
		sql.append(" c.placa_mae, ");
		sql.append(" c.processador, ");
		sql.append(" c.placa_video, ");
		sql.append(" c.memoria, ");
		sql.append(" c.fonte, ");
		sql.append(" c.data_montagem, ");
		sql.append(" c.preco, ");
		sql.append(" c.estoque ");
		sql.append("FROM ");
		sql.append("  computador c ");
		sql.append("ORDER BY c.id ");
		PreparedStatement stat = null;
		try {

			stat = conn.prepareStatement(sql.toString());
			ResultSet rs = stat.executeQuery();

			while (rs.next()) {
				Computador computador = new Computador();

				computador.setId(rs.getInt("id"));
				computador.setGabinete(Gabinete.valueOf(rs.getInt("gabinete")));
				computador.setProcessador(rs.getString("processador"));
				computador.setPlacaMae(rs.getString("placa_mae"));
				computador.setPlacaDeVideo(rs.getString("placa_video"));
				computador.setMemoria(rs.getString("memoria"));
				computador.setFonte(rs.getString("fonte"));
				computador.setPreco(rs.getDouble("preco"));
				computador.setEstoque(rs.getInt("estoque"));
				Date data = rs.getDate("data_montagem");
				computador.setDataMontagem(data == null ? null : data.toLocalDate());

				listaComputador.add(computador);

			}

		} catch (SQLException e) {
			Util.addMessage("N�o foi poss�vel buscar os dados");
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
		if (exception != null)
			throw exception;
		return listaComputador;
	}

	@Override
	public Computador obterUm(Computador obj) throws Exception {
		// TODO Auto-generated method stub
		Exception exception = null;
		Connection conn = DAO.getConnection();

		Computador computador = null;

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		// sql.append(" c.*, "); -> Uso do * pode ser vi�vel quando n�o h� necessidade
		// de retornar um elemento em espec�fico.
		sql.append("  id, ");
		sql.append(" gabinete, ");
		sql.append(" placa_mae, ");
		sql.append(" processador, ");
		sql.append(" placa_video, ");
		sql.append(" memoria, ");
		sql.append(" fonte, ");
		sql.append(" data_montagem, ");
		sql.append(" preco, ");
		sql.append(" estoque ");
		sql.append("FROM ");
		sql.append("  computador ");
		sql.append("WHERE computador.id = ? ");

		PreparedStatement stat = null;
		conn.setAutoCommit(false);
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, obj.getId());

			ResultSet rs = stat.executeQuery();

			while (rs.next()) {
				computador = new Computador();
				computador.setId(rs.getInt("id"));
				Date data = rs.getDate("data_montagem");
				computador.setDataMontagem(data == null ? null : data.toLocalDate());
				computador.setGabinete(Gabinete.valueOf(rs.getInt("gabinete")));
				computador.setProcessador(rs.getString("processador"));
				computador.setPlacaMae(rs.getString("placa_mae"));
				computador.setPlacaDeVideo(rs.getString("placa_video"));
				computador.setMemoria(rs.getString("memoria"));
				computador.setFonte(rs.getString("fonte"));
				computador.setEstoque(rs.getInt("estoque"));
				computador.setPreco(rs.getDouble("preco"));

			}

		} catch (SQLException e) {
			Util.addMessage("N�o foi possivel buscar os dados do usuario.");
			e.printStackTrace();
			exception = new Exception("Erro ao executar um sql em UsuarioDAO.");
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
