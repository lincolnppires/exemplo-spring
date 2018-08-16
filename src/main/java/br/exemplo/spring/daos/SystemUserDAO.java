package br.exemplo.spring.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.exemplo.spring.models.SystemUser;;

@Repository
public class SystemUserDAO implements UserDetailsService {

	@PersistenceContext
	EntityManager em;	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		String jpql = "select u from SystemUser u where u.login = :login";
		
		List<SystemUser> users = em.createQuery(jpql, SystemUser.class)
			.setParameter("login", username)
			.getResultList();
		
		if(users.isEmpty())
			throw new UsernameNotFoundException("O usuario " + username + " nao existe.");
		
		return users.get(0);
	}

}
