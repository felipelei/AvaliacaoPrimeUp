package com.primeup.infra;

import java.util.List;

import com.primeup.dominio.Aluno;

public interface AlunoDao {

	public abstract List<Aluno> obterAlunosSemOrientacao();
	
	public abstract Aluno obterPorId(Long id);
	
}
