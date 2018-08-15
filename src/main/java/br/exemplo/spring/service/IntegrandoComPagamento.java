package br.exemplo.spring.service;

import java.math.BigDecimal;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.async.DeferredResult;

import br.exemplo.spring.models.PaymentData;

public class IntegrandoComPagamento implements Runnable{

	private DeferredResult<String> result;
	private BigDecimal value; 
	private RestTemplate restTemplate;
	
	
	public IntegrandoComPagamento(DeferredResult<String> result, BigDecimal value, RestTemplate restTemplate) {
		this.result = result;
		this.value = value;
		this.restTemplate = restTemplate;
	}



	@Override
	public void run() {
		
		//codigo de integração com a aplicação web chamada book-payment, hospedada atualmente no Heroku.
		//só aceita pagamentos de até 500 reais e os dados devem ser enviados no formato Json
		String uriToPay = "http://book-payment.herokuapp.com/payment";
		
		try {
			String response = restTemplate.postForObject(uriToPay, new PaymentData(value), String.class);
			//notificação
			result.setResult("payment/success");
		} catch (HttpClientErrorException e) {
			e.printStackTrace();
			result.setResult("payment/error");
		}		
	}
	

}
