package controller;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import model.Computador;
import dao.ComputadorDAO;
import dao.DAO;
import application.Util;


@Named
@ViewScoped
public class ComputadorController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7748328763730744281L;
	private Computador computador;
	List<Computador> listaComputador;
	int id = 0;


	public void incluir() {
		ComputadorDAO dao = new ComputadorDAO();
		dao.inserir(computador);
		Util.addMessage("Inclusão realizada com sucesso.");
		Util.addMessage("Erro ao realizar o rollback.");
		Util.addMessage("Problema ao fechar conn");
		Util.addMessage("Problema ao fechar statement");
		limpar();
	}

	public void alterar() {
		int index = getListaComputador().indexOf(getComputador());
		getListaComputador().set(index, getComputador());
		return;
	}
	
	public void editar(Computador pc) {
		setComputador(pc);
		Util.addMessage("Item "+pc.getId().toString() + "Selecionado");
	}

	public void excluir() {
		int index = getListaComputador().indexOf(getComputador());
		getListaComputador().remove(index);
		limpar();
		return;
	}
	
	public void ValidarCpf() {
		if(computador.getCpf().isBlank()||computador.getCpf().isEmpty()) {
			return;
		}
		if(computador.getCpf().length()<11) {
			Util.addMessage("Verifique se o CPF possui 11 digitos, digite sem . ou - apenas números");
			return;
		}
		
	}
	
	
	
	public void limpar() {
		computador = null;
		listaComputador = null; // Para efetuar a nova conexão sem dar problema de não buscar o usuário

	}

	public Computador getComputador() {
		if (computador == null) {
			computador = new Computador();
		}
		return computador;
	}

	public void setComputador(Computador computador) {
		this.computador = computador;
	}

	public List<Computador> getListaComputador() {
		if (listaComputador == null) {
			listaComputador = new ArrayList<Computador>();
			Connection conn = DAO.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("  c.id, ");
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
					computador.setCpf(rs.getString("cpf"));
					computador.setPlacaMae(rs.getString("placa_mae"));
					computador.setProcessador(rs.getString("processador"));
					computador.setPlacaDeVideo(rs.getString("placa_video"));
					computador.setMemoria(rs.getString("memoria"));
					computador.setFonte(rs.getString("fonte"));

					listaComputador.add(computador);
			
				}
				
			} catch (SQLException e) {
				Util.addMessage("Não foi possível buscar os dados");
				e.printStackTrace();
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
		}
		return listaComputador;
	}

	public void setListaComputador(List<Computador> listaComputador) {
		this.listaComputador = listaComputador;
	}

}
