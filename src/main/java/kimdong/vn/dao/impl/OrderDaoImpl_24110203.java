package kimdong.vn.dao.impl;

import kimdong.vn.configs.JpaConfig_24110203;
import kimdong.vn.dao.IOrderDao_24110203;
import kimdong.vn.entity.OrderItems_24110203;
import kimdong.vn.entity.Orders_24110203;
import kimdong.vn.entity.Product_24110203;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class OrderDaoImpl_24110203 implements IOrderDao_24110203 {

	@Override
	public void createOrder(Orders_24110203 order) {
		EntityManager em = JpaConfig_24110203.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.persist(order);
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
	public List<Orders_24110203> findByUserIdAndStatus(int userId, String status) {
		EntityManager em = JpaConfig_24110203.getEntityManager();
		try {
			String jpql = "SELECT o FROM Orders_24110203 o WHERE o.user.userId = :userId";
			if (status != null && !status.trim().isEmpty() && !status.equalsIgnoreCase("all")) {
				jpql += " AND o.status = :status";
			}
			jpql += " ORDER BY o.orderId DESC";

			TypedQuery<Orders_24110203> query = em.createQuery(jpql, Orders_24110203.class);
			query.setParameter("userId", userId);
			if (status != null && !status.trim().isEmpty() && !status.equalsIgnoreCase("all")) {
				query.setParameter("status", status.trim());
			}
			return query.getResultList();
		} finally {
			em.close();
		}
	}

	@Override
	public Orders_24110203 findById(int orderId) {
		EntityManager em = JpaConfig_24110203.getEntityManager();
		try {
			return em.find(Orders_24110203.class, orderId);
		} finally {
			em.close();
		}
	}

	@Override
	public void updateOrderStatus(int orderId, String newStatus) {
		EntityManager em = JpaConfig_24110203.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			Orders_24110203 order = em.find(Orders_24110203.class, orderId);
			if (order != null) {
				String oldStatus = order.getStatus() != null ? order.getStatus().trim() : "";
				String targetStatus = newStatus != null ? newStatus.trim() : "";

				// 1. Nếu chuyển sang "Đã xác nhận" từ trạng thái chưa duyệt -> Trừ kho
				if (!oldStatus.equalsIgnoreCase("Đã xác nhận") && targetStatus.equalsIgnoreCase("Đã xác nhận")) {
					for (OrderItems_24110203 item : order.getOrderItems()) {
						Product_24110203 p = em.find(Product_24110203.class, item.getProduct().getProductId());
						if (p != null) {
							int currentStock = (p.getStock() != null) ? p.getStock() : 0;
							p.setStock(Math.max(0, currentStock - item.getCount()));
							em.merge(p);
						}
					}
				}

				// 2. Nếu đơn đã từng duyệt (Đã xác nhận / Vận chuyển / Giao hàng) nay bị Hoàn
				// hoặc Hủy -> Cộng lại kho
				boolean wasConfirmed = oldStatus.equalsIgnoreCase("Đã xác nhận")
						|| oldStatus.equalsIgnoreCase("Chuẩn bị hàng") || oldStatus.equalsIgnoreCase("Vận chuyển")
						|| oldStatus.equalsIgnoreCase("Giao hàng");

				boolean isCanceledOrReturned = targetStatus.equalsIgnoreCase("Đơn hàng hoàn")
						|| targetStatus.equalsIgnoreCase("Đơn hàng hủy");

				if (wasConfirmed && isCanceledOrReturned) {
					for (OrderItems_24110203 item : order.getOrderItems()) {
						Product_24110203 p = em.find(Product_24110203.class, item.getProduct().getProductId());
						if (p != null) {
							int currentStock = (p.getStock() != null) ? p.getStock() : 0;
							p.setStock(currentStock + item.getCount());
							em.merge(p);
						}
					}
				}

				order.setStatus(targetStatus);
				em.merge(order);
			}
			trans.commit();
		} catch (Exception e) {
			if (trans.isActive())
				trans.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
}