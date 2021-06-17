package com.primeup.infra;

import java.util.List;

import com.primeup.dominio.ProducaoAcademica;

public interface ProducaoAcademicaDao {

	public List<ProducaoAcademica> obterTodos();
	
	public boolean incluir(ProducaoAcademica p);
	
	public boolean alterar(ProducaoAcademica p);
	
	public ProducaoAcademica obterPorId(Long id);
	
}
