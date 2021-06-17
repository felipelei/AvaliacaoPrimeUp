package com.primeup.infra;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.primeup.dominio.ProducaoAcademica;

@Repository
public class ProducaoAcademicaDaoJpa extends GenericDaoJpa<ProducaoAcademica> implements ProducaoAcademicaDao {

	@Override
	public boolean incluir(ProducaoAcademica p) {
		return super.incluir(p);
	}
	
	@Override
	public List<ProducaoAcademica> obterTodos() {
		return super.obterTodos(ProducaoAcademica.class);
	}

	@Override
	public boolean alterar(ProducaoAcademica p) {
		return super.alterar(p);
	}
	
	@Override
	public ProducaoAcademica obterPorId(Long id) {
		return super.obterPorId(ProducaoAcademica.class, id);
	}
	
}
