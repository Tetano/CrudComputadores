package controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import application.Session;
import application.Util;
import dao.DAO;
import dao.UsuarioDAO;
import model.Usuario;

@Named
@RequestScoped
public class LoginController {

	private Usuario usuario;

	public void logar() {
		
		UsuarioDAO dao = new UsuarioDAO();
		try {
			Usuario usuarioLogado = 
					dao.obterUsuario(getUsuario().getEmail(), getUsuario().getSenha());
							
			if (usuarioLogado == null)
				Util.addMessage("Usuário ou senha inválido.");
			else {
				// Usuario existe com as credenciais
				Session.getInstance().setAttribute("usuarioLogado", usuarioLogado);
				Util.redirect("pagina.xhtml");
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			Util.addMessage("Problema ao verificar o Login. Entre em contato pelo email: contato@email.com.br");
		}
	}

	public Usuario getUsuario() {
		if (usuario == null)
			usuario = new Usuario();
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}