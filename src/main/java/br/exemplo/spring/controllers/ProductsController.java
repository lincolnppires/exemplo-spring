package br.exemplo.spring.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.exemplo.spring.component.FileSaver;
import br.exemplo.spring.daos.ProcuctDAO;
import br.exemplo.spring.models.BookType;
import br.exemplo.spring.models.Product;
import br.exemplo.spring.validation.ProductValidator;
import javassist.bytecode.analysis.MultiType;

@Transactional
@Controller
@RequestMapping("/produtos")
public class ProductsController {

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		// binder.setValidator(new ProductValidator());
	}

	@Autowired
	private ProcuctDAO productDAO;

//	@Autowired
//	private FileSaver fileSaver;

	@RequestMapping("/form")
	public ModelAndView form(Product product) {
		ModelAndView modelAndView = new ModelAndView("products/form");
		modelAndView.addObject("types", BookType.values());
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST, name = "saveProduct")
	public ModelAndView save(MultipartFile summary, @Validated Product product, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		System.out.println("Inicio cadastrando o produto");
		System.out.println(summary.getName() + ";" + summary.getOriginalFilename());

		if (bindingResult.hasErrors()) {
			System.out.println("existe erro " + bindingResult.getFieldError().toString());
			return form(product);
		}

		// String webPath = fileSaver.write("uploaded-images",summary);
		// product.setSummaryPath(webPath);
		productDAO.save(product);

		redirectAttributes.addFlashAttribute("sucesso", "Produto cadastrado com sucesso");
		return new ModelAndView("redirect:produtos");
	}

	@RequestMapping(method = RequestMethod.GET)
	@Cacheable(value="books")
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("products/list");
		modelAndView.addObject("products", productDAO.list());
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, value ="/show/{id}", name = "showProduct")
	public ModelAndView show(@PathVariable("id") 	Integer id) {
		ModelAndView modelAndView = new ModelAndView("products/show");
		Product product = productDAO.find(id);
		modelAndView.addObject("product", product);
		return modelAndView;
	}

}
