package br.exemplo.spring.controllers;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import br.exemplo.spring.models.PaymentData;
import br.exemplo.spring.models.ShoppingCart;

@Controller
@RequestMapping("/payment")
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class PaymentController {

	@Autowired
	private ShoppingCart shoppingCart;
	@Autowired
	private RestTemplate restTemplate;
	

	@RequestMapping(value = "checkout", method = RequestMethod.POST, name = "checkoutPaymentController")
	public String checkout() {
		BigDecimal total = shoppingCart.getTotal();
		
		//codigo de integração com a aplicação web chamada book-payment, hospedada atualmente no Heroku.
		//só aceita pagamentos de até 500 reais e os dados devem ser enviados no formato Json
		String uriToPay = "http://book-payment.herokuapp.com/payment";
		
		try {
			String response = restTemplate.postForObject(uriToPay, new PaymentData(total), String.class);
			return "payment/success";
		} catch (HttpClientErrorException e) {
			e.printStackTrace();
			return "payment/error";
		}
		
	}
}
