# 🛒 Website Thương Mại Điện Tử kimdong.vn (Đề số 05)

Hệ thống quản lý bán hàng sách trực tuyến với đầy đủ phân quyền, giỏ hàng, đặt hàng COD và quản lý danh mục/sản phẩm có phân trang.

---

### 📌 Thông Tin Sinh Viên
- **Họ và tên:** Thạch Kim Đồng
- **MSSV:** 24110203
- **Mã đề:** Đề số 05

---

### 🛠️ Công Nghệ Sử Dụng (Tech Stack)
- **Backend:** Jakarta EE (Servlet 6.0), JPA / Hibernate ORM
- **Frontend:** JSP, JSTL (Jakarta Tags), HTML5/CSS3, Bootstrap 5, FontAwesome
- **Cơ sở dữ liệu:** MySQL (`WebDB_05`)
- **Server:** Apache Tomcat 10+
- **Quản lý mã nguồn & thư viện:** Maven, Git

---

### ✨ Các Tính Năng Đã Triển Khai
1. **Xác thực & Phân quyền (RBAC):**
   - Đăng ký tài khoản gửi mã OTP kích hoạt qua Email.
   - Phân quyền 3 vai trò: Admin (`roleId: 1`), User (`roleId: 2`), Seller (`roleId: 3`).
2. **Khách hàng (User):**
   - Giỏ hàng: Thêm, sửa, xóa, kiểm soát số lượng theo tồn kho thực tế (`stock`).
   - Đặt hàng thanh toán khi nhận hàng (COD).
   - Xem lịch sử đơn hàng và lọc đơn theo 8 trạng thái.
3. **Quản trị (Admin):**
   - CRUD Category & Product có phân trang dữ liệu.
   - Điều chỉnh số lượng và đồng bộ kho hàng tự động.
4. **Cửa hàng (Seller):**
   - Hiển thị và lọc danh sách sản phẩm theo mã gian hàng (`sellerId`).