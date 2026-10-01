<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="row justify-content-center">
	<div class="col-lg-8 col-md-10">
		<div
			class="card border-0 shadow-sm rounded-3 overflow-hidden bg-white">
			<div class="card-header bg-dark text-white py-3 fw-bold">
				<i class="fa-solid fa-box-open text-warning me-2"></i> ${empty product ? 'Thêm Mới Sản Phẩm' : 'Cập Nhật Sản Phẩm'}
			</div>
			<div class="card-body p-4">
				<form action="${pageContext.request.contextPath}/admin/product"
					method="post">
					<input type="hidden" name="id" value="${product.productId}" />

					<div class="row g-3 mb-3">
						<div class="col-md-8">
							<label class="form-label small fw-semibold text-secondary">Tên
								sản phẩm</label> <input type="text" name="productName"
								value="${product.productName}" class="form-control" required />
						</div>
						<div class="col-md-4">
							<label class="form-label small fw-semibold text-secondary">Mã
								sản phẩm (Code)</label> <input type="number" name="productCode"
								value="${product.productCode}" class="form-control" required />
						</div>
					</div>

					<div class="row g-3 mb-3">
						<div class="col-md-6">
							<label class="form-label small fw-semibold text-secondary">Danh
								mục</label> <select name="categoryId" class="form-select" required>
								<c:forEach items="${categories}" var="c">
									<option value="${c.categoryId}"
										${product.category.categoryId == c.categoryId ? 'selected' : ''}>${c.categoryName}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-md-6">
							<label class="form-label small fw-semibold text-secondary">Seller
								ID</label> <input type="number" name="sellerId"
								value="${product.sellerId != null ? product.sellerId : 1}"
								class="form-control" required />
						</div>
					</div>

					<div class="row g-3 mb-3">
						<div class="col-md-4">
							<label class="form-label small fw-semibold text-secondary">Giá
								bán (VNĐ)</label> <input type="number" step="any" name="price"
								value="${product.price}" class="form-control" required />
						</div>
						<div class="col-md-4">
							<label class="form-label small fw-semibold text-secondary">Amount</label>
							<input type="number" name="amount" value="${product.amount}"
								class="form-control" required />
						</div>
						<div class="col-md-4">
							<label class="form-label small fw-semibold text-secondary">Stock</label>
							<input type="number" name="stock" value="${product.stock}"
								class="form-control" required />
						</div>
					</div>

					<div class="mb-3">
						<label class="form-label small fw-semibold text-secondary">Link
							hình ảnh (URL)</label> <input type="text" name="images"
							value="${product.images}" class="form-control" />
					</div>

					<div class="mb-4">
						<label class="form-label small fw-semibold text-secondary">Mô
							tả (Description)</label>
						<textarea name="description" rows="3" class="form-control">${product.description}</textarea>
					</div>

					<div class="d-flex gap-2">
						<button type="submit" class="btn btn-primary px-4 fw-semibold">${empty product ? 'Lưu sản phẩm' : 'Cập nhật'}</button>
						<a href="${pageContext.request.contextPath}/admin/product"
							class="btn btn-outline-secondary px-4">Quay lại</a>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>