package kimdong.vn.service.impl;

import kimdong.vn.dao.IProductDao_24110203;
import kimdong.vn.dao.impl.ProductDaoImpl_24110203;
import kimdong.vn.entity.Product_24110203;
import kimdong.vn.service.IProductService_24110203;
import java.util.List;

public class ProductServiceImpl_24110203 implements IProductService_24110203 {
	private IProductDao_24110203 productDao = new ProductDaoImpl_24110203();

	@Override
	public void insert(Product_24110203 product) {
		productDao.insert(product);
	}

	@Override
	public void update(Product_24110203 product) {
		productDao.update(product);
	}

	@Override
	public void delete(int id) {
		productDao.delete(id);
	}

	@Override
	public Product_24110203 findById(int id) {
		return productDao.findById(id);
	}

	@Override
	public List<Product_24110203> findBySeller(int sellerId) {
		return productDao.findBySeller(sellerId);
	}

	@Override
	public List<Product_24110203> findAll(int page, int pageSize) {
		return productDao.findAll(page, pageSize);
	}

	@Override
	public int count() {
		return (int) productDao.count();
	}
}