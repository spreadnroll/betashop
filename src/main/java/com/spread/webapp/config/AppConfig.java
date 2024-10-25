package com.spread.webapp.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Configuration("application")
@Getter
@Setter
public class AppConfig 
{
	private String listino;
	private String lingua;
}
