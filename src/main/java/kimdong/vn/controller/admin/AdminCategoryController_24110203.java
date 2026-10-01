package kimdong.vn.controller.admin;

import kimdong.vn.entity.Category_24110203;
import kimdong.vn.service.ICategoryService_24110203;
import kimdong.vn.service.impl.CategoryServiceImpl_24110203;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = { "/admin/category", "/admin/category/add", "/admin/category/edit",
		"/admin/category/delete" })
public class AdminCategoryController_24110203 extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ICategoryService_24110203 categoryService = new CategoryServiceImpl_24110203();
	private static final int PAGE_SIZE = 5;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();

		if (path.equals("/admin/category/delete")) {
			int id = Integer.parseInt(req.getParameter("id"));
			categoryService.delete(id);
			resp.sendRedirect(req.getContextPath() + "/admin/category");
			return;
		}

		if (path.equals("/admin/category/edit")) {
			int id = Integer.parseInt(req.getParameter("id"));
			Category_24110203 c = categoryService.findById(id);
			req.setAttribute("category", c);
			req.getRequestDispatcher("/WEB-INF/views/admin/category-form.jsp").forward(req, resp);
			return;
		}

		if (path.equals("/admin/category/add")) {
			req.getRequestDispatcher("/WEB-INF/views/admin/category-form.jsp").forward(req, resp);
			return;
		}

		int page = req.getParameter("page") != null ? Integer.parseInt(req.getParameter("page")) : 1;
		int totalItems = categoryService.count();
		int totalPages = (int) Math.ceil((double) totalItems / PAGE_SIZE);

		List<Category_24110203> list = categoryService.findAll(page, PAGE_SIZE);
		req.setAttribute("categoryList", list);
		req.setAttribute("currentPage", page);
		req.setAttribute("totalPages", totalPages);
		req.getRequestDispatcher("/WEB-INF/views/admin/category-list.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String idStr = req.getParameter("id");
		String name = req.getParameter("categoryName");
		String images = req.getParameter("images");
		int status = Integer.parseInt(req.getParameter("status"));

		Category_24110203 c = new Category_24110203();
		c.setCategoryName(name);
		c.setImages(images);
		c.setStatus(status);

		if (idStr == null || idStr.isEmpty()) {
			categoryService.insert(c);
		} else {
			c.setCategoryId(Integer.parseInt(idStr));
			categoryService.update(c);
		}
		resp.sendRedirect(req.getContextPath() + "/admin/category");
	}
}