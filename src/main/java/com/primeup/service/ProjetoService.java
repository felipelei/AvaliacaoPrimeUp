package com.primeup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.primeup.dominio.Colaborador;
import com.primeup.dominio.Projeto;
import com.primeup.dominio.Publicacao;
import com.primeup.infra.ProjetoDao;

@Service
public class ProjetoService {

	@Autowired
	private ProjetoDao dao;

	@Transactional
	public String incluir(Projeto projeto) {
		dao.incluir(projeto);
		
		return "Sucesso ao inserir Projeto.";
	}
	
	@Transactional
	public List<Projeto> obterTodos() {
		return dao.obterTodos();
	}
	
	@Transactional
	public String adicionarPublicacao(Projeto projeto, Publicacao publicacao) {
		projeto.addPublicacao(publicacao);
		publicacao.setProjeto(projeto);
		
		dao.alterar(projeto);
		
		return "Sucesso ao adicionar publicação.";
	}
	
	@Transactional
	public String adicionarColaborador(Projeto projeto, Colaborador colaborador) {
		projeto.addColaborador(colaborador);
		colaborador.addProjeto(projeto);
		
		dao.alterar(projeto);
		
		return "Sucesso ao adicionar Colaborador.";
	}
	
	@Transactional
	public Projeto obterPorIdParaVisualizacao(Long id) {
		return dao.obterPorIdParaVisualizacao(id);
	}
	
	@Transactional
	public String atualizar(Projeto projeto) {
		dao.alterar(projeto);
		return "Sucesso ao atualizar Projeto.";
	}
	
	@Transactional
	public List<Projeto> obterProjetosEmAndamentoNaoRelacionadoComPublicacao(Long publicacaoId) {
		return dao.obterProjetosEmAndamentoNaoRelacionadoComPublicacao(publicacaoId);
	}

}
