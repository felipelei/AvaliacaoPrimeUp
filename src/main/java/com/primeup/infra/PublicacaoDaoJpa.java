package com.primeup.infra;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import com.primeup.dominio.Publicacao;

@Repository
public class PublicacaoDaoJpa extends GenericDaoJpa<Publicacao> implements PublicacaoDao{

	@Override
	public List<Publicacao> obterTodos() {
		return super.obterTodos(Publicacao.class);
	}

	@Override
	public boolean alterar(Publicacao p) {
		return super.alterar(p);
	}
	
	@Override
	public List<Publicacao> obterPublicacaoNaoRelacionadaComProjeto() {
		String query = "SELECT p from Publicacao p WHERE p.projeto.id IS NULL";
		
		return super.obterEntidades(query);
	}
	
	public Publicacao obterPorIdParaVisualizacao(Long id) {
		Publicacao publicacao = super.obterPorId(Publicacao.class, id);
		
		Hibernate.initialize(publicacao.getColaboradorList());
		
		return publicacao;
	}
}
