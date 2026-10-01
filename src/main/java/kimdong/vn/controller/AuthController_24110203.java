package kimdong.vn.controller;

import kimdong.vn.configs.EmailUtil_24110203;
import kimdong.vn.entity.Users_24110203;
import kimdong.vn.service.IUserService_24110203;
import kimdong.vn.service.impl.UserServiceImpl_24110203;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Random;

@WebServlet(urlPatterns = { "/login", "/logout", "/register", "/verify-otp" })
public class AuthController_24110203 extends HttpServlet {
	private IUserService_24110203 userService = new UserServiceImpl_24110203();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		switch (path) {
		case "/register" -> req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
		case "/verify-otp" -> req.getRequestDispatcher("/WEB-INF/views/verify.jsp").forward(req, resp);
		case "/login" -> req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
		case "/logout" -> {
			HttpSession session = req.getSession(false);
			if (session != null)
				session.invalidate();
			resp.sendRedirect(req.getContextPath() + "/login");
		}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String path = req.getServletPath();

		if (path.equals("/register")) {
			String username = req.getParameter("username");
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			String fullname = req.getParameter("fullname");

			String otp = String.valueOf(100000 + new Random().nextInt(900000));

			Users_24110203 user = new Users_24110203();
			user.setUsername(username);
			user.setEmail(email);
			user.setPassword(password);
			user.setFullname(fullname);
			user.setStatus(0);
			user.setCode(otp);
			user.setRoleId(2); // Vai trò User

			try {
				userService.register(user);
				EmailUtil_24110203.sendEmail(email, "Mã kích hoạt OTP", "Mã xác thực của bạn là: <b>" + otp + "</b>");

				// Lưu vào session và mang theo tham số URL dự phòng
				req.getSession().setAttribute("registeredEmail", email.trim());
				resp.sendRedirect(req.getContextPath() + "/verify-otp?email=" + email.trim());
			} catch (Exception e) {
				e.printStackTrace();
				req.setAttribute("error", "Tên đăng nhập hoặc email đã tồn tại!");
				req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
			}

		} else if (path.equals("/verify-otp")) {
			// Lấy email từ request param trước, nếu null thì lấy từ session
			String email = req.getParameter("email");
			if (email == null || email.trim().isEmpty()) {
				email = (String) req.getSession().getAttribute("registeredEmail");
			}

			String otp = req.getParameter("otp");
			if (otp != null) {
				otp = otp.trim();
			}

			if (email != null && userService.verifyOtp(email.trim(), otp)) {
				req.getSession().removeAttribute("registeredEmail");
				resp.sendRedirect(req.getContextPath() + "/login");
			} else {
				req.setAttribute("error", "Mã kích hoạt không đúng!");
				req.getRequestDispatcher("/WEB-INF/views/verify.jsp").forward(req, resp);
			}

		} else if (path.equals("/login")) {
			String username = req.getParameter("username");
			String pass = req.getParameter("password");

			Users_24110203 user = userService.login(username, pass);
			if (user != null) {
				HttpSession session = req.getSession();
				session.setAttribute("account", user);

				// Điều hướng phân quyền
				if (user.getRoleId() != null && user.getRoleId() == 1) {
					resp.sendRedirect(req.getContextPath() + "/admin/category");
				} else if (user.getSellerId() != null) {
					resp.sendRedirect(req.getContextPath() + "/products?sellerId=" + user.getSellerId());
				} else {
					resp.sendRedirect(req.getContextPath() + "/home");
				}
			} else {
				req.setAttribute("error", "Sai tài khoản, mật khẩu hoặc tài khoản chưa được kích hoạt OTP!");
				req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
			}
		}
	}
}