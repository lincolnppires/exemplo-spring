package br.exemplo.spring.daos;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.exemplo.spring.builders.ProductBuilder;
import br.exemplo.spring.conf.DataSourceConfigurationTest;
import br.exemplo.spring.conf.JPAConfiguration;
import br.exemplo.spring.models.BookType;
import br.exemplo.spring.models.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {DataSourceConfigurationTest.class, ProductDAO.class, JPAConfiguration.class})
@ActiveProfiles("test")
public class ProductDAOTest {

	@Autowired
	private ProductDAO productDAO;
	
	@Transactional
	@Test
	public void shouldSumAllPricesOfEachBookPerType() {
		
		List<Product> printedBooks = ProductBuilder
				.newProduct(BookType.PRINTED, BigDecimal.TEN).more(4)
				.buildAll();
		printedBooks.stream().forEach(productDAO::save);
			
		List<Product> ebooks = ProductBuilder
				.newProduct(BookType.EBOOK, BigDecimal.TEN).more(4).buildAll();
		ebooks.stream().forEach(productDAO::save);
		
//		try {
//			Thread.sleep(20000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

		
		
		BigDecimal value = productDAO.sumPricesPerType(BookType.PRINTED);
		assertEquals(new BigDecimal(50).setScale(2), value);
		
	}
}
