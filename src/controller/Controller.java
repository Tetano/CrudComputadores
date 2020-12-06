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
			limpar();
		} catch(Exception e) {
			Util.addMessage("N�o foi poss�vel fazer a inclus�o");
			e.printStackTrace();
		}
	}
	
	
	public void excluir() {
		excluir(getEntity());
	}

	public void excluir(T entity) {	
		try {
			dao.excluir(entity);
			Util.addMessage("Altera��o Realizada com sucesso");
			limpar();
		} catch (Exception e) {
			Util.addMessage("N�o � possivel fazer uma exclus�o.");
			e.printStackTrace();
		}
	}

	public void editar(T entity) {
		try {
			setEntity(dao.obterUm(entity));
		} catch (Exception e) {
			Util.addMessage("Problema ao editar.");
			e.printStackTrace();
		}
	}
	
	public void alterar() {
		try {
			dao.alterar(getEntity());
			Util.addMessage("Altera��o Realizada com sucesso");
			limpar();
		} catch (Exception e) {
			Util.addMessage("N�o � possivel fazer uma altera��o.");
			e.printStackTrace();
		}
	}
	

	public List<T> getlistaEntity() {
		if (listaEntity == null) {
			try {
				listaEntity = dao.obterTodos();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				listaEntity = new ArrayList<T>();
			}
		}
		return listaEntity;
	}
	
	
	public void limpar() {
		entity = null; 
		listaEntity = null;
		}
	
	public abstract T getEntity();

	public void setEntity(T entity) {
		this.entity = entity;
	}
	

}