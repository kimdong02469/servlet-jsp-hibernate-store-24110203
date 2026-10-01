<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Đăng Ký - kimdong.vn</title>
<link
	href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
	rel="stylesheet">
<style>
body {
	font-family: 'Inter', sans-serif;
	background-color: #f1f5f9;
}

.auth-card {
	border: none;
	border-radius: 14px;
	box-shadow: 0 10px 25px -5px rgba(0, 0, 0, 0.08);
	background: #ffffff;
}

.auth-header {
	background: #1e2022;
	border-top-left-radius: 14px;
	border-top-right-radius: 14px;
	border-bottom: 2px solid #22c55e;
	padding: 24px 20px;
}
</style>
</head>
<body class="d-flex align-items-center min-vh-100 py-5">
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-12 col-sm-10 col-md-8 col-lg-6 col-xl-5">
				<div class="auth-card overflow-hidden">
					<div class="auth-header text-center text-white">
						<h4 class="fw-bold mb-1">
							<span class="text-warning">kimdong</span>.vn
						</h4>
						<p class="small text-white-50 mb-0">Đăng Ký Tài Khoản Nhận OTP
							Qua Mail</p>
					</div>
					<div class="p-4 p-md-5">
						<c:if test="${not empty error}">
							<div class="alert alert-danger py-2 small rounded-3 mb-3">${error}</div>
						</c:if>
						<form action="${pageContext.request.contextPath}/register"
							method="post">
							<div class="mb-3">
								<label class="form-label small fw-semibold text-secondary">Tên
									đăng nhập</label> <input type="text" name="username"
									class="form-control" required />
							</div>
							<div class="mb-3">
								<label class="form-label small fw-semibold text-secondary">Họ
									và tên</label> <input type="text" name="fullname" class="form-control"
									required />
							</div>
							<div class="mb-3">
								<label class="form-label small fw-semibold text-secondary">Email
									nhận mã OTP</label> <input type="email" name="email"
									class="form-control" placeholder="name@example.com" required />
							</div>
							<div class="mb-4">
								<label class="form-label small fw-semibold text-secondary">Mật
									khẩu</label> <input type="password" name="password"
									class="form-control" required />
							</div>
							<button type="submit"
								class="btn btn-success w-100 mb-3 fw-semibold py-2">
								<i class="fa-solid fa-paper-plane me-1"></i> Đăng Ký &amp; Nhận
								OTP
							</button>
							<div class="text-center pt-2 border-top">
								<span class="small text-muted">Đã có tài khoản?</span> <a
									href="${pageContext.request.contextPath}/login"
									class="small fw-semibold text-decoration-none text-primary ms-1">Đăng
									nhập</a>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>