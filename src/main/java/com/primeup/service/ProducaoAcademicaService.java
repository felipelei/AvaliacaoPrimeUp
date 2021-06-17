package com.primeup.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.primeup.dominio.Colaborador;
import com.primeup.dominio.Orientacao;
import com.primeup.dominio.ProducaoAcademica;
import com.primeup.dominio.Projeto;
import com.primeup.dominio.Publicacao;
import com.primeup.infra.ColaboradorDao;
import com.primeup.infra.OrientacaoDao;
import com.primeup.infra.ProducaoAcademicaDao;
import com.primeup.infra.ProjetoDao;
import com.primeup.infra.PublicacaoDao;
import com.primeup.service.util.RelatorioProducaoAcademicaResponse;

@Service
public class ProducaoAcademicaService {

	private static final Logger logger = Logger.getLogger(ProducaoAcademicaService.class);
	
	@Autowired
	private OrientacaoDao orientacaoDao;
	
	@Autowired
	private ProducaoAcademicaDao producaoDao;
	
	@Autowired
	private PublicacaoDao publicacaoDao;

	@Autowired
	private ColaboradorDao colaboradorDao;
	
	@Autowired
	private ProjetoDao projetoDao;
	
	@Transactional
	public String incluir(ProducaoAcademica p) {
		producaoDao.incluir(p);
		
		return "Sucesso ao incluir Produção Acadêmica.";
	}
	
	@Transactional
	public List<ProducaoAcademica> obterTodos() {
		return producaoDao.obterTodos();
	}
	
	@Transactional
	public ProducaoAcademica obterPorId(Long id) {
		return producaoDao.obterPorId(id);
	}
	
	@Transactional
	public String atualizar(ProducaoAcademica p) {
		producaoDao.alterar(p);
		
		return "Sucesso ao atualizar Produção Acadêmica.";
	}
	
	public RelatorioProducaoAcademicaResponse getRelatorioProducaoAcademica() {
		RelatorioProducaoAcademicaResponse response = new RelatorioProducaoAcademicaResponse();
		
		int totalColaboradores = 0;
		int totalProjetoEmElaboracao = 0;
		int totalProjetoEmAndamento = 0;
		int totalProjetoConcluido = 0;
		int totalOrientacao = 0;
		int totalPublicacao = 0;
		
		// totais de producao
		List<Orientacao> orientacaoList = orientacaoDao.obterTodos();
		List<Publicacao> publicacaoList = publicacaoDao.obterTodos();
		
		totalOrientacao = orientacaoList.size();
		totalPublicacao = publicacaoList.size();
		
		// totais de colaborador
		List<Colaborador> colaboradorList = colaboradorDao.obterTodos();
		totalColaboradores = colaboradorList.size();
		
		// tolais de projeto
		List<Projeto> projetoList = projetoDao.obterTodos();
		for(Projeto p: projetoList) {
			switch(p.getStatus()) {
				case EM_ELABORACAO:
					totalProjetoEmElaboracao++;
				break;
				case EM_ANDAMENTO:
					totalProjetoEmAndamento++;
				break;
				case CONCLUIDO:
					totalProjetoConcluido++;
				break;
			}
		}
		
		response.setTotalColaborador(totalColaboradores);
		
		response.setTotalProjetoConcluido(totalProjetoConcluido);
		response.setTotalProjetoEmAndamento(totalProjetoEmAndamento);
		response.setTotalProjetoEmElaboracao(totalProjetoEmElaboracao);
		
		response.setTotalPublicacao(totalPublicacao);
		response.setTotalOrientacao(totalOrientacao);
		
		return response;
	}
	
}
