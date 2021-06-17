package com.primeup.dominio;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public abstract class Aluno extends Colaborador {

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataIngresso;

	@OneToOne(mappedBy="aluno", cascade = CascadeType.MERGE)
	private Orientacao orientacao;

	public Date getDataIngresso() {
		return dataIngresso;
	}

	public void setDataIngresso(Date dataIngresso) {
		this.dataIngresso = dataIngresso;
	}

	public Orientacao getOrientacao() {
		return orientacao;
	}

	public void setOrientacao(Orientacao orientacao) {
		this.orientacao = orientacao;
	}
	
}
