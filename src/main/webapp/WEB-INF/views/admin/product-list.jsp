<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>

<div class="d-flex justify-content-between align-items-center mb-3">
	<div>
		<h2 class="text-primary fw-bold mb-1">Quản Lý Sản Phẩm (Product)</h2>
		<p class="text-muted small mb-0">Danh sách toàn bộ các sản phẩm
			trong hệ thống</p>
	</div>
	<a href="${pageContext.request.contextPath}/admin/product/add"
		class="btn btn-success fw-semibold"> <i
		class="fa-solid fa-plus me-1"></i> Thêm Sản Phẩm Mới
	</a>
</div>

<div class="card border-0 shadow-sm rounded-3 overflow-hidden bg-white">
	<div class="table-responsive">
		<table class="table table-hover align-middle mb-0">
			<thead class="table-dark">
				<tr>
					<th style="width: 60px;">ID</th>
					<th style="width: 80px;" class="text-center">Ảnh</th>
					<th>Tên sản phẩm</th>
					<th>Mã SP</th>
					<th>Danh mục</th>
					<th>Giá</th>
					<th style="width: 90px;" class="text-center">Số lượng</th>
					<th style="width: 110px;" class="text-center">Hành động</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${productList}" var="p">
					<tr>
						<td class="fw-bold text-muted">${p.productId}</td>
						<td class="text-center"><img src="${p.images}"
							onerror="this.src='https://placehold.co/50x50/e2e8f0/64748b?text=SP'"
							class="rounded border"
							style="width: 48px; height: 48px; object-fit: cover;" /></td>
						<td><strong class="text-dark">${p.productName}</strong>
							<div class="text-muted small">Seller ID: ${p.sellerId}</div></td>
						<td><span class="badge bg-light text-secondary border">#${p.productCode}</span></td>
						<td><span
							class="badge bg-primary-subtle text-primary border border-primary-subtle">${p.category.categoryName}</span></td>
						<td class="fw-bold text-danger"><fmt:formatNumber
								value="${p.price}" pattern="#,###" /> đ</td>
						<td class="text-center"><span
							class="badge bg-secondary-subtle text-dark px-2 py-1">${p.amount}</span></td>
						<td class="text-center"><a
							href="${pageContext.request.contextPath}/admin/product/edit?id=${p.productId}"
							class="btn btn-sm btn-outline-warning me-1"> <i
								class="fa-solid fa-pen"></i>
						</a> <a
							href="${pageContext.request.contextPath}/admin/product/delete?id=${p.productId}"
							onclick="return confirm('Bạn có chắc muốn xóa?');"
							class="btn btn-sm btn-outline-danger"> <i
								class="fa-solid fa-trash"></i>
						</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<!-- Phân trang -->
	<c:if test="${totalPages > 1}">
		<div
			class="card-footer bg-white d-flex justify-content-between align-items-center py-2">
			<span class="small text-muted">Hiển thị 5 sản phẩm/trang</span>
			<ul class="pagination pagination-sm mb-0">
				<c:forEach begin="1" end="${totalPages}" var="i">
					<li class="page-item ${currentPage == i ? 'active' : ''}"><a
						class="page-link"
						href="${pageContext.request.contextPath}/admin/product?page=${i}">${i}</a>
					</li>
				</c:forEach>
			</ul>
		</div>
	</c:if>
</div>