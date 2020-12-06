package controller;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import dao.ComputadorDAO;
import model.Computador;
import submodelHardware.Gabinete;


@Named
@ViewScoped
public class ComputadorController extends Controller<Computador> implements Serializable {
	//validado

	private static final long serialVersionUID = -7748328763730744281L;
	
	public ComputadorController() {
		super(new ComputadorDAO());
		// TODO Auto-generated constructor stub
	}

	@Override
	public Computador getEntity() {
		// TODO Auto-generated method stub
		if(entity == null) 
			entity = new Computador();
			return entity;
		
		}

	
	
	public Gabinete[] getListaGabinete() {
		return Gabinete.values();
	}


}
