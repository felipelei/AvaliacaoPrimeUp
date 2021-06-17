package com.primeup.dominio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Colaborador {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	private String nome;

	private String email;

	@ManyToMany(mappedBy="colaboradorList", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private List<Projeto> projetoList;

	@ManyToMany(mappedBy="colaboradorList", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private List<Publicacao> publicacaoList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Publicacao> getPublicacaoList() {
		return publicacaoList;
	}

	public void setPublicacaoList(List<Publicacao> publicacaoList) {
		this.publicacaoList = publicacaoList;
	}

	public List<Projeto> getProjetoList() {
		return projetoList;
	}

	public void setProjetoList(List<Projeto> projetoList) {
		this.projetoList = projetoList;
	}
	
	public void addProjeto(Projeto p) {
		if(p == null) {
			throw new IllegalArgumentException("Não foi enviado o projeto!");
		}
		
		if(this.projetoList == null) {
			this.projetoList = new ArrayList<>();
		}
		
		if(this.getQuantidadeProjetoEmAndamento() == 2 && p.isEmAndamento() && this instanceof AlunoGraduacao) {
			throw new IllegalStateException("Um aluno de graduação não pode participar de mais de dois projetos Em andamento.");
		}
		
		this.projetoList.add(p);
	}
	
	public void addPublicacao(Publicacao p) {
		if(p == null) {
			throw new IllegalArgumentException("Não foi enviada a publicação!");
		}
		
		if(this.publicacaoList == null) {
			this.publicacaoList = new ArrayList<>();
		}
		
		this.publicacaoList.add(p);
	}
	
	public int getQuantidadeProjetoEmAndamento() {
		if(projetoList == null)
			return 0;
		
		int qtd = 0;
		for(Projeto projeto: projetoList) {
			if(projeto.isEmAndamento()) {
				qtd++;
			}
		}
			
		return qtd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Colaborador other = (Colaborador) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
