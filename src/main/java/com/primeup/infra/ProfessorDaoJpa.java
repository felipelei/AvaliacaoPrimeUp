package com.primeup.infra;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.primeup.dominio.Professor;

@Repository
public class ProfessorDaoJpa extends GenericDaoJpa<Professor> implements ProfessorDao {

	@Override
	public List<Professor> obterTodos() {
		return super.obterTodos(Professor.class);
	}

	@Override
	public Professor obterPorId(Long id) {
		return super.obterPorId(Professor.class, id);
	}

}
