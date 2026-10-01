package kimdong.vn.dao;

import kimdong.vn.entity.Product_24110203;
import java.util.List;

public interface IProductDao_24110203 {
	void insert(Product_24110203 product);

	void update(Product_24110203 product);

	void delete(int id);

	Product_24110203 findById(int id);

	List<Product_24110203> findAll();

	List<Product_24110203> findBySeller(int sellerId);

	List<Product_24110203> findAll(int page, int pageSize);

	long count();
}