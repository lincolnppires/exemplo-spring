package br.exemplo.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.exemplo.spring.daos.ProcuctDAO;
import br.exemplo.spring.models.BookType;
import br.exemplo.spring.models.Product;
import br.exemplo.spring.models.ShoppingCart;
import br.exemplo.spring.models.ShoppingItem;

@Controller
@RequestMapping("/shopping")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class ShoppingCartController {
	
	@Autowired
	private ProcuctDAO productDAO;
	@Autowired
	private ShoppingCart shoppingCart;
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView add (Integer productId, BookType bookType) {
		ShoppingItem item = createItem(productId, bookType);
		shoppingCart.add(item);
		System.out.println("Elementos no carrinho: " + shoppingCart.getQuantity());
		return new ModelAndView("redirect:produtos");
	}

	private ShoppingItem createItem(Integer productId, BookType bookType) {
		Product product = productDAO.find(productId);
		ShoppingItem item = new ShoppingItem(product, bookType);
		return item;
	}

}
