package com.primeup.dominio;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class AlunoPosGraduacao extends Aluno {

	public enum EnumRegime {
		PARCIAL, INTEGRAL;
	};

	@Enumerated(EnumType.ORDINAL)
	private EnumRegime regime;

	private boolean doutorado;

	public EnumRegime getRegime() {
		return regime;
	}

	public void setRegime(EnumRegime regime) {
		this.regime = regime;
	}

	public boolean isDoutorado() {
		return doutorado;
	}

	public void setDoutorado(boolean doutorado) {
		this.doutorado = doutorado;
	}

	public String toString() {
		if(doutorado) {
			return "Aluno Doutorado: " + super.getNome();
		} else {
			return "Aluno Mestrado: " + super.getNome();
		}
	}
}
