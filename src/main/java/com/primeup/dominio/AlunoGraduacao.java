package com.primeup.dominio;

import javax.persistence.Entity;

@Entity
public class AlunoGraduacao extends Aluno {

	public String toString() {
		return "Aluno Gradua��o: " + super.getNome();
	}
}
