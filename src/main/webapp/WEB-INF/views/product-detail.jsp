<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>

<div
	class="card border-0 shadow-sm rounded-4 overflow-hidden bg-white p-4 p-md-5 my-2">
	<div class="row g-4 align-items-center">
		<div class="col-md-5 text-center">
			<img src="${p.images}"
				onerror="this.src='https://placehold.co/400x400/e2e8f0/64748b?text=No+Image'"
				class="img-fluid rounded-3 border shadow-sm"
				style="max-height: 380px; width: 100%; object-fit: cover;"
				alt="${p.productName}" />
		</div>
		<div class="col-md-7 ps-md-4">
			<div
				class="badge bg-warning-subtle text-dark border border-warning mb-2 px-3 py-1">Chi
				Tiết Sản Phẩm</div>
			<h2 class="fw-bold text-dark mb-3">${p.productName}</h2>
			<hr class="text-muted" />

			<div class="row g-3 mb-3">
				<div class="col-sm-6">
					<span class="text-muted small d-block">Mã sản phẩm:</span> <strong>#${p.productCode}</strong>
				</div>
				<div class="col-sm-6">
					<span class="text-muted small d-block">Danh mục:</span> <strong
						class="text-primary">${p.category.categoryName}</strong>
				</div>
				<div class="col-sm-6">
					<span class="text-muted small d-block">Giá bán:</span> <span
						class="text-danger fw-bold fs-4"><fmt:formatNumber
							value="${p.price}" pattern="#,###" /> VNĐ</span>
				</div>
				<div class="col-sm-6">
					<span class="text-muted small d-block">Tồn kho hiện có:</span>
					<c:choose>
						<c:when test="${p.stock != null && p.stock > 0}">
							<span class="badge bg-success px-3 py-2 fs-6">${p.stock}
								sản phẩm</span>
						</c:when>
						<c:otherwise>
							<span class="badge bg-danger px-3 py-2 fs-6">Hết hàng</span>
						</c:otherwise>
					</c:choose>
				</div>
			</div>

			<div class="mb-4">
				<h6 class="fw-bold text-secondary mb-1">Mô tả chi tiết:</h6>
				<p class="text-secondary bg-light p-3 rounded-3 border mb-0"
					style="line-height: 1.6;">${empty p.description ? 'Chưa có mô tả cho mặt hàng này.' : p.description}
				</p>
			</div>

			<div class="d-flex gap-3 align-items-center">
				<c:choose>
					<c:when test="${p.stock != null && p.stock > 0}">
						<a
							href="${pageContext.request.contextPath}/cart/add?id=${p.productId}"
							class="btn btn-warning btn-lg px-4 fw-bold shadow-sm"> <i
							class="fa-solid fa-cart-plus me-1"></i> Thêm Vào Giỏ Hàng
						</a>
					</c:when>
					<c:otherwise>
						<button class="btn btn-secondary btn-lg px-4 fw-bold" disabled>
							<i class="fa-solid fa-ban me-1"></i> Hết Hàng
						</button>
					</c:otherwise>
				</c:choose>
				<a href="javascript:history.back()"
					class="btn btn-outline-secondary btn-lg px-4"> <i
					class="fa-solid fa-arrow-left me-1"></i> Quay lại
				</a>
			</div>
		</div>
	</div>
</div>