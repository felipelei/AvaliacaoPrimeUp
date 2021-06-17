package com.primeup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.primeup.dominio.Professor;
import com.primeup.infra.ProfessorDao;

@Service
public class ProfessorService {

	@Autowired
	private ProfessorDao dao;
	
	@Transactional
	public List<Professor> obterTodos() {
		return dao.obterTodos();
	}
	
	@Transactional
	public Professor obterPorId(Long id) {
		return dao.obterPorId(id);
	}
}
