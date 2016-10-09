package dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import models.Game;
import util.JPAUtil;
import util.Logger;

public class GameDao {	
	
	private EntityManager em = JPAUtil.getEM();
	
	public void add(Game game) {
		try {
			em.getTransaction().begin();
			em.persist(game);
			em.getTransaction().commit();
			em.close();		
		} catch (Exception e) {
			Logger.log("ERROR: Failed to commit game " + game.getId() + ". " + e.getMessage());
		}
		
	}
	
	public Long getLastGameId() {
		String jpql = "SELECT MAX(g) FROM Game g";
		TypedQuery<Game> query = em.createQuery(jpql, Game.class);
		try {
			Long result = query.getSingleResult().getId();
			return result;
		} catch (Exception e) {
			return 0L;
		}		 		
	}

}