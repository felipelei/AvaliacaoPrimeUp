package com.primeup.infra;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import com.primeup.dominio.Colaborador;

@Repository
public class ColaboradorDaoJpa extends GenericDaoJpa<Colaborador> implements ColaboradorDao {

	private static final Logger logger = Logger.getLogger(ColaboradorDaoJpa.class);
		
	public boolean excluir(Colaborador p) {
		return super.excluir(Colaborador.class, p.getId());
	}

	public List<Colaborador> obterTodos() {
		return super.obterTodos(Colaborador.class);
	}
	
	@Override
	public boolean incluir(Colaborador p) {
		return super.incluir(p);
	}

	@Override
	public boolean alterar(Colaborador p) {
		return super.alterar(p);
	}
	
	public Colaborador obterPorId(Long id) {
		return super.obterPorId(Colaborador.class, id);
	}
	
	public Colaborador obterPorIdParaVisualizacao(Long id) {
		Colaborador colaborador = obterPorId(id);
		
		Hibernate.initialize(colaborador.getProjetoList());
		Hibernate.initialize(colaborador.getPublicacaoList());
		
		return colaborador;
	}
	
	public List<Colaborador> obterColaboradorNaoRelacionadaComProjeto(Long projetoId) {
		String query = "SELECT c from Colaborador c WHERE NOT EXISTS (SELECT p from c.projetoList p WHERE p.id = ?)";
		
		return super.obterEntidades(query, projetoId);
	}
	
	public List<Colaborador> obterColaboradorRelacionadaComProjeto(Long projetoId) {
		String query = "SELECT c from Colaborador c WHERE EXISTS (SELECT p from c.projetoList p WHERE p.id = ?)";
		
		return super.obterEntidades(query, projetoId);
	}
	
	public List<Colaborador> obterColaboradorNaoRelacionadaComPublicacao(Long publicacaoId) {
		String query = "SELECT c from Colaborador c WHERE NOT EXISTS (SELECT p from c.publicacaoList p WHERE p.id = ?)";
		
		return super.obterEntidades(query, publicacaoId);
	}

	public Colaborador obterUmColaboradorRelacionadaComPublicacao(Long publicacaoId) {
		String query = "SELECT c from Colaborador c WHERE EXISTS (SELECT p from c.publicacaoList p WHERE p.id = ?)";
		
		List<Colaborador> colaboradores = super.obterEntidades(query, publicacaoId);
		
		if (colaboradores!= null)
			return obterPorId(colaboradores.get(0).getId());
		else
			return null;
		
	}

}

