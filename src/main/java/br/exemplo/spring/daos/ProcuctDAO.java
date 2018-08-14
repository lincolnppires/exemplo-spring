package br.exemplo.spring.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.exemplo.spring.models.Product;

@Repository
public class ProcuctDAO {
	
	@PersistenceContext
	private EntityManager manager;

	public void save(Product product) {
		manager.persist(product);
	}

	public List<Product> list() {
		List<Product> resultList = manager.createQuery("select distinct(p) from Product p join fetch p.prices order by p.id desc", Product.class)
										.getResultList();
		return resultList;
	}

}
