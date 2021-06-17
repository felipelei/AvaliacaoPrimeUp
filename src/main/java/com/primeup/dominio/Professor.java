package com.primeup.dominio;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Professor extends Colaborador {

	@OneToMany(mappedBy="professor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Orientacao> orientacaoList;

	public List<Orientacao> getOrientacaoList() {
		return orientacaoList;
	}

	public void setOrientacaoList(List<Orientacao> orientacaoList) {
		this.orientacaoList = orientacaoList;
	}
	
	public String toString() {
		return super.getNome();
	}

}
