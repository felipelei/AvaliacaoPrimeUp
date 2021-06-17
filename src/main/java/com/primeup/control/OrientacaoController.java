package com.primeup.control;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.primeup.dominio.Aluno;
import com.primeup.dominio.Orientacao;
import com.primeup.dominio.Professor;
import com.primeup.service.AlunoService;
import com.primeup.service.OrientacaoService;
import com.primeup.service.ProducaoAcademicaService;
import com.primeup.service.ProfessorService;

@Controller
@RequestMapping("/orientacao")
public class OrientacaoController {

	private static final Logger logger = Logger.getLogger(OrientacaoController.class);
	
	@Autowired
	private OrientacaoService orientacaoService;
	
	@Autowired
	private ProducaoAcademicaService producaoService;
	
	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	private ProfessorService professorService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model model) {
		return "redirect:/orientacao/";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homeOrientacaooList(Model model) {
		List<Orientacao> orientacaoList = orientacaoService.obterTodos();

		model.addAttribute("orientacaoList", orientacaoList);

		return "listaOrientacoesView";
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String redirectInsert(Model model) {
		model.addAttribute("acao", "insert");
		
		mapearNoRetorno(model, null);
		
		return "orientacaoView";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String showOrientacao(@PathVariable("id") Long id, Model model) {
		Orientacao orientacao = orientacaoService.obterPorId(id);
		
		if(orientacao == null) {
			model.addAttribute("erro", "Erro: objeto não encontrado.");
			return "paginaPrincipal";
		}
		
		mapearNoRetorno(model, orientacao);
		
		return "orientacaoView";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveOrUpdate(String titulo, Long alunoId, Long professorId, String acao, Model model) {
		Orientacao orientacao = null;
		String msg = null;
		
		try {
			Aluno aluno = alunoService.obterPorId(alunoId);
			Professor professor = professorService.obterPorId(professorId);
			
			orientacao = new Orientacao(titulo, aluno, professor);
			
			msg = producaoService.incluir(orientacao);
		} catch (Exception e) {
			logger.error(e);
			model.addAttribute("erro", e.getMessage());
		}

		model.addAttribute("msgAcao", msg);
		mapearNoRetorno(model, orientacao);

		return "orientacaoView";
	}
	
	@RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
	public String saveOrUpdate(@PathVariable("id") Long id, String titulo, Long alunoId, Long professorId, String acao, Model model) {
		Orientacao orientacao = null;
		String msg = null;
		
		try {
			Aluno aluno = alunoService.obterPorId(alunoId);
			Professor professor = professorService.obterPorId(professorId);
			
			orientacao = orientacaoService.obterPorId(id);
			orientacao.setTitulo(titulo);
			orientacao.setAluno(aluno);
			orientacao.setProfessor(professor);
			
			msg = producaoService.atualizar(orientacao);
		} catch (Exception e) {
			logger.error(e);
			model.addAttribute("erro", e.getMessage());
		}

		model.addAttribute("msgAcao", msg);
		mapearNoRetorno(model, orientacao);

		return "orientacaoView";
	}
	
	private void mapearNoRetorno(Model model, Orientacao o) {
		model.addAttribute("alunosSemOrientacao", alunoService.obterAlunosSemOrientacao());
		model.addAttribute("professores", professorService.obterTodos());
		model.addAttribute("orientacao", o);
	}
	
}
