package kimdong.vn.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Orders")
public class Orders_24110203 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;

	@ManyToOne
	@JoinColumn(name = "userId")
	private Users_24110203 user;

	private String fullname;
	private String phone;
	private String address;
	private Double totalPrice;
	private String paymentMethod;
	private String status;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate = new Date();

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItems_24110203> orderItems;

	public Orders_24110203() {
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Users_24110203 getUser() {
		return user;
	}

	public void setUser(Users_24110203 user) {
		this.user = user;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public List<OrderItems_24110203> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItems_24110203> orderItems) {
		this.orderItems = orderItems;
	}
}