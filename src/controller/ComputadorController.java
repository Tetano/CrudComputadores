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
import model.Gabinete;
import model.Usuario;
import dao.ComputadorDAO;
import dao.DAO;
import dao.UsuarioDAO;
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
		try {
			dao.inserir(computador);
		} catch(Exception e) {
			Util.addMessage("Não foi possível fazer a inclusão");
			e.printStackTrace();
		}
		limpar();
	}

	public void alterar() {
		ComputadorDAO dao = new ComputadorDAO();
		try {
			dao.alterar(getComputador());
			Util.addMessage("Alteração Realizada com sucesso");
			limpar();
		} catch (Exception e) {
			Util.addMessage("Não é possivel fazer uma alteração.");
			e.printStackTrace();
		}
	}
	
	public void editar(Computador pc) {
		setComputador(pc);
	}

	public void excluir() {
		int index = getListaComputador().indexOf(getComputador());
		getListaComputador().remove(index);
		limpar();
		return;
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
			ComputadorDAO dao = new ComputadorDAO();
			listaComputador = new ArrayList<Computador>();
			try {
				listaComputador = dao.obterTodos();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				listaComputador = new ArrayList<Computador>();
			}
			Connection conn = DAO.getConnection();

		}
		return listaComputador;
	}
	
	public Gabinete[] getListaGabinete() {
		return Gabinete.values();
	}

	public void setListaComputador(List<Computador> listaComputador) {
		this.listaComputador = listaComputador;
	}

}
