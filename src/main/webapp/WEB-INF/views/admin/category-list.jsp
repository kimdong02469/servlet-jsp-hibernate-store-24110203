<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!-- TIÊU ĐỀ TRANG & NÚT THÊM DANH MỤC MỚI -->
<div class="d-flex justify-content-between align-items-center mb-3">
	<div class="d-flex align-items-center gap-3">
		<h2 class="text-primary fw-bold mb-0">
			<i class="fa-solid fa-layer-group me-2"></i>Quản Lý Danh Mục
			(Category)
		</h2>
		<!-- Nút thêm mới giúp reset form bên phải về trạng thái nhập mới -->
		<a href="${pageContext.request.contextPath}/admin/category"
			class="btn btn-success btn-sm fw-semibold shadow-sm"> <i
			class="fa-solid fa-plus me-1"></i> + Thêm Danh Mục Mới
		</a>
	</div>
	<span class="badge bg-primary px-3 py-2 fs-6">Trang
		${currentPage} / ${totalPages > 0 ? totalPages : 1}</span>
</div>

<!-- THANH THỐNG KÊ BỘ LỌC -->
<div
	class="alert alert-info py-2 px-3 small border-start border-4 border-info d-flex justify-content-between align-items-center mb-4 shadow-sm">
	<div>
		<strong>Bộ lọc:</strong> Danh sách toàn bộ danh mục sản phẩm trong hệ
		thống
	</div>
	<span class="badge bg-info text-dark">5 danh mục/trang</span>
</div>

<div class="row g-4">
	<!-- CỘT TRÁI: BẢNG DANH SÁCH & PHÂN TRANG -->
	<div class="col-lg-8 col-12">
		<div
			class="card border-0 shadow-sm rounded-3 overflow-hidden bg-white">
			<div class="table-responsive">
				<table class="table table-hover align-middle mb-0">
					<thead class="table-dark">
						<tr>
							<th style="width: 70px;">ID</th>
							<th style="width: 80px;" class="text-center">Hình ảnh</th>
							<th>Tên danh mục</th>
							<th style="width: 130px;" class="text-center">Trạng thái</th>
							<th style="width: 110px;" class="text-center">Hành động</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${categoryList}" var="c">
							<tr>
								<td class="fw-bold text-secondary">${c.categoryId}</td>
								<td class="text-center"><img src="${c.images}"
									onerror="this.src='https://placehold.co/50x50/e2e8f0/64748b?text=Cat'"
									class="rounded border shadow-sm"
									style="width: 48px; height: 48px; object-fit: cover;"
									alt="${c.categoryName}" /></td>
								<td class="fw-semibold text-dark">${c.categoryName}</td>
								<td class="text-center"><c:choose>
										<c:when test="${c.status == 1}">
											<span
												class="badge bg-success-subtle text-success border border-success-subtle px-2 py-1">Hoạt
												động</span>
										</c:when>
										<c:otherwise>
											<span
												class="badge bg-danger-subtle text-danger border border-danger-subtle px-2 py-1">Khóa</span>
										</c:otherwise>
									</c:choose></td>
								<td class="text-center">
									<!-- Nút Sửa: Tải thông tin vào form cột bên phải --> <a
									href="${pageContext.request.contextPath}/admin/category/edit?id=${c.categoryId}"
									class="btn btn-sm btn-outline-warning me-1" title="Sửa"> <i
										class="fa-solid fa-pen-to-square"></i>
								</a> <!-- Nút Xóa --> <a
									href="${pageContext.request.contextPath}/admin/category/delete?id=${c.categoryId}"
									onclick="return confirm('Bạn có chắc chắn muốn xóa danh mục này?');"
									class="btn btn-sm btn-outline-danger" title="Xóa"> <i
										class="fa-solid fa-trash"></i>
								</a>
								</td>
							</tr>
						</c:forEach>
						<c:if test="${empty categoryList}">
							<tr>
								<td colspan="5" class="text-center py-4 text-muted">Chưa có
									danh mục nào.</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>

			<!-- PHÂN TRANG -->
			<c:if test="${totalPages > 1}">
				<div
					class="card-footer bg-white d-flex justify-content-between align-items-center py-3 border-top">
					<span class="small text-muted">Hiển thị 5 mục mỗi trang</span>
					<ul class="pagination pagination-sm mb-0">
						<c:forEach begin="1" end="${totalPages}" var="i">
							<li class="page-item ${currentPage == i ? 'active' : ''}"><a
								class="page-link"
								href="${pageContext.request.contextPath}/admin/category?page=${i}">${i}</a>
							</li>
						</c:forEach>
					</ul>
				</div>
			</c:if>
		</div>
	</div>

	<!-- CỘT PHẢI: FORM THÊM MỚI / CẬP NHẬT -->
	<div class="col-lg-4 col-12">
		<div
			class="card border-0 shadow-sm rounded-3 overflow-hidden bg-white">
			<div
				class="card-header bg-light py-3 border-bottom fw-bold text-dark">
				<i class="fa-solid fa-file-pen text-primary me-2"></i> ${empty category ? 'Thêm Danh Mục Mới' : 'Cập Nhật Danh Mục'}
			</div>
			<div class="card-body p-4">
				<form action="${pageContext.request.contextPath}/admin/category"
					method="post">
					<!-- Gửi kèm ID ẩn nếu đang ở chế độ sửa -->
					<input type="hidden" name="id" value="${category.categoryId}" />

					<div class="mb-3">
						<label class="form-label small fw-semibold text-secondary">Tên
							danh mục</label> <input type="text" name="categoryName"
							value="${category.categoryName}" class="form-control"
							placeholder="Nhập tên..." required />
					</div>

					<div class="mb-3">
						<label class="form-label small fw-semibold text-secondary">Link
							hình ảnh</label> <input type="text" name="images"
							value="${category.images}" class="form-control"
							placeholder="URL hình ảnh..." />
					</div>

					<div class="mb-4">
						<label class="form-label small fw-semibold text-secondary">Trạng
							thái</label> <select name="status" class="form-select">
							<option value="1" ${category.status == 1 ? 'selected' : ''}>Hoạt
								động</option>
							<option value="0" ${category.status == 0 ? 'selected' : ''}>Khóa</option>
						</select>
					</div>

					<button type="submit"
						class="btn btn-primary w-100 fw-bold py-2 mb-2 shadow-sm">
						${empty category ? 'Lưu Danh Mục' : 'Cập Nhật Thay Đổi'}</button>

					<!-- Nút hủy chế độ sửa, quay lại thêm mới -->
					<c:if test="${not empty category}">
						<a href="${pageContext.request.contextPath}/admin/category"
							class="btn btn-light border w-100 btn-sm text-secondary"> Hủy
							chỉnh sửa </a>
					</c:if>
				</form>
			</div>
		</div>
	</div>
</div>