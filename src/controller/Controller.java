package controller;

import java.util.ArrayList;
import java.util.List;

import application.Util;
import dao.DAO;

public abstract class Controller<T> {

	protected T entity;
	private DAO<T> dao = null;
	private List<T> listaEntity;
	
	public Controller(DAO<T> dao) {
		super();
		this.dao = dao;
	}

	public void incluir() {
		try {
			dao.inserir(getEntity());
			Util.addMessage("Inclusão realizada com sucesso.");

		} catch (Exception e) {
			Util.addMessage("Não é possivel fazer uma inclusão.");
			e.printStackTrace();
		}
	}
	
	public abstract T getEntity();

	public void setEntity(T entity) {
		this.entity = entity;
	}
	
	public List<T> getListaEntity() {
		if (listaEntity == null) {
			try {
				listaEntity = dao.obterTodos();
			} catch (Exception e) {
				e.printStackTrace();
				listaEntity = new ArrayList<T>();
			}
		}	
		return listaEntity;
	}
	
}