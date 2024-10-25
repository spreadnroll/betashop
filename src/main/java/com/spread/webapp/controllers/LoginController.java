package com.spread.webapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("username")
public class LoginController {
	
	private String titolo = "Accesso & Autenticazione";
	private String sottotitolo = "Procedi ad inserire la userID e la Password";
	private String errmsg = "Spiacente, la userID o la Password sono errati!";

	@Autowired
	private AuthenticationService authenticationService;

//	public LoginController(AuthenticationService authenticationService) {
//		this.authenticationService = authenticationService;
//	}

	@GetMapping(value = "/login")
	public String getLogin(ModelMap model) {
		
		model.addAttribute("Titolo", titolo);
		model.addAttribute("SottoTitolo", sottotitolo);
		model.addAttribute("ErrMsg", errmsg);
		
		return "login";
	}

	@PostMapping(value = "/login")
	public String goToWelcomePage(@RequestParam("username") String username, @RequestParam("password") String password, ModelMap model) {
		if (authenticationService.auth(username, password)) {
			model.put("username", username);
			return "welcome";
		} else {
			return "redirect:/login?error=nologged";
		}
	}
}


