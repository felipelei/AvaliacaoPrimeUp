package com.primeup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.primeup.dominio.Colaborador;
import com.primeup.dominio.Publicacao;
import com.primeup.infra.ProducaoAcademicaDao;
import com.primeup.infra.PublicacaoDao;

@Service
public class PublicacaoService {

	@Autowired
	private PublicacaoDao publicacaoDao;
	
	@Autowired
	private ProducaoAcademicaDao producaoDao;
	
	@Transactional
	public List<Publicacao> obterTodos() {
		return publicacaoDao.obterTodos();
	}
	
	@Transactional
	public Publicacao obterPorIdParaVisualizacao(Long id) {
		return publicacaoDao.obterPorIdParaVisualizacao(id);
	}
	
	@Transactional
	public List<Publicacao> obterPublicacaoNaoRelacionadaComProjeto() {
		return publicacaoDao.obterPublicacaoNaoRelacionadaComProjeto();
	}
	
	
	@Transactional
	public String adicionarColaborador(Publicacao publicacao, Colaborador colaborador) {
		publicacao.addColaborador(colaborador);
		colaborador.addPublicacao(publicacao);
		
		producaoDao.alterar(publicacao);
		
		return "Sucesso ao adicionar Colaborador.";
	}
	
}
