<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="p-5 mb-4 bg-white rounded-4 shadow-sm border text-center">
	<div class="container-fluid py-4">
		<h1 class="display-5 fw-bold text-dark mb-4">Hệ Thống Mua Sắm
			Trực Tuyến</h1>
		<div class="d-flex justify-content-center gap-3">
			<a href="${pageContext.request.contextPath}/products"
				class="btn btn-primary btn-lg px-4 rounded-3 shadow-sm"> <i
				class="fa-solid fa-store me-2"></i>Xem Sản Phẩm
			</a>
			<c:if test="${empty sessionScope.account}">
				<a href="${pageContext.request.contextPath}/login"
					class="btn btn-outline-dark btn-lg px-4 rounded-3"> Đăng Nhập </a>
			</c:if>
		</div>
	</div>
</div>