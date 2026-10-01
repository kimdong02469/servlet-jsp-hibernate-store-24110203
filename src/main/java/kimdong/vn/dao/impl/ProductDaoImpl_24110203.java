package kimdong.vn.dao.impl;

import kimdong.vn.configs.JpaConfig_24110203;
import kimdong.vn.dao.IProductDao_24110203;
import kimdong.vn.entity.Product_24110203;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class ProductDaoImpl_24110203 implements IProductDao_24110203 {

	@Override
	public void insert(Product_24110203 product) {
		EntityManager em = JpaConfig_24110203.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.persist(product);
			trans.commit();
		} catch (Exception e) {
			if (trans.isActive())
				trans.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	@Override
	public void update(Product_24110203 product) {
		EntityManager em = JpaConfig_24110203.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.merge(product);
			trans.commit();
		} catch (Exception e) {
			if (trans.isActive())
				trans.rollback();
			e.printStackTrace();
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
			Product_24110203 p = em.find(Product_24110203.class, id);
			if (p != null)
				em.remove(p);
			trans.commit();
		} catch (Exception e) {
			if (trans.isActive())
				trans.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	@Override
	public Product_24110203 findById(int id) {
		EntityManager em = JpaConfig_24110203.getEntityManager();
		try {
			return em.find(Product_24110203.class, id);
		} finally {
			em.close();
		}
	}

	@Override
	public List<Product_24110203> findAll() {
		EntityManager em = JpaConfig_24110203.getEntityManager();
		try {
			return em.createQuery("SELECT p FROM Product_24110203 p ORDER BY p.productId DESC", Product_24110203.class)
					.getResultList();
		} finally {
			em.close();
		}
	}

	@Override
	public List<Product_24110203> findBySeller(int sellerId) {
		EntityManager em = JpaConfig_24110203.getEntityManager();
		try {
			TypedQuery<Product_24110203> q = em.createQuery(
					"SELECT p FROM Product_24110203 p WHERE p.sellerId = :sellerId ORDER BY p.productId DESC",
					Product_24110203.class);
			q.setParameter("sellerId", sellerId);
			return q.getResultList();
		} finally {
			em.close();
		}
	}

	@Override
	public List<Product_24110203> findAll(int page, int pageSize) {
		EntityManager em = JpaConfig_24110203.getEntityManager();
		try {
			TypedQuery<Product_24110203> q = em
					.createQuery("SELECT p FROM Product_24110203 p ORDER BY p.productId DESC", Product_24110203.class);
			q.setFirstResult((page - 1) * pageSize);
			q.setMaxResults(pageSize);
			return q.getResultList();
		} finally {
			em.close();
		}
	}

	@Override
	public long count() {
		EntityManager em = JpaConfig_24110203.getEntityManager();
		try {
			return em.createQuery("SELECT COUNT(p) FROM Product_24110203 p", Long.class).getSingleResult();
		} finally {
			em.close();
		}
	}
}