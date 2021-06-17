package com.primeup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.primeup.dominio.Colaborador;
import com.primeup.infra.ColaboradorDao;

@Service
public class ColaboradorService {

	@Autowired
	private ColaboradorDao dao;

	@Transactional
	public void incluir(Colaborador colaborador) {
		dao.incluir(colaborador);
	}
	
	@Transactional
	public List<Colaborador> obterTodos() {
		return dao.obterTodos();
	}
	
	@Transactional
	public Colaborador obterPorId(Long id) {
		
		return dao.obterPorId(id);
	}
	
	@Transactional
	public Colaborador obterPorIdParaVisualizacao(Long id) {
		return dao.obterPorIdParaVisualizacao(id);
	}
	
	@Transactional
	public List<Colaborador> obterColaboradorNaoRelacionadaComProjeto(Long projetoId) {
		return dao.obterColaboradorNaoRelacionadaComProjeto(projetoId);
	}
	
	@Transactional
	public List<Colaborador> obterColaboradorNaoRelacionadaComPublicacao(Long publicacaoId) {
		return dao.obterColaboradorNaoRelacionadaComPublicacao(publicacaoId);
	}
	
	@Transactional
	public boolean atualizar(Colaborador c) {
		return dao.alterar(c);
	}
	
	@Transactional
	public Colaborador atualizarObterPorIdParaVisualizacao(Colaborador c) {
		dao.alterar(c);
		return dao.obterPorIdParaVisualizacao(c.getId());
	}
}
