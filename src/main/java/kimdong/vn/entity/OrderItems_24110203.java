package kimdong.vn.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "OrderItems")
public class OrderItems_24110203 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderItemId;

	@ManyToOne
	@JoinColumn(name = "orderId")
	private Orders_24110203 order;

	@ManyToOne
	@JoinColumn(name = "productId")
	private Product_24110203 product;

	private int count;
	private Double price;

	public OrderItems_24110203() {
	}

	public int getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Orders_24110203 getOrder() {
		return order;
	}

	public void setOrder(Orders_24110203 order) {
		this.order = order;
	}

	public Product_24110203 getProduct() {
		return product;
	}

	public void setProduct(Product_24110203 product) {
		this.product = product;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}