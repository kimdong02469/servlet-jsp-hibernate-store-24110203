package kimdong.vn.dao;

import kimdong.vn.entity.Orders_24110203;
import java.util.List;

public interface IOrderDao_24110203 {
	void createOrder(Orders_24110203 order);

	List<Orders_24110203> findByUserIdAndStatus(int userId, String status);

	Orders_24110203 findById(int orderId);

	void updateOrderStatus(int orderId, String newStatus);
}