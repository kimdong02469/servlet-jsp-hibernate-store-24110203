<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Xác Thực OTP - kimdong.vn</title>
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
	border-bottom: 2px solid #f59e0b;
	padding: 24px 20px;
}

.otp-input {
	letter-spacing: 8px;
	font-weight: 700;
	font-size: 1.5rem;
	text-align: center;
	border-radius: 8px;
	border: 2px solid #cbd5e1;
}

.otp-input:focus {
	border-color: #f59e0b;
	box-shadow: 0 0 0 3px rgba(245, 158, 11, 0.2);
}
</style>
</head>
<body class="d-flex align-items-center min-vh-100 py-5">
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-12 col-sm-10 col-md-7 col-lg-5 col-xl-4">
				<div class="auth-card overflow-hidden">
					<div class="auth-header text-center text-white">
						<i class="fa-solid fa-shield-halved text-warning fs-2 mb-2"></i>
						<h5 class="fw-bold mb-1">Xác Thực Mã OTP</h5>
						<p class="small text-white-50 mb-0">Nhập mã 6 chữ số gửi qua
							email của bạn</p>
					</div>
					<div class="p-4 p-md-5">
						<c:if test="${not empty error}">
							<div
								class="alert alert-danger py-2 small rounded-3 mb-3 text-center">
								<i class="fa-solid fa-circle-exclamation me-1"></i> ${error}
							</div>
						</c:if>

						<form action="${pageContext.request.contextPath}/verify-otp"
							method="post">
							<!-- Giữ email qua form để tránh mất Session -->
							<input type="hidden" name="email"
								value="${not empty sessionScope.registeredEmail ? sessionScope.registeredEmail : param.email}" />

							<div class="mb-4">
								<input type="text" name="otp" maxlength="6"
									class="form-control otp-input py-2" placeholder="••••••"
									required autofocus autocomplete="off" />
							</div>
							<button type="submit"
								class="btn btn-warning text-dark fw-bold w-100 py-2 rounded-3 shadow-sm mb-3">
								<i class="fa-solid fa-circle-check me-1"></i> Kích Hoạt Tài
								Khoản
							</button>
							<div class="text-center">
								<a href="${pageContext.request.contextPath}/register"
									class="small text-secondary text-decoration-none"> <i
									class="fa-solid fa-arrow-left me-1"></i> Quay lại đăng ký
								</a>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>