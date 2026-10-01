package kimdong.vn.entity;

import java.io.Serializable;

public class CartItemBean_24110203 implements Serializable {
	private static final long serialVersionUID = 1L;
	private Product_24110203 product;
	private int quantity;

	public CartItemBean_24110203(Product_24110203 product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	public Product_24110203 getProduct() {
		return product;
	}

	public void setProduct(Product_24110203 product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotal() {
		return product.getPrice() * quantity;
	}
}