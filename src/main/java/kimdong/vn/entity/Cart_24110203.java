package kimdong.vn.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Cart")
public class Cart_24110203 implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(length = 50)
	private String cartId;

	private Integer userId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date buyDate;

	private Integer status;

	public Cart_24110203() {
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}