<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>

<c:if test="${not empty error}">
	<div
		class="alert alert-danger alert-dismissible fade show mb-4 shadow-sm"
		role="alert">
		<i class="fa-solid fa-triangle-exclamation me-2"></i> ${error}
		<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
	</div>
</c:if>

<div class="row g-4">
	<!-- CỘT TRÁI: DANH SÁCH SẢN PHẨM TRONG GIỎ -->
	<div class="col-lg-8 col-12">
		<div class="card border-0 shadow-sm rounded-3 p-4 bg-white">
			<h4 class="fw-bold text-dark mb-3">
				<i class="fa-solid fa-cart-shopping text-warning me-2"></i>Giỏ Hàng
				Của Bạn
			</h4>
			<c:choose>
				<c:when test="${empty cartItems}">
					<div class="alert alert-info text-center py-4 mb-0">
						Giỏ hàng trống! <a
							href="${pageContext.request.contextPath}/products"
							class="alert-link">Mua sắm ngay</a>
					</div>
				</c:when>
				<c:otherwise>
					<div class="table-responsive">
						<table class="table align-middle">
							<thead class="table-light">
								<tr>
									<th>Sản phẩm</th>
									<th>Giá</th>
									<th style="width: 130px;">Số lượng</th>
									<th>Tạm tính</th>
									<th class="text-center">Xóa</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${cartItems}" var="item">
									<tr>
										<td>
											<div class="d-flex align-items-center gap-2">
												<img src="${item.product.images}"
													onerror="this.src='https://placehold.co/50x50/e2e8f0/64748b?text=SP'"
													class="rounded border"
													style="width: 50px; height: 50px; object-fit: cover;" />
												<div>
													<span class="fw-semibold text-dark">${item.product.productName}</span>
													<div class="text-muted small">
														Tồn kho còn: <strong>${item.product.stock != null ? item.product.stock : 0}</strong>
													</div>
												</div>
											</div>
										</td>
										<td class="text-danger fw-bold"><fmt:formatNumber
												value="${item.product.price}" pattern="#,###" /> đ</td>
										<td>
											<form action="${pageContext.request.contextPath}/cart/update"
												method="post" class="d-flex gap-1 align-items-center">
												<input type="hidden" name="id"
													value="${item.product.productId}" /> <input type="number"
													name="quantity" value="${item.quantity}" min="1"
													max="${item.product.stock != null ? item.product.stock : 1}"
													class="form-control form-control-sm text-center"
													onchange="this.form.submit()" />
											</form>
										</td>
										<td class="fw-bold text-dark"><fmt:formatNumber
												value="${item.total}" pattern="#,###" /> đ</td>
										<td class="text-center"><a
											href="${pageContext.request.contextPath}/cart/delete?id=${item.product.productId}"
											class="btn btn-sm btn-outline-danger" title="Xóa khỏi giỏ">
												<i class="fa-solid fa-trash"></i>
										</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>

	<!-- CỘT PHẢI: FORM THANH TOÁN COD -->
	<div class="col-lg-4 col-12">
		<div class="card border-0 shadow-sm rounded-3 p-4 bg-white">
			<h5 class="fw-bold mb-3">Thông Tin Giao Hàng (COD)</h5>
			<div class="d-flex justify-content-between mb-3 border-bottom pb-2">
				<span>Tổng tiền hàng:</span> <span class="text-danger fw-bold fs-5"><fmt:formatNumber
						value="${totalMoney}" pattern="#,###" /> VNĐ</span>
			</div>
			<form action="${pageContext.request.contextPath}/checkout"
				method="post">
				<div class="mb-3">
					<label class="form-label small fw-semibold text-secondary">Họ
						tên người nhận</label> <input type="text" name="fullname"
						value="${sessionScope.account.fullname}" class="form-control"
						required />
				</div>
				<div class="mb-3">
					<label class="form-label small fw-semibold text-secondary">Số
						điện thoại</label> <input type="text" name="phone" class="form-control"
						placeholder="0987xxxxxx" required />
				</div>
				<div class="mb-3">
					<label class="form-label small fw-semibold text-secondary">Địa
						chỉ nhận hàng</label>
					<textarea name="address" rows="2" class="form-control"
						placeholder="Số nhà, tên đường, phường/xã..." required></textarea>
				</div>
				<div class="alert alert-secondary small py-2 mb-3">
					<i class="fa-solid fa-truck text-primary me-1"></i> Phương thức: <strong>Thanh
						toán khi nhận hàng (COD)</strong>
				</div>
				<button type="submit"
					class="btn btn-warning w-100 fw-bold py-2 shadow-sm text-dark"
					${empty cartItems ? 'disabled' : ''}>
					<i class="fa-solid fa-check me-1"></i> Xác Nhận Đặt Hàng COD
				</button>
			</form>
		</div>
	</div>
</div>