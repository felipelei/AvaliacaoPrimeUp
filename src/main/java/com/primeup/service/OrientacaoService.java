package com.primeup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.primeup.dominio.Orientacao;
import com.primeup.infra.OrientacaoDao;

@Service
public class OrientacaoService {

	@Autowired
	private OrientacaoDao orientacaoDao;
	
	@Transactional
	public List<Orientacao> obterTodos() {
		return orientacaoDao.obterTodos();
	}
	
	@Transactional
	public Orientacao obterPorId(Long id) {
		return orientacaoDao.obterPorId(id);
	}
	
}
