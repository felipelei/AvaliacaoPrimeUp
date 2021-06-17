package com.primeup.infra;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.primeup.dominio.Aluno;

@Repository
public class AlunoDaoJpa extends GenericDaoJpa<Aluno> implements AlunoDao {

	@Override
	public List<Aluno> obterAlunosSemOrientacao() {
		// teve problema utilizando a.orientacao is null
		String query = "SELECT a from Aluno a WHERE NOT EXISTS (SELECT o from a.orientacao o)";
		
		return super.obterEntidades(query);
	}

	@Override
	public Aluno obterPorId(Long id) {
		return null; //super.obterPorId(Aluno.class, id);
	}

}
