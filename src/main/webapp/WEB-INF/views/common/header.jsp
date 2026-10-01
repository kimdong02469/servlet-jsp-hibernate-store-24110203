<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<nav class="navbar navbar-expand-lg navbar-custom sticky-top">
	<div class="container">
		<!-- Logo thương hiệu -->
		<a class="navbar-brand brand-logo"
			href="${pageContext.request.contextPath}/home"> <i
			class="fa-solid fa-store me-1"></i> kimdong.vn
		</a>

		<button class="navbar-toggler text-white border-0" type="button"
			data-bs-toggle="collapse" data-bs-target="#navbarMain">
			<i class="fa-solid fa-bars"></i>
		</button>

		<div class="collapse navbar-collapse" id="navbarMain">
			<!-- Menu điều hướng chính -->
			<ul class="navbar-nav me-auto ms-lg-4 mb-2 mb-lg-0">
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/home">Trang Chủ</a></li>
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/products">Sản Phẩm</a></li>

				<!-- Menu Giỏ hàng -->
				<li class="nav-item"><a class="nav-link position-relative"
					href="${pageContext.request.contextPath}/cart"> <i
						class="fa-solid fa-cart-shopping me-1"></i> Giỏ Hàng <c:if
							test="${not empty sessionScope.cart && sessionScope.cart.size() > 0}">
							<span
								class="position-absolute top-1 start-100 translate-middle badge rounded-pill bg-danger"
								style="font-size: 0.7rem;"> ${sessionScope.cart.size()} </span>
						</c:if>
				</a></li>

				<!-- Menu Lịch sử Đơn hàng (dành cho người dùng đã đăng nhập) -->
				<c:if test="${sessionScope.account != null}">
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/order-history"> <i
							class="fa-solid fa-receipt me-1"></i> Đơn Hàng
					</a></li>
				</c:if>

				<!-- Menu Quản Trị (Chỉ hiển thị cho tài khoản Admin có roleId = 1) -->
				<c:if
					test="${sessionScope.account != null && sessionScope.account.roleId == 1}">
					<li class="nav-item"><a class="nav-link text-warning fw-bold"
						href="${pageContext.request.contextPath}/admin/category"> <i
							class="fa-solid fa-gear me-1"></i> Trang Quản Trị
					</a></li>
				</c:if>
			</ul>

			<!-- Khu vực tài khoản / Đăng nhập -->
			<div class="d-flex align-items-center gap-2 mt-3 mt-lg-0">
				<c:choose>
					<c:when test="${sessionScope.account != null}">
						<span class="text-light small me-2"> Xin chào, <strong>${sessionScope.account.fullname}</strong>
						</span>
						<a href="${pageContext.request.contextPath}/logout"
							class="btn btn-outline-danger btn-sm px-3 rounded-2"> <i
							class="fa-solid fa-arrow-right-from-bracket me-1"></i> Đăng xuất
						</a>
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath}/login"
							class="btn btn-outline-light btn-sm px-3 rounded-2"> Đăng
							nhập </a>
						<a href="${pageContext.request.contextPath}/register"
							class="btn btn-warning btn-sm px-3 rounded-2 fw-semibold text-dark">
							Đăng ký </a>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</nav>