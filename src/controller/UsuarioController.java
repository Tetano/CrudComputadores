package controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import dao.UsuarioDAO;
import model.Perfil;
import model.Sexo;
import model.Usuario;

@Named
@RequestScoped
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
	
	public Sexo[] getListaSexo() {
		return Sexo.values();
	}

	public Perfil[] getListaPerfil() {
		return Perfil.values();
	}




	
}