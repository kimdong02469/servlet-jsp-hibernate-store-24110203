package kimdong.vn.controller.admin;

import kimdong.vn.entity.Category_24110203;
import kimdong.vn.entity.Product_24110203;
import kimdong.vn.service.ICategoryService_24110203;
import kimdong.vn.service.IProductService_24110203;
import kimdong.vn.service.impl.CategoryServiceImpl_24110203;
import kimdong.vn.service.impl.ProductServiceImpl_24110203;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = { "/admin/product", "/admin/product/add", "/admin/product/edit", "/admin/product/delete" })
public class AdminProductController_24110203 extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private IProductService_24110203 productService = new ProductServiceImpl_24110203();
	private ICategoryService_24110203 categoryService = new CategoryServiceImpl_24110203();
	private static final int PAGE_SIZE = 5;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();

		if (path.equals("/admin/product/delete")) {
			int id = Integer.parseInt(req.getParameter("id"));
			productService.delete(id);
			resp.sendRedirect(req.getContextPath() + "/admin/product");
			return;
		}

		if (path.equals("/admin/product/add") || path.equals("/admin/product/edit")) {
			if (path.equals("/admin/product/edit")) {
				int id = Integer.parseInt(req.getParameter("id"));
				Product_24110203 p = productService.findById(id);
				req.setAttribute("product", p);
			}
			req.setAttribute("categories", categoryService.findAll());
			req.getRequestDispatcher("/WEB-INF/views/admin/product-form.jsp").forward(req, resp);
			return;
		}

		int page = req.getParameter("page") != null ? Integer.parseInt(req.getParameter("page")) : 1;
		int totalItems = productService.count();
		int totalPages = (int) Math.ceil((double) totalItems / PAGE_SIZE);

		List<Product_24110203> list = productService.findAll(page, PAGE_SIZE);
		req.setAttribute("productList", list);
		req.setAttribute("currentPage", page);
		req.setAttribute("totalPages", totalPages);
		req.getRequestDispatcher("/WEB-INF/views/admin/product-list.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		String idStr = req.getParameter("id");
		String name = req.getParameter("productName");
		int code = Integer.parseInt(req.getParameter("productCode"));
		int categoryId = Integer.parseInt(req.getParameter("categoryId"));
		int sellerId = Integer.parseInt(req.getParameter("sellerId"));
		double price = Double.parseDouble(req.getParameter("price"));
		int amount = Integer.parseInt(req.getParameter("amount"));

		// Đọc stock gửi lên từ form (nếu form có ô nhập stock)
		String stockStr = req.getParameter("stock");
		String images = req.getParameter("images");
		String description = req.getParameter("description");

		Category_24110203 category = categoryService.findById(categoryId);

		if (idStr == null || idStr.trim().isEmpty()) {
			// --- TRƯỜNG HỢP THÊM MỚI ---
			Product_24110203 p = new Product_24110203();
			p.setProductName(name);
			p.setProductCode(code);
			p.setCategory(category);
			p.setSellerId(sellerId);
			p.setPrice(price);
			p.setAmount(amount);
			// Khi tạo mới: Tồn kho stock mặc định bằng amount (hoặc lấy theo ô stock nhập
			// vào)
			int initialStock = (stockStr != null && !stockStr.trim().isEmpty()) ? Integer.parseInt(stockStr) : amount;
			p.setStock(initialStock);
			p.setImages(images);
			p.setDescription(description);
			p.setStatus(1);

			productService.insert(p);
		} else {
			// --- TRƯỜNG HỢP ADMIN SỬA SẢN PHẨM ---
			int id = Integer.parseInt(idStr);
			Product_24110203 p = productService.findById(id);
			if (p != null) {
				p.setProductName(name);
				p.setProductCode(code);
				p.setCategory(category);
				p.setSellerId(sellerId);
				p.setPrice(price);

				// LOGIC CẬP NHẬT TỒN KHO:
				if (stockStr != null && !stockStr.trim().isEmpty()) {
					// Nếu Admin nhập trực tiếp số lượng tồn kho mới vào ô stock:
					p.setStock(Integer.parseInt(stockStr));
					p.setAmount(amount);
				} else {
					// Nếu Admin tăng ô amount (nhập thêm hàng):
					// Cộng thêm lượng chênh lệch (amount mới - amount cũ) vào tồn kho hiện tại
					int diff = amount - p.getAmount();
					if (diff > 0) {
						int currentStock = (p.getStock() != null) ? p.getStock() : 0;
						p.setStock(currentStock + diff);
					}
					p.setAmount(amount);
				}

				p.setImages(images);
				p.setDescription(description);

				productService.update(p);
			}
		}

		resp.sendRedirect(req.getContextPath() + "/admin/product");
	}
}