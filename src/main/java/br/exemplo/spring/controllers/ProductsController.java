package br.exemplo.spring.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.exemplo.spring.daos.ProcuctDAO;
import br.exemplo.spring.models.BookType;
import br.exemplo.spring.models.Product;
import br.exemplo.spring.validation.ProductValidator;

@Transactional
@Controller
@RequestMapping("/produtos")
public class ProductsController {
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new ProductValidator());
	}

	@Autowired
	private ProcuctDAO productDAO;

	@RequestMapping("/form")
	public ModelAndView form(Product product) {
		System.out.println("método form");
		ModelAndView modelAndView = new ModelAndView("products/form");
		modelAndView.addObject("types", BookType.values());

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST, name="saveProduct")
	public ModelAndView save(@Validated Product product, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		System.out.println("Inicio cadastrando o produto");
		
		if(bindingResult.hasErrors()) {
			System.out.println("existe erro");
			return form(product);
		}

		productDAO.save(product);

		redirectAttributes.addFlashAttribute("sucesso", "Produto cadastrado com sucesso");
		return new ModelAndView("redirect:produtos");
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("products/list");
		modelAndView.addObject("products", productDAO.list());
		return modelAndView;
	}

}
