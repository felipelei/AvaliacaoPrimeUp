package com.primeup.infra;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.primeup.dominio.Orientacao;

@Repository
public class OrientacaoDaoJpa extends GenericDaoJpa<Orientacao> implements OrientacaoDao {

	@Override
	public List<Orientacao> obterTodos() {
		return super.obterTodos(Orientacao.class);
	}

	public Orientacao obterPorId(Long id) {
		return super.obterPorId(Orientacao.class, id);
	}
}
