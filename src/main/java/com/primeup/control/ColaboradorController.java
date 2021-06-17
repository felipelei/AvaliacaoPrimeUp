package com.primeup.control;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.primeup.control.util.OrdenaProjetoRecente;
import com.primeup.control.util.OrdenaPublicacaoRecente;
import com.primeup.dominio.Colaborador;
import com.primeup.dominio.Professor;
import com.primeup.service.ColaboradorService;

@Controller
@RequestMapping(value = "/colaborador")
public class ColaboradorController {

	private static final Logger logger = Logger.getLogger(ColaboradorController.class);
	
	@Autowired
	private ColaboradorService colaboradorService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model model) {
		return "redirect:/colaborador/";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homeColaboradorList(Model model) {
		List<Colaborador> colaboradorList = colaboradorService.obterTodos();
		
		// adicionar projetos
		model.addAttribute("colaboradorList", colaboradorList);
		
		return "listaColaboradoresView";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String showColaborador(@PathVariable("id") Long id, Model model) {
		Colaborador colaborador = colaboradorService.obterPorIdParaVisualizacao(id);
		
		if(colaborador == null) {
			model.addAttribute("erro", "Erro: objeto não encontrado.");
			return "paginaPrincipal";
		}
		
		Collections.sort(colaborador.getProjetoList(), new OrdenaProjetoRecente());
		
		Collections.sort(colaborador.getPublicacaoList(), new OrdenaPublicacaoRecente());
		
		model.addAttribute("colaborador", colaborador);
		model.addAttribute("isProfessor", (colaborador instanceof Professor));
		
		return "colaboradorView";
	}
	
}
