package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import application.Util;
import model.Computador;

public class ComputadorDAO implements DAO<Computador> {
	// Para implementar o DAO genêrico utilize implements DAO<ClasseDesejada>
	
	

	@Override
	public void inserir(Computador obj) {
		// TODO Auto-generated method stub
		Connection conn = DAO.getConnection();

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO ");
		sql.append("computador ");
		sql.append("  (cpf, placa_mae, processador, placa_video, memoria, fonte) ");
		sql.append("VALUES ");
		sql.append("  ( ?, ?, ?, ?, ?, ?) ");
		PreparedStatement stat = null;
		try {

			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, obj.getCpf()); // Usa o get para definir o nome
			//stat.setObject(2, computador.getDataCompra());
			stat.setString(2, obj.getPlacaMae());
			stat.setString(3, obj.getPlacaDeVideo());
			stat.setString(4, obj.getProcessador()); // Usa o get para definir o nome
			stat.setString(5, obj.getMemoria());
			stat.setString(6, obj.getFonte());
			//stat.setString(8, computador.getGabinete());
			conn.setAutoCommit(false);
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
	public List<Computador> obterTodos() {
		// TODO Auto-generated method stub
		return null;
	}





}
