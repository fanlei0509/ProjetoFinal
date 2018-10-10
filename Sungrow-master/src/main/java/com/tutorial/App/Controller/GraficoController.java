package com.tutorial.App.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class GraficoController {

	@RequestMapping(value = "/graficos/temperatura", method = RequestMethod.GET)
	public String graficoTemperatura(ModelMap model) {

		model.addAttribute("message", 15);

		// return "graficoTemperaturaDaAgua";
		return "index";
	}

}
