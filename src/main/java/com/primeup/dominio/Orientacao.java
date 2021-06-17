package com.primeup.dominio;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Orientacao extends ProducaoAcademica {

	@OneToOne
	@JoinColumn(name="aluno_id")
	private Aluno aluno;

	@ManyToOne
	@JoinColumn(name="professor_id")
	private Professor professor;

	public Orientacao() {}
	
	public Orientacao(String titulo, Aluno aluno, Professor professor) {
		super(titulo);
		this.aluno = aluno;
		this.professor = professor;
	}
	
	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
	public String toString() {
		if(aluno instanceof AlunoGraduacao)
			return "Trabalho de Conclusão de Curso: " + super.getTitulo();
		else if(aluno instanceof AlunoPosGraduacao) {
			if(((AlunoPosGraduacao) aluno).isDoutorado()) {
				return "Tese: " + super.getTitulo();
			} else {
				return "Dissertação: " + super.getTitulo();
			}
		}
		
		return "";
	}
	
}
