package com.primeup.dominio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Publicacao extends ProducaoAcademica {

	private String conferencia;

	private int ano;

	@ManyToOne
	@JoinColumn(name = "projeto_id")
	private Projeto projeto;

	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinTable(name = "publicacao_colaborador", joinColumns = {
			@JoinColumn(name = "publicacao_id") }, inverseJoinColumns = { @JoinColumn(name = "colaborador_id") })
	private List<Colaborador> colaboradorList;

	public String getConferencia() {
		return conferencia;
	}

	public void setConferencia(String conferencia) {
		this.conferencia = conferencia;
	}

	public void addColaborador(Colaborador c) {
		if (c == null) {
			throw new IllegalArgumentException("Não foi enviado o colaborador!");
		}

		if (this.colaboradorList == null) {
			this.colaboradorList = new ArrayList<>();
		}
		
		if(colaboradorList.contains(c)) {
			throw new IllegalArgumentException("Este colaborador já está relacionado com a publicação!");
		}

		this.colaboradorList.add(c);
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public List<Colaborador> getColaboradorList() {
		return colaboradorList;
	}

	public void setColaboradorList(List<Colaborador> colaboradorList) {
		this.colaboradorList = colaboradorList;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	@Override
	public String toString() {
		return "Publicacao: " + super.getTitulo();
	}

}
