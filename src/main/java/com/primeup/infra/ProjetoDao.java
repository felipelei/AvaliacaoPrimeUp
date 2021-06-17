package com.primeup.infra;

import java.util.List;

import com.primeup.dominio.Projeto;

public interface ProjetoDao {

	public abstract boolean incluir(Projeto p);

	public abstract boolean alterar(Projeto p);

	public abstract boolean excluir(Projeto p);

	public abstract List<Projeto> obterTodos();
	
	public abstract Projeto obterPorIdParaVisualizacao(Long id);
	
	public abstract Projeto obterPorId(Long id);
	
	public abstract List<Projeto> obterProjetosEmAndamentoNaoRelacionadoComPublicacao(Long id);
	
}
