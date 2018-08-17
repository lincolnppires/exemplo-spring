package br.exemplo.spring.daos;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.ParameterNameProvider;

import org.springframework.stereotype.Repository;

import br.exemplo.spring.models.BookType;
import br.exemplo.spring.models.Product;

@Repository
public class ProductDAO {

	@PersistenceContext
	private EntityManager manager;

	public void save(Product product) {
		manager.persist(product);
	}

	public List<Product> list() {
		List<Product> resultList = manager
				.createQuery("select distinct(p) from Product p join fetch p.prices order by p.id desc", Product.class)
				.getResultList();
		return resultList;
	}

	public Product find(Integer id) {

		TypedQuery<Product> createQuery = manager.createQuery(
				"select (p) from Product p join fetch p.prices " + "where p.id = :pId order by p.id desc ",
				Product.class);

		createQuery.setParameter("pId", id);
		Product product = createQuery.getSingleResult();

		return product;

	}

	public BigDecimal sumPricesPerType(BookType bookType) {

		TypedQuery<BigDecimal> query = manager.createQuery(
				"select sum(price.value) from Product p join p.prices price where price.bookType =:bookType",
				BigDecimal.class);
		query.setParameter("bookType", bookType);
		return query.getSingleResult();
	}

}
