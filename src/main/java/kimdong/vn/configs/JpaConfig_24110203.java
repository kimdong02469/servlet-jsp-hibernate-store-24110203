package kimdong.vn.configs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaConfig_24110203 {
	private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("WebDB_05");

	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
}