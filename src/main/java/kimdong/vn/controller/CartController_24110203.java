package kimdong.vn.controller;

import kimdong.vn.dao.IOrderDao_24110203;
import kimdong.vn.dao.IProductDao_24110203;
import kimdong.vn.dao.impl.OrderDaoImpl_24110203;
import kimdong.vn.dao.impl.ProductDaoImpl_24110203;
import kimdong.vn.entity.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns = { "/cart", "/cart/add", "/cart/update", "/cart/delete", "/checkout", "/order-history" })
public class CartController_24110203 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IProductDao_24110203 productDao = new ProductDaoImpl_24110203();
	private IOrderDao_24110203 orderDao = new OrderDaoImpl_24110203();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		HttpSession session = req.getSession();
		Map<Integer, CartItemBean_24110203> cart = (Map<Integer, CartItemBean_24110203>) session.getAttribute("cart");
		if (cart == null)
			cart = new HashMap<>();

		if (path.equals("/cart")) {
			double totalMoney = cart.values().stream().mapToDouble(CartItemBean_24110203::getTotal).sum();
			req.setAttribute("cartItems", cart.values());
			req.setAttribute("totalMoney", totalMoney);
			req.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(req, resp);

		} else if (path.equals("/cart/add")) {
			int productId = Integer.parseInt(req.getParameter("id"));
			Product_24110203 product = productDao.findById(productId);

			if (product != null) {
				int maxStock = (product.getStock() != null) ? product.getStock() : 0;

				// Nếu sản phẩm đã hết hàng trong kho thì không cho thêm
				if (maxStock <= 0) {
					resp.sendRedirect(req.getContextPath() + "/products?error=outofstock");
					return;
				}

				CartItemBean_24110203 item = cart.get(productId);
				int currentQty = (item == null) ? 0 : item.getQuantity();

				// Chỉ cho thêm nếu không vượt quá tồn kho
				if (currentQty + 1 <= maxStock) {
					if (item == null) {
						cart.put(productId, new CartItemBean_24110203(product, 1));
					} else {
						item.setQuantity(currentQty + 1);
					}
				}
			}
			session.setAttribute("cart", cart);
			resp.sendRedirect(req.getContextPath() + "/cart");

		} else if (path.equals("/cart/delete")) {
			int productId = Integer.parseInt(req.getParameter("id"));
			cart.remove(productId);
			session.setAttribute("cart", cart);
			resp.sendRedirect(req.getContextPath() + "/cart");

		} else if (path.equals("/order-history")) {
			Users_24110203 user = (Users_24110203) session.getAttribute("account");
			if (user == null) {
				resp.sendRedirect(req.getContextPath() + "/login");
				return;
			}
			String status = req.getParameter("status");
			if (status == null || status.trim().isEmpty()) {
				status = "all";
			}
			List<Orders_24110203> list = orderDao.findByUserIdAndStatus(user.getUserId(), status);
			req.setAttribute("orders", list);
			req.setAttribute("selectedStatus", status);
			req.getRequestDispatcher("/WEB-INF/views/order-history.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String path = req.getServletPath();
		HttpSession session = req.getSession();
		Map<Integer, CartItemBean_24110203> cart = (Map<Integer, CartItemBean_24110203>) session.getAttribute("cart");

		if (path.equals("/cart/update")) {
			int productId = Integer.parseInt(req.getParameter("id"));
			int quantity = Integer.parseInt(req.getParameter("quantity"));
			Product_24110203 product = productDao.findById(productId);

			if (cart != null && cart.containsKey(productId) && product != null) {
				int maxStock = (product.getStock() != null) ? product.getStock() : 0;
				if (quantity <= 0) {
					cart.remove(productId);
				} else if (quantity <= maxStock) {
					cart.get(productId).setQuantity(quantity);
				} else {
					cart.get(productId).setQuantity(maxStock);
				}
			}
			session.setAttribute("cart", cart);
			resp.sendRedirect(req.getContextPath() + "/cart");

		} else if (path.equals("/checkout")) {
			Users_24110203 user = (Users_24110203) session.getAttribute("account");
			if (user == null) {
				resp.sendRedirect(req.getContextPath() + "/login");
				return;
			}
			if (cart == null || cart.isEmpty()) {
				resp.sendRedirect(req.getContextPath() + "/cart");
				return;
			}

			// 1. Kiểm tra lại toàn bộ tồn kho thực tế trước khi xác nhận trừ
			for (CartItemBean_24110203 itemBean : cart.values()) {
				Product_24110203 currentProduct = productDao.findById(itemBean.getProduct().getProductId());
				int availableStock = (currentProduct != null && currentProduct.getStock() != null)
						? currentProduct.getStock()
						: 0;

				if (itemBean.getQuantity() > availableStock) {
					req.setAttribute("error", "Sản phẩm '" + itemBean.getProduct().getProductName()
							+ "' không đủ số lượng trong kho (còn lại: " + availableStock + ")!");
					double totalMoney = cart.values().stream().mapToDouble(CartItemBean_24110203::getTotal).sum();
					req.setAttribute("cartItems", cart.values());
					req.setAttribute("totalMoney", totalMoney);
					req.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(req, resp);
					return;
				}
			}

			// 2. Tạo đối tượng đơn hàng COD
			String fullname = req.getParameter("fullname");
			String phone = req.getParameter("phone");
			String address = req.getParameter("address");
			double totalMoney = cart.values().stream().mapToDouble(CartItemBean_24110203::getTotal).sum();

			Orders_24110203 order = new Orders_24110203();
			order.setUser(user);
			order.setFullname(fullname);
			order.setPhone(phone);
			order.setAddress(address);
			order.setTotalPrice(totalMoney);
			order.setPaymentMethod("COD");
			order.setStatus("Đơn hàng mới");

			List<OrderItems_24110203> items = new ArrayList<>();

			// 3. Lưu chi tiết đơn hàng VÀ TRỪ TRỰC TIẾP TỒN KHO TẠI THỜI ĐIỂM NÀY
			for (CartItemBean_24110203 itemBean : cart.values()) {
				Product_24110203 p = productDao.findById(itemBean.getProduct().getProductId());

				OrderItems_24110203 item = new OrderItems_24110203();
				item.setOrder(order);
				item.setProduct(p);
				item.setCount(itemBean.getQuantity());
				item.setPrice(p.getPrice());
				items.add(item);

				// TRỪ TỒN KHO:
				int currentStock = (p.getStock() != null) ? p.getStock() : 0;
				int remainingStock = Math.max(0, currentStock - itemBean.getQuantity());
				p.setStock(remainingStock);

				// Cập nhật lại tồn kho mới vào Database
				productDao.update(p);
			}
			order.setOrderItems(items);

			// Lưu đơn hàng vào Database
			orderDao.createOrder(order);

			// Xóa sạch giỏ hàng trong session và chuyển tới trang lịch sử đơn hàng
			session.removeAttribute("cart");
			resp.sendRedirect(req.getContextPath() + "/order-history");
		}
	}
}