package model;

import java.time.LocalDate;

import submodelHardware.Gabinete;

public class Computador {

	private Integer id;
	private String placaMae;
	private String processador;
	private String placaDeVideo;
	private String memoria;
	private String fonte;
	private Gabinete gabinete;
	private LocalDate dataMontagem;
	private double preco;
	private Integer estoque;

	
	


	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public Integer getEstoque() {
		return estoque;
	}
	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPlacaMae() {
		return placaMae;
	}
	public void setPlacaMae(String placaMae) {
		this.placaMae = placaMae;
	}
	public String getPlacaDeVideo() {
		return placaDeVideo;
	}
	public void setPlacaDeVideo(String placaDeVideo) {
		this.placaDeVideo = placaDeVideo;
	}
	public String getMemoria() {
		return memoria;
	}
	public void setMemoria(String memoria) {
		this.memoria = memoria;
	}
	public String getProcessador() {
		return processador;
	}
	public void setProcessador(String processador) {
		this.processador = processador;
	}
	public String getFonte() {
		return fonte;
	}
	public void setFonte(String fonte) {
		this.fonte = fonte;
	}
	public Gabinete getGabinete() {
		return gabinete;
	}
	public void setGabinete(Gabinete gabinete) {
		this.gabinete = gabinete;
	}
	public LocalDate getDataMontagem() {
		return dataMontagem;
	}
	public void setDataMontagem(LocalDate dataMontagem) {
		this.dataMontagem = dataMontagem;
	}



}
