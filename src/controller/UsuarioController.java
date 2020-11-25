package controller;

import java.io.Serializable;

import dao.UsuarioDAO;
import model.Usuario;

public class UsuarioController extends Controller<Usuario> implements Serializable {

	private static final long serialVersionUID = -3955368378894625110L;
	
	public UsuarioController() {
		super(new UsuarioDAO());
	}

	public Usuario getEntity() {
		if (entity == null)
			entity = new Usuario();
		return entity;
	}
	




	
}