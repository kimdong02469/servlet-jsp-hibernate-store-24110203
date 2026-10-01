package kimdong.vn.dao.impl;

import kimdong.vn.configs.JpaConfig_24110203;
import kimdong.vn.dao.ICategoryDao_24110203;
import kimdong.vn.entity.Category_24110203;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class CategoryDaoImpl_24110203 implements ICategoryDao_24110203 {
	@Override
	public void insert(Category_24110203 category) {
		EntityManager em = JpaConfig_24110203.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.persist(category);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	@Override
	public void update(Category_24110203 category) {
		EntityManager em = JpaConfig_24110203.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.merge(category);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	@Override
	public void delete(int id) {
		EntityManager em = JpaConfig_24110203.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			Category_24110203 c = em.find(Category_24110203.class, id);
			if (c != null)
				em.remove(c);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	@Override
	public Category_24110203 findById(int id) {
		EntityManager em = JpaConfig_24110203.getEntityManager();
		try {
			return em.find(Category_24110203.class, id);
		} finally {
			em.close();
		}
	}

	@Override
	public List<Category_24110203> findAll() {
		EntityManager em = JpaConfig_24110203.getEntityManager();
		try {
			return em.createQuery("SELECT c FROM Category_24110203 c", Category_24110203.class).getResultList();
		} finally {
			em.close();
		}
	}

	@Override
	public List<Category_24110203> findAll(int page, int pageSize) {
		EntityManager em = JpaConfig_24110203.getEntityManager();
		try {
			TypedQuery<Category_24110203> query = em.createQuery(
					"SELECT c FROM Category_24110203 c ORDER BY c.categoryId DESC", Category_24110203.class);
			query.setFirstResult((page - 1) * pageSize);
			query.setMaxResults(pageSize);
			return query.getResultList();
		} finally {
			em.close();
		}
	}

	@Override
	public int count() {
		EntityManager em = JpaConfig_24110203.getEntityManager();
		try {
			Long count = em.createQuery("SELECT COUNT(c) FROM Category_24110203 c", Long.class).getSingleResult();
			return count.intValue();
		} finally {
			em.close();
		}
	}
}