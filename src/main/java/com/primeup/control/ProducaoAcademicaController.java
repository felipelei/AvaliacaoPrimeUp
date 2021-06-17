package com.primeup.control;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.primeup.service.ProducaoAcademicaService;
import com.primeup.service.util.RelatorioProducaoAcademicaResponse;

@Controller
@RequestMapping("/producaoAcademica")
public class ProducaoAcademicaController {

	private static final Logger logger = Logger.getLogger(ProducaoAcademicaController.class);
	
	@Autowired
	private ProducaoAcademicaService producaoService;
	
	@RequestMapping("/homeRelatorioProducaoAcademica")
	public String homeRelatorioProducaoAcademica(Model model) {
		logger.info("ProducaoAcademicaController homeRelatorioProducaoAcademica");
		
		RelatorioProducaoAcademicaResponse response = producaoService.getRelatorioProducaoAcademica();
		
		model.addAttribute("relatorio", response);
		
		return "relatorioProducaoAcademicaView";
	}
	
}
