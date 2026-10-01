<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title><sitemesh:write property='title' /></title>
<!-- Font Inter & Bootstrap 5 & Font Awesome 6 -->
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

.navbar-custom {
	background-color: #1e2022;
	padding: 12px 0;
	border-bottom: 2px solid #f59e0b;
}

.brand-logo {
	color: #f59e0b !important;
	font-weight: 700;
	font-size: 1.3rem;
	letter-spacing: -0.5px;
}

.nav-link {
	color: #cbd5e1 !important;
	font-size: 0.95rem;
	font-weight: 500;
}

.nav-link:hover {
	color: #ffffff !important;
}

footer {
	background: #1e2022;
	color: #94a3b8;
	font-size: 0.9rem;
	padding: 18px 0;
	margin-top: auto;
}
</style>
<sitemesh:write property='head' />
</head>
<body class="d-flex flex-column min-vh-100">

	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<main class="container my-4 flex-grow-1">
		<sitemesh:write property='body' />
	</main>

	<jsp:include page="/WEB-INF/views/common/footer.jsp" />

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>