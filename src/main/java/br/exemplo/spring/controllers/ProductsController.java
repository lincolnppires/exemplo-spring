package br.exemplo.spring.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.exemplo.spring.daos.ProcuctDAO;
import br.exemplo.spring.models.Product;

@Controller
public class ProductsController {

	@Autowired
	private ProcuctDAO productDAO;
	
	@RequestMapping("/produtos/form")
	public String form() {
		System.out.println("método form");
		return "products/form";
	}
	
	@Transactional
	@RequestMapping("/produtos")
	public String save(Product product) {
		System.out.println("Cadastrando o produto");
		productDAO.save(product);
		return "products/ok";
	}
	
	
}
