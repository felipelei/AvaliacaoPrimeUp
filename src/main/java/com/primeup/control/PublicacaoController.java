package com.primeup.control;

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

import com.primeup.dominio.Colaborador;
import com.primeup.dominio.Projeto;
import com.primeup.dominio.Publicacao;
import com.primeup.service.ColaboradorService;
import com.primeup.service.ProducaoAcademicaService;
import com.primeup.service.ProjetoService;
import com.primeup.service.PublicacaoService;

@Controller
@RequestMapping("/publicacao")
public class PublicacaoController {

	private static final Logger logger = Logger.getLogger(PublicacaoController.class);

	@Autowired
	private PublicacaoService publicacaoService;

	@Autowired
	private ProjetoService projetoService;

	@Autowired
	private ProducaoAcademicaService producaoService;

	@Autowired
	private ColaboradorService colaboradorService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model model) {
		return "redirect:/publicacao/";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homePublicacaoList(Model model) {
		List<Publicacao> publicacaoList = publicacaoService.obterTodos();

		model.addAttribute("publicacaoList", publicacaoList);

		return "listaPublicacoesView";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String showPublicacao(@PathVariable("id") Long id, Model model) {
		try 
		{
			Publicacao publicacao = publicacaoService.obterPorIdParaVisualizacao(id);

			mapearNoRetorno(model, publicacao);
		} catch(Exception e) {
			model.addAttribute("erro", "Erro: objeto não encontrado.");
			return "paginaPrincipal";
		}
		
		return "publicacaoView";
	}

	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String redirectInsert(Model model) {
		model.addAttribute("acao", "insert");
		
		mapearNoRetorno(model, new Publicacao());
		
		return "publicacaoView";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Long id, @ModelAttribute("publicacao") Publicacao publicacao,
			@RequestParam(required = false) String acao, Model model) {

		try {
			Projeto proj = null;

			if (publicacao.getProjeto().getId() != null) {
				proj = projetoService.obterPorIdParaVisualizacao(publicacao.getProjeto().getId());
			}

			publicacao.setProjeto(proj);

			String msg = producaoService.incluir(publicacao);

			model.addAttribute("msgAcao", msg);
		} catch (Exception e) {
			logger.error(e);
			model.addAttribute("erro", e.getMessage());
		}

		mapearNoRetorno(model, publicacao);

		return "publicacaoView";
	}

	@RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
	public String update(@PathVariable("id") Long id, @ModelAttribute("publicacao") Publicacao publicacao,
			@RequestParam(required = false) String acao, Model model) {
		Publicacao publicacaoParaView = null;

		try {
			if ("edit".equals(acao)) {
				publicacaoParaView = publicacaoService.obterPorIdParaVisualizacao(id);

				Projeto proj = null;

				if (publicacao.getProjeto().getId() != null) {
					proj = projetoService.obterPorIdParaVisualizacao(publicacao.getProjeto().getId());
				}
				
				publicacaoParaView.setProjeto(proj);
				publicacaoParaView.setConferencia(publicacao.getConferencia());
				publicacaoParaView.setAno(publicacao.getAno());
				publicacaoParaView.setTitulo(publicacao.getTitulo());

				String msg = producaoService.atualizar(publicacaoParaView);

				model.addAttribute("msgAcao", msg);
			}
		} catch (Exception e) {
			logger.error(e);
			model.addAttribute("erro", e.getMessage());
		}

		mapearNoRetorno(model, publicacaoParaView);

		return "publicacaoView";
	}

	@RequestMapping(value = "/{id}/addColaborador", method = RequestMethod.POST)
	public String addColaborador(@PathVariable("id") Long id, Long colaboradorId, Model model) {
		Publicacao publicacaoParaView = publicacaoService.obterPorIdParaVisualizacao(id);

		try {
			Colaborador colaborador = colaboradorService.obterPorIdParaVisualizacao(colaboradorId);

			String msg = publicacaoService.adicionarColaborador(publicacaoParaView, colaborador);

			model.addAttribute("msgAcao", msg);
		} catch (Exception e) {
			logger.error(e);
			model.addAttribute("erro", e.getMessage());
		}

		mapearNoRetorno(model, publicacaoParaView);

		return "publicacaoView";
	}

	private void mapearNoRetorno(Model model, Publicacao publicacao) {
		model.addAttribute("publicacao", publicacao);
		model.addAttribute("colaboradorNaoVinculadoComPublicacao", colaboradorService.obterColaboradorNaoRelacionadaComPublicacao(publicacao.getId()));
		model.addAttribute("projetoEmAndamento", projetoService.obterProjetosEmAndamentoNaoRelacionadoComPublicacao(publicacao.getId()));
	}

}
