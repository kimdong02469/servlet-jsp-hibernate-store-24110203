package kimdong.vn.dao.impl;

import kimdong.vn.configs.JpaConfig_24110203;
import kimdong.vn.dao.IUserDao_24110203;
import kimdong.vn.entity.Users_24110203;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class UserDaoImpl_24110203 implements IUserDao_24110203 {
	@Override
	public void insert(Users_24110203 user) {
		EntityManager em = JpaConfig_24110203.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.persist(user);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	@Override
	public void update(Users_24110203 user) {
		EntityManager em = JpaConfig_24110203.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.merge(user);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	@Override
	public Users_24110203 findByUsername(String username) {
		EntityManager em = JpaConfig_24110203.getEntityManager();
		try {
			TypedQuery<Users_24110203> query = em
					.createQuery("SELECT u FROM Users_24110203 u WHERE u.username = :username", Users_24110203.class);
			query.setParameter("username", username);
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		} finally {
			em.close();
		}
	}

	@Override
	public Users_24110203 findByEmail(String email) {
		EntityManager em = JpaConfig_24110203.getEntityManager();
		try {
			TypedQuery<Users_24110203> q = em.createQuery(
					"SELECT u FROM Users_24110203 u WHERE u.email = :email ORDER BY u.userId DESC",
					Users_24110203.class);
			q.setParameter("email", email.trim());
			q.setMaxResults(1); // Chỉ lấy 1 bản ghi mới nhất
			List<Users_24110203> list = q.getResultList();
			return list.isEmpty() ? null : list.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}
}