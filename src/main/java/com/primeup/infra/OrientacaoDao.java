package com.primeup.infra;

import java.util.List;

import com.primeup.dominio.Orientacao;

public interface OrientacaoDao {

	public abstract List<Orientacao> obterTodos();
	
	public abstract Orientacao obterPorId(Long id);
}
