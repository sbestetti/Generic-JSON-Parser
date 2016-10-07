package util;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class JPAUtil {
	
	public static EntityManager getEM() {
		return Persistence.createEntityManagerFactory("GameGrabber").createEntityManager();
	}
	
	

}
