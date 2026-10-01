package kimdong.vn.controller;

import kimdong.vn.entity.Product_24110203;
import kimdong.vn.service.IProductService_24110203;
import kimdong.vn.service.impl.ProductServiceImpl_24110203;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = { "/products", "/product/detail" })
public class ProductController_24110203 extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private IProductService_24110203 productService = new ProductServiceImpl_24110203();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		if (uri.contains("/product/detail")) {
			int id = Integer.parseInt(req.getParameter("id"));
			Product_24110203 product = productService.findById(id);
			req.setAttribute("p", product);
			req.getRequestDispatcher("/WEB-INF/views/product-detail.jsp").forward(req, resp);
		} else {
			int sellerId = req.getParameter("sellerId") != null ? Integer.parseInt(req.getParameter("sellerId")) : 1;
			List<Product_24110203> list = productService.findBySeller(sellerId);
			req.setAttribute("productList", list);
			req.setAttribute("currentSellerId", sellerId);
			req.getRequestDispatcher("/WEB-INF/views/product-by-seller.jsp").forward(req, resp);
		}
	}
}