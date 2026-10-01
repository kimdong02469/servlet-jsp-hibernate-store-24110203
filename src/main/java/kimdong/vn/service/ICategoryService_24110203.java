package kimdong.vn.service;

import kimdong.vn.entity.Category_24110203;
import java.util.List;

public interface ICategoryService_24110203 {
	void insert(Category_24110203 category);

	void update(Category_24110203 category);

	void delete(int id);

	Category_24110203 findById(int id);

	List<Category_24110203> findAll();

	List<Category_24110203> findAll(int page, int pageSize);

	int count();
}