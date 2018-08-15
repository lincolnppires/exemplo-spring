package br.exemplo.spring.controllers;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;

import br.exemplo.spring.models.ShoppingCart;

@Controller
@RequestMapping("/payment")
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class PaymentController {

	@Autowired
	private ShoppingCart shoppingCart;

	@RequestMapping(value = "checkout", method = RequestMethod.POST, name = "checkoutPaymentController")
	public String checkout() {
		BigDecimal total = shoppingCart.getTotal();
		//codigo de integração
		
		return "redirect:/success";
	}
}
