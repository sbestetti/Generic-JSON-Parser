package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import models.Platform;
import util.JPAUtil;

public class PlatformDao {
	
	private EntityManager em = JPAUtil.getEM();
	
	public void add(Platform platform) {
		em.getTransaction().begin();
		em.persist(platform);
		em.getTransaction().commit();
		em.close();		
	}
	
	public void addList(List<Platform> list) {
		String jpql = "SELECT p FROM Platform p WHERE id = :pid";
		TypedQuery<Platform> query = em.createQuery(jpql, Platform.class);
		em.getTransaction().begin();
		for (Platform p : list) {
			query.setParameter("pid", p.getId());
			if (query.getResultList().isEmpty()) {
				em.persist(p);
			}						
		}
		em.getTransaction().commit();
		em.close();		
	}

}
