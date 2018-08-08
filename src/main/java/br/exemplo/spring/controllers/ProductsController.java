package br.exemplo.spring.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.exemplo.spring.daos.ProcuctDAO;
import br.exemplo.spring.models.BookType;
import br.exemplo.spring.models.Product;

@Transactional
@Controller
@RequestMapping("/produtos")
public class ProductsController {

	@Autowired
	private ProcuctDAO productDAO;

	@RequestMapping("/form")
	public ModelAndView form() {
		System.out.println("método form");
		ModelAndView modelAndView = new ModelAndView("products/form");
		modelAndView.addObject("types", BookType.values());

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView save(Product product) {
		System.out.println("Cadastrando o produto");
		productDAO.save(product);
		ModelAndView modelAndView = new ModelAndView("redirect:produtos");
		modelAndView.addObject("sucesso", "Produto cadastrado com sucesso");
		return modelAndView;
	}

	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("products/list");
		modelAndView.addObject("products", productDAO.list());
		return modelAndView;
	}

}
