package controller;

import application.Session;
import application.Util;
import dao.UsuarioDAO;
import model.Usuario;


public class LoginController {
	
	private Usuario usuario;
	
	
public void logar() {
		
		UsuarioDAO dao = new UsuarioDAO();
		try {
			Usuario usuarioLogado = 
					dao.obterUsuario(getUsuario().getEmail(), 
							Util.hash(getUsuario().getEmail()+getUsuario().getSenha()));
			if (usuarioLogado == null)
				Util.addMessage("Usuário ou senha inválido.");
			else {
				// Usuario existe com as credenciais
				Session.getInstance().setAttribute("usuarioLogado", usuarioLogado);
				Util.redirect("template.xhtml");
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			Util.addMessage("Problema ao verificar o Login. Entre em contato pelo email: contato@email.com.br");
		}
	}


private Usuario getUsuario() {
	// TODO Auto-generated method stub
	if (usuario == null)
		usuario = new Usuario();
	return usuario;
}
public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
}

}
