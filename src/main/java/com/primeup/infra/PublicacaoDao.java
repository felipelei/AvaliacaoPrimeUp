package com.primeup.infra;

import java.util.List;

import com.primeup.dominio.Publicacao;

public interface PublicacaoDao {

	public abstract List<Publicacao> obterTodos();
	
	public abstract List<Publicacao> obterPublicacaoNaoRelacionadaComProjeto();
	
	public abstract Publicacao obterPorIdParaVisualizacao(Long id);

}
