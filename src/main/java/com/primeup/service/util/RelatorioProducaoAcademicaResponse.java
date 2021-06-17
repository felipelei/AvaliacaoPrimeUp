package com.primeup.service.util;

public class RelatorioProducaoAcademicaResponse {

	private int totalColaborador;
	private int totalProjetoEmElaboracao;
	private int totalProjetoEmAndamento;
	private int totalProjetoConcluido;

	private int totalOrientacao;
	private int totalPublicacao;

	public int getTotalColaborador() {
		return totalColaborador;
	}

	public void setTotalColaborador(int totalColaborador) {
		this.totalColaborador = totalColaborador;
	}

	public int getTotalProjetoEmElaboracao() {
		return totalProjetoEmElaboracao;
	}

	public void setTotalProjetoEmElaboracao(int totalProjetoEmElaboracao) {
		this.totalProjetoEmElaboracao = totalProjetoEmElaboracao;
	}

	public int getTotalProjetoEmAndamento() {
		return totalProjetoEmAndamento;
	}

	public void setTotalProjetoEmAndamento(int totalProjetoEmAndamento) {
		this.totalProjetoEmAndamento = totalProjetoEmAndamento;
	}

	public int getTotalProjetoConcluido() {
		return totalProjetoConcluido;
	}

	public void setTotalProjetoConcluido(int totalProjetoConcluido) {
		this.totalProjetoConcluido = totalProjetoConcluido;
	}

	public int getTotalOrientacao() {
		return totalOrientacao;
	}

	public void setTotalOrientacao(int totalOrientacao) {
		this.totalOrientacao = totalOrientacao;
	}

	public int getTotalPublicacao() {
		return totalPublicacao;
	}

	public void setTotalPublicacao(int totalPublicacao) {
		this.totalPublicacao = totalPublicacao;
	}
	
	public int getTotalProjeto() {
		return totalProjetoEmElaboracao + totalProjetoConcluido + totalProjetoEmAndamento;
	}
}
