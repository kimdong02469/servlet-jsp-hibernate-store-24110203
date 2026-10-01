<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>

<div class="mb-4 d-flex justify-content-between align-items-center">
	<div>
		<h2 class="fw-bold text-dark mb-1">
			<i class="fa-solid fa-shop text-warning me-2"></i>Mã cửa hàng: <span
				class="text-primary">${currentSellerId}</span>
		</h2>
		<p class="text-muted small mb-0">Hiển thị danh sách sản phẩm gom
			theo từng SellerID</p>
	</div>
	<div class="d-flex align-items-center gap-2">
		<span class="small fw-semibold text-secondary">Cửa hàng:</span> <a
			href="?sellerId=1"
			class="btn btn-sm ${currentSellerId == 1 ? 'btn-primary' : 'btn-outline-secondary'}">Shop
			1</a> <a href="?sellerId=2"
			class="btn btn-sm ${currentSellerId == 2 ? 'btn-primary' : 'btn-outline-secondary'}">Shop
			2</a>
	</div>
</div>

<c:if test="${param.error == 'outofstock'}">
	<div class="alert alert-danger alert-dismissible fade show"
		role="alert">
		<i class="fa-solid fa-circle-exclamation me-2"></i>Sản phẩm đã hết
		hàng, vui lòng chọn sản phẩm khác!
		<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
	</div>
</c:if>

<div class="row g-4">
	<c:forEach items="${productList}" var="p">
		<div class="col-lg-6 col-12">
			<div
				class="card border-0 shadow-sm rounded-3 overflow-hidden h-100 bg-white p-3">
				<div class="row g-0 align-items-center h-100">
					<div class="col-sm-4 text-center">
						<img src="${p.images}"
							onerror="this.src='https://placehold.co/200x200/e2e8f0/64748b?text=SP'"
							class="img-fluid rounded-2 border"
							style="max-height: 180px; width: 100%; object-fit: cover;"
							alt="${p.productName}" />
					</div>
					<div class="col-sm-8 ps-sm-3 mt-3 mt-sm-0">
						<h5 class="fw-bold mb-2">
							<a
								href="${pageContext.request.contextPath}/product/detail?id=${p.productId}"
								class="text-decoration-none text-dark link-primary">
								${p.productName} </a>
						</h5>
						<ul class="list-unstyled small mb-2">
							<li class="mb-1"><span class="text-muted">Mã sản
									phẩm:</span> <strong>#${p.productCode}</strong></li>
							<li class="mb-1"><span class="text-muted">Danh mục:</span> <span
								class="badge bg-light text-primary border">${p.category.categoryName}</span></li>
							<li class="mb-1"><span class="text-muted">Giá:</span> <strong
								class="text-danger fs-6"><fmt:formatNumber
										value="${p.price}" pattern="#,###" /> VNĐ</strong></li>
							<li class="mb-1"><span class="text-muted">Tồn kho:</span> <c:choose>
									<c:when test="${p.stock != null && p.stock > 0}">
										<span class="badge bg-success-subtle text-success">${p.stock}</span>
									</c:when>
									<c:otherwise>
										<span class="badge bg-danger text-white">Hết hàng</span>
									</c:otherwise>
								</c:choose></li>
						</ul>
						<!-- Nút Thêm vào giỏ -->
						<c:choose>
							<c:when test="${p.stock != null && p.stock > 0}">
								<a
									href="${pageContext.request.contextPath}/cart/add?id=${p.productId}"
									class="btn btn-warning btn-sm fw-semibold"> <i
									class="fa-solid fa-cart-plus me-1"></i> Thêm giỏ hàng
								</a>
							</c:when>
							<c:otherwise>
								<button class="btn btn-secondary btn-sm fw-semibold" disabled>
									<i class="fa-solid fa-ban me-1"></i> Hết hàng
								</button>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>
</div>