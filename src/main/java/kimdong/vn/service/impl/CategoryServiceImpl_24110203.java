package kimdong.vn.service.impl;

import kimdong.vn.dao.ICategoryDao_24110203;
import kimdong.vn.dao.impl.CategoryDaoImpl_24110203;
import kimdong.vn.entity.Category_24110203;
import kimdong.vn.service.ICategoryService_24110203;
import java.util.List;

public class CategoryServiceImpl_24110203 implements ICategoryService_24110203 {
	private ICategoryDao_24110203 categoryDao = new CategoryDaoImpl_24110203();

	@Override
	public void insert(Category_24110203 category) {
		categoryDao.insert(category);
	}

	@Override
	public void update(Category_24110203 category) {
		categoryDao.update(category);
	}

	@Override
	public void delete(int id) {
		categoryDao.delete(id);
	}

	@Override
	public Category_24110203 findById(int id) {
		return categoryDao.findById(id);
	}

	@Override
	public List<Category_24110203> findAll() {
		return categoryDao.findAll();
	}

	@Override
	public List<Category_24110203> findAll(int page, int pageSize) {
		return categoryDao.findAll(page, pageSize);
	}

	@Override
	public int count() {
		return categoryDao.count();
	}
}