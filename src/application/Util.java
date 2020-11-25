package application;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.component.visit.VisitContext;
import javax.faces.context.FacesContext;

import org.apache.commons.codec.digest.DigestUtils;

import com.sun.faces.component.visit.FullVisitContext;

public class Util {
	public static void addMessage(String value) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(value));
		
	}
	
	public static void redirect(String page) {
		try {
			FacesContext.getCurrentInstance()
				.getExternalContext().redirect(page);
		} catch (IOException e) {
			e.printStackTrace();
			addMessage("Problemas ao redirecionar a página.");
		}
	}
	public static String hash(String valor) {
		return DigestUtils.sha256Hex(valor);
	}
}