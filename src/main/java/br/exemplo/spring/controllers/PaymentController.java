package br.exemplo.spring.controllers;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.exemplo.spring.models.ShoppingCart;

@Controller
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private ShoppingCart shoppingCart;

	@RequestMapping(value = "checkout", method = RequestMethod.POST)
	public String checkout() {
		BigDecimal total = shoppingCart.getTotal();
		return "redirect:/success";
	}
}
