package com.primeup.infra;

import java.util.List;

import com.primeup.dominio.Colaborador;

public interface ColaboradorDao {

	public abstract boolean incluir(Colaborador p);

	public abstract boolean alterar(Colaborador p);

	public abstract boolean excluir(Colaborador p);

	public abstract List<Colaborador> obterTodos();
	
	public abstract Colaborador obterPorId(Long id);
	
	public Colaborador obterPorIdParaVisualizacao(Long id);
	
	public List<Colaborador> obterColaboradorNaoRelacionadaComProjeto(Long projetoId);
	
	public List<Colaborador> obterColaboradorNaoRelacionadaComPublicacao(Long publicacaoId);
	
	public List<Colaborador> obterColaboradorRelacionadaComProjeto(Long projetoId);
	
	public Colaborador obterUmColaboradorRelacionadaComPublicacao(Long publicacaoId);
	
}
