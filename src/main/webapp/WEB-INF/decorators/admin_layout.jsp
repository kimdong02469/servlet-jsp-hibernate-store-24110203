<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title><sitemesh:write property='title' /></title>
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
	color: #1e293b;
}

header {
	background-color: #1a1d20;
	border-bottom: 2px solid #f59e0b;
	padding: 12px 0;
}

.logo {
	color: #f59e0b;
	font-weight: bold;
	font-size: 1.3rem;
	text-decoration: none;
	display: flex;
	align-items: center;
	gap: 8px;
}

.nav-admin-link {
	color: #cbd5e1;
	text-decoration: none;
	padding: 6px 12px;
	border-radius: 6px;
	font-size: 0.9rem;
	font-weight: 500;
}

.nav-admin-link:hover {
	background-color: #334155;
	color: #fff;
}

footer {
	background-color: #1a1d20;
	color: #94a3b8;
	padding: 18px 0;
	margin-top: auto;
	text-align: center;
	font-size: 0.9rem;
}
</style>
<sitemesh:write property='head' />
</head>
<body class="d-flex flex-column min-vh-100">

	<header>
		<div
			class="container-fluid px-4 d-flex justify-content-between align-items-center">
			<div class="d-flex align-items-center gap-4">
				<a href="${pageContext.request.contextPath}/home" class="logo">
					<i class="fa-solid fa-cube"></i> kimdong.vn <span
					style="font-size: 0.8rem; background: #f59e0b; color: #000; padding: 2px 6px; border-radius: 4px;">ADMIN</span>
				</a>
				<nav class="d-flex gap-2">
					<a href="${pageContext.request.contextPath}/admin/category"
						class="nav-admin-link"><i class="fa-solid fa-layer-group me-1"></i>
						Quản lý Category</a> <a
						href="${pageContext.request.contextPath}/admin/product"
						class="nav-admin-link"><i class="fa-solid fa-box-open me-1"></i>
						Quản lý Product</a>
				</nav>
			</div>
			<div class="d-flex gap-2">
				<a href="${pageContext.request.contextPath}/home"
					class="btn btn-sm btn-secondary"><i
					class="fa-solid fa-arrow-left me-1"></i> Xem Trang Chủ</a> <a
					href="${pageContext.request.contextPath}/logout"
					class="btn btn-sm btn-danger"><i
					class="fa-solid fa-arrow-right-from-bracket me-1"></i> Đăng xuất</a>
			</div>
		</div>
	</header>

	<main class="container-fluid px-4 my-4 flex-grow-1">
		<sitemesh:write property='body' />
	</main>

	<jsp:include page="/WEB-INF/views/common/footer.jsp" />

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>