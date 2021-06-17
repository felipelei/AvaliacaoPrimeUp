package com.primeup.control;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WelcomeController {
	
	private static final Logger logger = Logger.getLogger(WelcomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getWelcome(Model model) {
		logger.info("paginaPrincipal");

		return "paginaPrincipal";

	}

}
