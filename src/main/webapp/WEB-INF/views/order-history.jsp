<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>

<div class="card border-0 shadow-sm rounded-3 p-4 bg-white mb-4">
	<div class="d-flex justify-content-between align-items-center mb-3">
		<h4 class="fw-bold text-dark mb-0">
			<i class="fa-solid fa-clock-rotate-left text-warning me-2"></i>Lịch
			Sử Đặt Hàng
		</h4>
		<a href="${pageContext.request.contextPath}/products"
			class="btn btn-sm btn-outline-primary">Tiếp tục mua hàng</a>
	</div>

	<!-- BỘ LỌC TRẠNG THÁI -->
	<div class="d-flex flex-wrap gap-2 mb-4 pt-2 border-top">
		<a href="?status=all"
			class="btn btn-sm ${selectedStatus == 'all' ? 'btn-primary' : 'btn-light border'}">Tất
			cả</a> <a href="?status=Đơn hàng mới"
			class="btn btn-sm ${selectedStatus == 'Đơn hàng mới' ? 'btn-primary' : 'btn-light border'}">Đơn
			hàng mới</a> <a href="?status=Đã xác nhận"
			class="btn btn-sm ${selectedStatus == 'Đã xác nhận' ? 'btn-primary' : 'btn-light border'}">Đã
			xác nhận</a> <a href="?status=Chuẩn bị hàng"
			class="btn btn-sm ${selectedStatus == 'Chuẩn bị hàng' ? 'btn-primary' : 'btn-light border'}">Chuẩn
			bị hàng</a> <a href="?status=Vận chuyển"
			class="btn btn-sm ${selectedStatus == 'Vận chuyển' ? 'btn-primary' : 'btn-light border'}">Vận
			chuyển</a> <a href="?status=Giao hàng"
			class="btn btn-sm ${selectedStatus == 'Giao hàng' ? 'btn-primary' : 'btn-light border'}">Giao
			hàng</a> <a href="?status=Đã giao"
			class="btn btn-sm ${selectedStatus == 'Đã giao' ? 'btn-primary' : 'btn-light border'}">Đã
			giao</a> <a href="?status=Đơn hàng hủy"
			class="btn btn-sm ${selectedStatus == 'Đơn hàng hủy' ? 'btn-primary' : 'btn-light border'}">Đơn
			hàng hủy</a> <a href="?status=Đơn hàng hoàn"
			class="btn btn-sm ${selectedStatus == 'Đơn hàng hoàn' ? 'btn-primary' : 'btn-light border'}">Đơn
			hàng hoàn</a>
	</div>

	<!-- DANH SÁCH ĐƠN HÀNG -->
	<c:choose>
		<c:when test="${empty orders}">
			<div class="alert alert-light text-center py-4 border text-muted">Không
				có đơn hàng nào ở trạng thái này.</div>
		</c:when>
		<c:otherwise>
			<div class="table-responsive">
				<table class="table table-hover align-middle border">
					<thead class="table-dark">
						<tr>
							<th>Mã Đơn</th>
							<th>Ngày Đặt</th>
							<th>Người Nhận & Địa Chỉ</th>
							<th>Tổng Tiền (COD)</th>
							<th class="text-center">Trạng Thái</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${orders}" var="o">
							<tr>
								<td class="fw-bold text-primary">#${o.orderId}</td>
								<td><fmt:formatDate value="${o.createDate}"
										pattern="dd/MM/yyyy HH:mm" /></td>
								<td>
									<div>
										<strong>${o.fullname}</strong> (${o.phone})
									</div> <small class="text-muted">${o.address}</small>
								</td>
								<td class="text-danger fw-bold"><fmt:formatNumber
										value="${o.totalPrice}" pattern="#,###" /> VNĐ</td>
								<td class="text-center"><span
									class="badge 
                                        ${o.status == 'Đơn hàng mới' ? 'bg-secondary' : 
                                          o.status == 'Đã xác nhận' ? 'bg-info' : 
                                          o.status == 'Chuẩn bị hàng' ? 'bg-warning text-dark' : 
                                          o.status == 'Vận chuyển' ? 'bg-primary' : 
                                          o.status == 'Giao hàng' ? 'bg-info-subtle text-dark' : 
                                          o.status == 'Đã giao' ? 'bg-success' : 'bg-danger'} px-3 py-2">
										${o.status} </span></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</c:otherwise>
	</c:choose>
</div>