package com.primeup.infra;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import com.primeup.dominio.Projeto;
import com.primeup.dominio.Projeto.EnumStatus;

@Repository
public class ProjetoDaoJpa extends GenericDaoJpa<Projeto> implements ProjetoDao {

	public boolean excluir(Projeto p) {
		return super.excluir(Projeto.class, p.getId());
	}

	public List<Projeto> obterTodos() {
		return super.obterTodos(Projeto.class);
	}
	
	@Override
	public boolean incluir(Projeto p) {
		return super.incluir(p);
	}

	@Override
	public boolean alterar(Projeto p) {
		return super.alterar(p);
	}
	
	public Projeto obterPorId(Long id) {
		return super.obterPorId(Projeto.class, id);
	}
	
	public Projeto obterPorIdParaVisualizacao(Long id) {
		Projeto projeto = obterPorId(id);
		
		Hibernate.initialize(projeto.getColaboradorList());
		Hibernate.initialize(projeto.getPublicacaoList());
		
		return projeto;
	}
	
	public List<Projeto> obterProjetosEmAndamentoNaoRelacionadoComPublicacao(Long publicacaoId) {
		String query = "SELECT p from Projeto p WHERE p.status = ? AND NOT EXISTS (SELECT pb from p.publicacaoList pb WHERE pb.id = ?)";
		
		return super.obterEntidades(query, EnumStatus.EM_ANDAMENTO, publicacaoId);
	}

}
