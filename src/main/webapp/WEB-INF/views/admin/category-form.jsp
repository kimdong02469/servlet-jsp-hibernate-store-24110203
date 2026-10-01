<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${empty category ? 'Thêm mới Danh mục' : 'Cập nhật Danh mục'}</title>
<style>
.form-container {
	max-width: 500px;
	margin: 20px 0;
	padding: 25px;
	background: #ffffff;
	border-radius: 8px;
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.form-group {
	margin-bottom: 15px;
}

.form-group label {
	display: block;
	margin-bottom: 6px;
	font-weight: bold;
	color: #333;
}

.form-group input[type="text"], .form-group select {
	width: 100%;
	padding: 9px 12px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
	font-size: 14px;
}

.form-group select {
	background-color: white;
}

.btn-group {
	margin-top: 20px;
	display: flex;
	gap: 10px;
}

.btn-submit {
	background-color: #2980b9;
	color: white;
	padding: 10px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	font-weight: bold;
}

.btn-submit:hover {
	background-color: #1f618d;
}

.btn-cancel {
	background-color: #95a5a6;
	color: white;
	padding: 10px 20px;
	border-radius: 4px;
	text-decoration: none;
	display: inline-block;
	text-align: center;
}

.btn-cancel:hover {
	background-color: #7f8c8d;
}
</style>
</head>
<body>
	<h2>${empty category ? 'Thêm mới Danh mục (Category)' : 'Cập nhật Danh mục (Category)'}</h2>

	<div class="form-container">
		<form action="${pageContext.request.contextPath}/admin/category"
			method="post">
			<!-- Nếu là Update thì gửi kèm ID ẩn -->
			<input type="hidden" name="id" value="${category.categoryId}" />

			<div class="form-group">
				<label for="categoryName">Tên danh mục:</label> <input type="text"
					id="categoryName" name="categoryName"
					value="${category.categoryName}" placeholder="Nhập tên danh mục..."
					required />
			</div>

			<div class="form-group">
				<label for="images">Đường dẫn hình ảnh (URL):</label> <input
					type="text" id="images" name="images" value="${category.images}"
					placeholder="https://example.com/image.png" />
			</div>

			<div class="form-group">
				<label for="status">Trạng thái hoạt động:</label> <select
					id="status" name="status">
					<option value="1" ${category.status == 1 ? 'selected' : ''}>Hoạt
						động</option>
					<option value="0" ${category.status == 0 ? 'selected' : ''}>Tạm
						khóa</option>
				</select>
			</div>

			<div class="btn-group">
				<button type="submit" class="btn-submit">${empty category ? 'Lưu mới' : 'Cập nhật'}
				</button>
				<a href="${pageContext.request.contextPath}/admin/category"
					class="btn-cancel">Hủy bỏ</a>
			</div>
		</form>
	</div>
</body>
</html>