package com.primeup.infra;

import java.util.List;

import com.primeup.dominio.Professor;

public interface ProfessorDao {

	public abstract List<Professor> obterTodos();
	
	public abstract Professor obterPorId(Long id); 
}
