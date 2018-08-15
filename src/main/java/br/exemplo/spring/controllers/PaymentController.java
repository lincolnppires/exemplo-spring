package br.exemplo.spring.controllers;

import java.math.BigDecimal;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.async.DeferredResult;

import br.exemplo.spring.models.PaymentData;
import br.exemplo.spring.models.ShoppingCart;
import br.exemplo.spring.service.IntegrandoComPagamento;

@Controller
@RequestMapping("/payment")
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class PaymentController {

	@Autowired
	private ShoppingCart shoppingCart;
	@Autowired
	private RestTemplate restTemplate;
	

	@RequestMapping(value = "checkout", method = RequestMethod.POST, name = "checkoutPaymentController")
	public DeferredResult<String> checkout() {
		
		BigDecimal total = shoppingCart.getTotal();
		DeferredResult<String> result = new DeferredResult();
		
		IntegrandoComPagamento integrandoComPagamento = new IntegrandoComPagamento(result, total, restTemplate);
				
		Thread thread = new Thread(integrandoComPagamento);
		thread.start();
		return result;
			
	}
}
