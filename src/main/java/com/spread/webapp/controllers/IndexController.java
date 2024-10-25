package com.spread.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController 
{
	private String saluti = "Saluti, sono la tua prima applicazione web creata in Spring 6";
	
	@GetMapping(value="/")
	public String getWelcome(Model model)
	{
		model.addAttribute("intestazione", "Benvenuti nella root page della webapp BetaShop");
		model.addAttribute("saluti", saluti);
		
		return "index";
	}
	
	@RequestMapping(value="index")
	public String getWelcome2(Model model,  @RequestParam(name="name",required = false,defaultValue="anonimo") String nome)
	{
		model.addAttribute("intestazione", String.format("Benvenuto %s nella index page della webapp BetaShop", nome));
		model.addAttribute("saluti", saluti);
		
		return "index";
	}
}