package com.primeup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.primeup.dominio.Aluno;
import com.primeup.infra.AlunoDao;

@Service
public class AlunoService {

	@Autowired
	private AlunoDao dao;
	
	@Transactional
	public List<Aluno> obterAlunosSemOrientacao() {
		return dao.obterAlunosSemOrientacao();
	}
	
	@Transactional
	public Aluno obterPorId(Long id) {
		return dao.obterPorId(id);
	}
	
	
	public int somarParcelas (int parcela1, int parcela2) {
		return parcela1 + parcela2;
	}
	
}
