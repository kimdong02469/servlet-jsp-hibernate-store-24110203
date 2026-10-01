package kimdong.vn.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CartItem")
public class CartItem_24110203 implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(length = 50)
	private String cartItemId;

	private Integer quantity;
	private Double unitPrice;
	private Integer productId;

	@Column(length = 50)
	private String cartId;

	public CartItem_24110203() {
	}

	public String getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(String cartItemId) {
		this.cartItemId = cartItemId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}
}