package com.spread.webapp.controllers;

import org.springframework.stereotype.Component;

@Component
public class AuthenticationService {

	public boolean auth(String username, String password) {
		boolean isValidUsername = username.equalsIgnoreCase("Spread");
		boolean isValidPassword = password.equalsIgnoreCase("password");
		
		return isValidUsername && isValidPassword;
	}

}
