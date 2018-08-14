package br.exemplo.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/home")
	public String index() {
		System.out.println("Carregando os produtos	");
		return "hello-world";
	}
	
	@RequestMapping("/testetagfiles")
	public String testetagfiles() {
		System.out.println("testetagfiles	");
		return "testeTagfile";
	}
	
}
