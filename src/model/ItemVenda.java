package model;

public class ItemVenda {
	private Integer id;
	private Double preco;
	private Computador computador;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getPrece() {
		return preco;
	}
	public void setPrece(Double prece) {
		this.preco = prece;
	}
	public Computador getComputador() {
		return computador;
	}
	public void setComputador(Computador computador) {
		this.computador = computador;
	}
}
