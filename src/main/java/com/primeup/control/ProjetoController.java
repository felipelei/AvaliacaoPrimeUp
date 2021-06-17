package com.primeup.control;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.primeup.control.util.OrdenaPublicacaoRecente;
import com.primeup.dominio.Colaborador;
import com.primeup.dominio.Projeto;
import com.primeup.dominio.Publicacao;
import com.primeup.dominio.Projeto.EnumStatus;
import com.primeup.service.ColaboradorService;
import com.primeup.service.ProducaoAcademicaService;
import com.primeup.service.ProjetoService;
import com.primeup.service.PublicacaoService;

@Controller
@RequestMapping("/projeto")
public class ProjetoController {

	private static final Logger logger = Logger.getLogger(ProjetoController.class);

	@Autowired
	private ProjetoService projetoService;
	
	@Autowired
	private ProducaoAcademicaService producaoService;

	@Autowired
	private PublicacaoService publicacaoService;
	
	@Autowired
	private ColaboradorService colaboradorService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model model) {
		return "redirect:/projeto/";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homeProjetoList(Model model) {
		List<Projeto> projetoList = projetoService.obterTodos();

		// adicionar projetos
		model.addAttribute("projetoList", projetoList);

		return "listaProjetosView";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String showProjeto(@PathVariable("id") Long id, Model model) {
		try
		{
			Projeto projeto = projetoService.obterPorIdParaVisualizacao(id);
			mapearNoRetorno(model, projeto);
		} catch(Exception e) {
			model.addAttribute("erro", "Erro: objeto não encontrado.");
			return "paginaPrincipal";
		}

		return "projetoView";
	}

	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String redirectInsert(Model model) {
		model.addAttribute("acao", "insert");
		
		Projeto projeto = new Projeto();
		projeto.setStatus(EnumStatus.EM_ELABORACAO);
		
		model.addAttribute("projeto", projeto);
		
		return "projetoView";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Long id, @ModelAttribute("projeto")  Projeto projeto, Model model) {

		logger.info("save, projeto=" + projeto);
		
		try {

			String msg = projetoService.incluir(projeto);

			model.addAttribute("msgAcao", msg);
		} catch (Exception e) {
			logger.error(e);
			model.addAttribute("erro", e.getMessage());
		}

		mapearNoRetorno(model, projeto);

		return "projetoView";
	}
	
	@RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
	public String update(@PathVariable("id") Long id, @ModelAttribute("projeto") Projeto projeto, @RequestParam(required=false) String acao, Model model) {
		Projeto projetoToView = null;

		try {
			projetoToView = projetoService.obterPorIdParaVisualizacao(projeto.getId());
			projetoToView.setStatus(projeto.getStatus());

			String msg = projetoService.atualizar(projetoToView);
			
			model.addAttribute("msgAcao", msg);
		} catch (Exception e) {
			logger.error(e);
			model.addAttribute("erro", e.getMessage());
		}

		mapearNoRetorno(model, projetoToView);

		return "projetoView";
	}
	
	@RequestMapping(value = "/{id}/addProducaoAcademica", method = RequestMethod.POST)
	public String addProducao(@PathVariable("id") Long id, Long publicacaoId, Model model) {
		Projeto projetoToView = projetoService.obterPorIdParaVisualizacao(id);
		
		try {
			Publicacao pub = (Publicacao) producaoService.obterPorId(publicacaoId);
			
			String msg = projetoService.adicionarPublicacao(projetoToView, pub);
			
			model.addAttribute("msgAcao", msg);
		} catch (Exception e) {
			logger.error(e);
			model.addAttribute("erro", e.getMessage());
		}

		mapearNoRetorno(model, projetoToView);

		return "projetoView";
	}
	
	@RequestMapping(value = "/{id}/addColaborador", method = RequestMethod.POST)
	public String addColaborador(@PathVariable("id") Long id, Long colaboradorId, Model model) {
		Projeto projetoToView = projetoService.obterPorIdParaVisualizacao(id);
		
		try {
			Colaborador colaborador = colaboradorService.obterPorIdParaVisualizacao(colaboradorId);
			
			String msg = projetoService.adicionarColaborador(projetoToView, colaborador);
			model.addAttribute("msgAcao", msg);
		} catch (Exception e) {
			logger.error(e);
			model.addAttribute("erro", e.getMessage());
		}

		mapearNoRetorno(model, projetoToView);

		return "projetoView";
	}
	
	
	private void mapearNoRetorno(Model model, Projeto projeto) {
		// associacoes para o projeto
		if(projeto != null && projeto.isEmAndamento()) {
			model.addAttribute("publicacaoNaoVinculadaComProjeto", publicacaoService.obterPublicacaoNaoRelacionadaComProjeto());
		}
		
		if(projeto != null && projeto.isEmElaboracao()) {
			model.addAttribute("colaboradorNaoVinculadoComProjeto", colaboradorService.obterColaboradorNaoRelacionadaComProjeto(projeto.getId()));
		}
		
		// ordenar publicacoes
		if(projeto != null && projeto.getPublicacaoList() != null && !projeto.getPublicacaoList().isEmpty())
			Collections.sort(projeto.getPublicacaoList(), new OrdenaPublicacaoRecente());
		
		model.addAttribute("projeto", projeto);
	}

}

