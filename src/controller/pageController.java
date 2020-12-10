package controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import application.Session;
import application.Util;
import dao.DAO;
import dao.UsuarioDAO;
import model.Usuario;

@Named
@ViewScoped
public class pageController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4087953053809487318L;
	

	
	public void encerrarSessao() {
		Session.getInstance().invalidateSession();
	}

	public Usuario getUsuarioLogado() {
		Object obj = Session.getInstance().getAttribute("usuarioLogado");
		if(obj == null)
			return null;
		return (Usuario) obj;
	}


	

}